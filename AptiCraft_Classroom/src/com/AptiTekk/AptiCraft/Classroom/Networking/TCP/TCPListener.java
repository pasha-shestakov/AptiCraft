package com.AptiTekk.AptiCraft.Classroom.Networking.TCP;

import com.AptiTekk.AptiCraft.Classroom.Classroom;
import com.AptiTekk.AptiCraft.Classroom.Networking.TCP.Packets.Packet;
import com.AptiTekk.AptiCraft.Classroom.Networking.TCP.Packets.Requests.Request0Authentication;
import com.AptiTekk.AptiCraft.Classroom.Networking.TCP.Packets.Responses.Response0Authentication;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class TCPListener extends Listener
{
    private Classroom classroom;
    
    public TCPListener(Classroom classroom)
    {
	this.classroom = classroom;
    }
    
    public void connected(Connection connection)
    {
	classroom.logVerbose("Client Connected: "
		+ connection.getRemoteAddressTCP());
    }
    
    public void disconnected(Connection connection)
    {
	classroom.logVerbose("Client Disconnected: "
		+ connection.getRemoteAddressTCP());
    }
    
    public void received(Connection connection, Object object)
    {
	if(object instanceof Packet)
	{
	    if(object instanceof Request0Authentication)
	    {
		Request0Authentication request = (Request0Authentication) object;
		classroom.logVerbose("Recieved Message From Client: "
			+ request.text);
		
		Response0Authentication response = new Response0Authentication();
		response.text = "It Worked!";
		connection.sendTCP(response);
	    }
	}
    }
}
