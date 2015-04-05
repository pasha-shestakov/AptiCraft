package com.AptiTekk.AptiCraft.Student.Networking.TCP;

import com.AptiTekk.AptiCraft.Student.Networking.TCP.Packets.Responses.Response0Authentication;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class TCPListener extends Listener
{
    
    private Client client;

    public TCPListener(Client client)
    {
	this.client = client;
    }
    
    public void connected(Connection connection) 
    {
	System.out.println("Connected to "+connection.getRemoteAddressTCP());
    }

    public void disconnected(Connection connection) 
    {
	System.out.println("Disconnected.");
    }

    public void received(Connection connection, Object object) 
    {
        if (object instanceof Response0Authentication) {
            Response0Authentication response = (Response0Authentication) object;
            System.out.println("Password was "+((response.correctPassword) ? "Correct" : "Incorrect")+".");
        }
    }
}
