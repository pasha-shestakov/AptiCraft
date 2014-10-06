package com.AptiTekk.AptiCraft.Student.TCP;

import java.io.IOException;

import com.AptiTekk.AptiCraft.Student.Student;
import com.AptiTekk.AptiCraft.Student.TCP.Packets.Packet;
import com.AptiTekk.AptiCraft.Student.TCP.Packets.Requests.Request0Authentication;
import com.AptiTekk.AptiCraft.Student.TCP.Packets.Responses.Response0Authentication;
import com.esotericsoftware.kryonet.Client;

public class TCPClient
{
    
    private static Student student;
    private static Client client;
    
    public static void sendPacketToServer(Class<? extends Packet> packetType)
    {
	TCPClient.client = new Client();
	registerPacketTypes();
	new Thread(client).start();
	client.addListener(new TCPListener(client, packetType));
	
	    try
	    {
		client.connect(5000, "127.0.0.1", 12000);
	    }
	    catch(IOException e)
	    {
		System.out.println("Could not connect.");
	    }
    }
    
    private static void registerPacketTypes()
    {
	//Data Types
	client.getKryo().register(int.class);
	client.getKryo().register(String.class);
	client.getKryo().register(boolean.class);
	
	//Packets
	client.getKryo().register(Packet.class);
	client.getKryo().register(Request0Authentication.class);
	client.getKryo().register(Response0Authentication.class);
    }
   
    
}
