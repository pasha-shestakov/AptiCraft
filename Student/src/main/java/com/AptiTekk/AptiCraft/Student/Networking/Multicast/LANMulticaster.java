package com.AptiTekk.AptiCraft.Student.Networking.Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

import com.AptiTekk.AptiCraft.Student.Student;

public class LANMulticaster
{
    private static Thread thread;
    private static boolean running = false;
    private static MulticastSocket multicastSocket;
    private static String multicastIP;
    private static int multicastPort;
    
    private static final String DELIMETER = "§";
    
    public static void startMulticaster(Student student)
    {
        stopMulticaster(student);
        
        multicastIP = "239.1.20.0";
        multicastPort = 12010;
        
        student.logVerbose("Initializing Multicaster...");
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
                    
                    byte[] packet = ("AptiCraft" + DELIMETER + "Student" + DELIMETER + "listClassrooms").getBytes();
                    
                    DatagramPacket dataPacket = new DatagramPacket(new byte[512], 512);
                    
                    multicastSocket.send(new DatagramPacket(packet, packet.length, multicastGroup, multicastPort));
                    System.out.println("Classrooms Available:");
                    
                    while(running)
                    {
                        dataPacket.setData(new byte[512]);
                        multicastSocket.receive(dataPacket);
                        String data = new String(dataPacket.getData()).trim();
                        String[] dataSplit = data.split(DELIMETER);
                        if(dataSplit.length < 5)
                            continue;
                        
                        if(dataSplit[0].equals("AptiCraft"))
                        {
                            String clientType = dataSplit[1];
                            if(clientType.equals("Classroom"))
                            {
                                String classroomName = dataSplit[2];
                                boolean hasPassword = Boolean.parseBoolean(dataSplit[3]);
                                int classroomPort = Integer.parseInt(dataSplit[4]);
                                
                                System.out.println("Name: " + classroomName + " / Has Password: " + hasPassword + " / Port: " + classroomPort);
                            }
                        }
                    }
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
    
    public static void stopMulticaster(Student student)
    {
        if(thread != null)
        {
            student.logVerbose("Stopping Multicaster...");
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
