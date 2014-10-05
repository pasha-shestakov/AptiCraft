package com.AptiTekk.AptiCraft.Classroom.Networking.TCP;

import java.io.IOException;

import com.AptiTekk.AptiCraft.Classroom.Classroom;
import com.AptiTekk.AptiCraft.Classroom.Networking.TCP.Packets.Packet;
import com.AptiTekk.AptiCraft.Classroom.Networking.TCP.Packets.Requests.Request0Authentication;
import com.AptiTekk.AptiCraft.Classroom.Networking.TCP.Packets.Responses.Response0Authentication;
import com.esotericsoftware.kryonet.Server;

public class TCPServer
{
    
    private static Server server;
    
    public static void startTCPServer(Classroom classroom)
    {
	stopTCPServer(classroom);
	
	classroom.logVerbose("Initializing TCPServer...");
	server = new Server();
	registerPacketTypes();
	server.start();
	try
	{
	    int port = classroom.getPropertiesHandler().getClassroomPort();
	    server.bind(port);
	    classroom.logVerbose("TCPServer bound to port " + port + ".");
	    server.addListener(new TCPListener(classroom));
	}
	catch(IOException e)
	{
	    classroom.logSevere("Could not start TCPServer! Output:");
	    classroom.logSevere(e.toString());
	}
    }
    
    private static void registerPacketTypes()
    {
	// Data Types
	server.getKryo().register(int.class);
	server.getKryo().register(String.class);
	
	// Packets
	server.getKryo().register(Packet.class);
	server.getKryo().register(Request0Authentication.class);
	server.getKryo().register(Response0Authentication.class);
    }
    
    public static void stopTCPServer(Classroom classroom)
    {
	if(server != null)
	{
	    classroom.logVerbose("Stopping TCPServer...");
	    server.stop();
	}
    }
    
}
