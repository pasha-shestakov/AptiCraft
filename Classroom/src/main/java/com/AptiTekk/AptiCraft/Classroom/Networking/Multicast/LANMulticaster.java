package com.AptiTekk.AptiCraft.Classroom.Networking.Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import com.AptiTekk.AptiCraft.Classroom.Classroom;

public class LANMulticaster
{
    private static Thread thread;
    private static boolean running = false;
    private static MulticastSocket multicastSocket;
    private static String classroomName;
    private static boolean classroomHasPassword;
    private static int classroomPort;
    private static String multicastIP;
    private static int multicastPort;
    
    private static final String DELIMETER = "§";
    
    public static void startMulticaster(Classroom classroom)
    {
        stopMulticaster(classroom);
        
        classroomName = classroom.getPropertiesHandler().getClassroomName();
        classroomHasPassword = !classroom.getPropertiesHandler().getClassroomPassword().isEmpty();
        classroomPort = classroom.getPropertiesHandler().getClassroomPort();
        multicastIP = classroom.getPropertiesHandler().getMulticastIP();
        multicastPort = classroom.getPropertiesHandler().getMulticastPort();
        
        classroom.logVerbose("Initializing Multicaster...");
        thread = new Thread()
        {
            @Override
            public void interrupt()
            {
                super.interrupt();
                running = false;
                multicastSocket.close();
            }
            
            @Override
            public void run()
            {
                running = true;
                try
                {
                    InetAddress multicastGroup = InetAddress.getByName(multicastIP);
                    multicastSocket = new MulticastSocket(multicastPort);
                    multicastSocket.setInterface(InetAddress.getLocalHost());
                    multicastSocket.joinGroup(multicastGroup);
                    
                    byte[] packet = ("AptiCraft" + DELIMETER + "Classroom" + DELIMETER + classroomName + DELIMETER + classroomHasPassword + DELIMETER + classroomPort).getBytes();
                    
                    DatagramPacket dataPacket = new DatagramPacket(new byte[512], 512);
                    
                    while(running)
                    {
                        dataPacket.setData(new byte[512]);
                        multicastSocket.receive(dataPacket);
                        String data = new String(dataPacket.getData()).trim();
                        String[] dataSplit = data.split(DELIMETER);
                        if(dataSplit.length < 3)
                            continue;
                        
                        if(dataSplit[0].equals("AptiCraft"))
                        {
                            String clientType = dataSplit[1];
                            if(clientType.equals("Student"))
                            {
                                String requestType = dataSplit[2];
                                if(requestType.equals("listClassrooms"))
                                {
                                    multicastSocket.send(new DatagramPacket(packet, packet.length, multicastGroup, multicastPort));
                                }
                            }
                        }
                    }
                }
                catch(SocketException expected)
                {
                    ;
                }
                catch(UnknownHostException e)
                {
                    e.printStackTrace();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        };
        
        thread.start();
    }
    
    public static void stopMulticaster(Classroom classroom)
    {
        if(thread != null)
        {
            classroom.logVerbose("Stopping Multicaster...");
            thread.interrupt();
        }
    }
    
    private static String getLanIP()
    {
        try
        {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            
            while(interfaces.hasMoreElements())
            {
                NetworkInterface iface = interfaces.nextElement();
                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                
                while(addresses.hasMoreElements())
                {
                    InetAddress address = addresses.nextElement();
                    if(address instanceof Inet4Address && !address.isLoopbackAddress())
                        return address.getHostAddress();
                }
            }
            throw new Exception("No usable IPv4 non-loopback address found");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
