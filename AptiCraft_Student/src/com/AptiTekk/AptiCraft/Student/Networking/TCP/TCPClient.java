package com.AptiTekk.AptiCraft.Student.Networking.TCP;

import java.io.IOException;

import com.AptiTekk.AptiCraft.Student.Student;
import com.AptiTekk.AptiCraft.Student.Networking.TCP.Packets.Packet;
import com.AptiTekk.AptiCraft.Student.Networking.TCP.Packets.Requests.Request0Authentication;
import com.AptiTekk.AptiCraft.Student.Networking.TCP.Packets.Responses.Response0Authentication;
import com.esotericsoftware.kryonet.Client;

public class TCPClient
{
    
    private static Student student;
    private static Client client;
    
    public static void initialize()
    {
        TCPClient.client = new Client();
        new Thread(client).start();
        registerPacketTypes();
        client.addListener(new TCPListener(client));
    }
    
    public static void connectToClassroom(String IPAddress, int port)
    {
        try
        {
            client.connect(5000, IPAddress, port);
        }
        catch(IOException e)
        {
            System.out.println("Could not connect.");
        }
    }
    
    public static void sendPacketToServer(Class<? extends Packet> packetType)
    {
        if(client.isConnected())
        {
            if(packetType == Request0Authentication.class)
            {
                Request0Authentication request = new Request0Authentication();
                request.ClassroomPassword = "";
                
                client.sendTCP(request);
            }
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
