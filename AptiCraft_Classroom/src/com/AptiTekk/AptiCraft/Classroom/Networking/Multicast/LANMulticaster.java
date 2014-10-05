package com.AptiTekk.AptiCraft.Classroom.Networking.Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
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
    
    public static void startMulticaster(Classroom classroom)
    {
	stopMulticaster(classroom);
	
	classroomName = classroom.getPropertiesHandler().getClassroomName();
	classroomHasPassword = !classroom.getPropertiesHandler()
		.getClassroomPassword().isEmpty();
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
		    InetAddress multicastGroup = InetAddress
			    .getByName(multicastIP);
		    multicastSocket = new MulticastSocket(multicastPort);
		    multicastSocket.setInterface(InetAddress.getLocalHost());
		    multicastSocket.joinGroup(multicastGroup);
		    
		    byte[] packet = ("§AptiCraft§" + classroomName + "§"
			    + classroomHasPassword + "§" + classroomPort + "§")
			    .getBytes();
		    
		    while(running)
		    {
			multicastSocket.send(new DatagramPacket(packet,
				packet.length, multicastGroup, multicastPort));
			Thread.sleep(500);
		    }
		}
		catch(InterruptedException e)
		{
		    // Ignore
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
	    Enumeration<NetworkInterface> interfaces = NetworkInterface
		    .getNetworkInterfaces();
	    
	    while(interfaces.hasMoreElements())
	    {
		NetworkInterface iface = interfaces.nextElement();
		Enumeration<InetAddress> addresses = iface.getInetAddresses();
		
		while(addresses.hasMoreElements())
		{
		    InetAddress address = addresses.nextElement();
		    if(address instanceof Inet4Address
			    && !address.isLoopbackAddress())
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
