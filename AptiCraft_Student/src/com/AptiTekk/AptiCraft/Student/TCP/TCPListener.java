package com.AptiTekk.AptiCraft.Student.TCP;

import com.AptiTekk.AptiCraft.Student.TCP.Packets.Packet;
import com.AptiTekk.AptiCraft.Student.TCP.Packets.Requests.Request0Authentication;
import com.AptiTekk.AptiCraft.Student.TCP.Packets.Responses.Response0Authentication;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class TCPListener extends Listener
{
    
    private Class<? extends Packet> packetType;
    private Client client;

    public TCPListener(Client client, Class<? extends Packet> packetType)
    {
	this.client = client;
	this.packetType = packetType;
    }
    
    public void connected(Connection connection) 
    {
	System.out.println("Connected.");
	if(packetType == Request0Authentication.class)
	{
	    Request0Authentication request = new Request0Authentication();
	    request.text = "Hi Server!";
	    
	    client.sendTCP(request);
	}
    }

    public void disconnected(Connection connection) 
    {
	System.out.println("Disconnected.");
    }

    public void received(Connection connection, Object object) 
    {
        if (object instanceof Response0Authentication) {
            Response0Authentication response = (Response0Authentication) object;
            System.out.println(response.text);
        }
    }
}
