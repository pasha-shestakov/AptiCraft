package com.AptiTekk.AptiCraft.Classroom.TCP;

import java.io.IOException;

import com.AptiTekk.AptiCraft.Classroom.Classroom;
import com.AptiTekk.AptiCraft.Classroom.TCP.Packets.Packet;
import com.AptiTekk.AptiCraft.Classroom.TCP.Packets.Requests.Request0Authentication;
import com.AptiTekk.AptiCraft.Classroom.TCP.Packets.Responses.Response0Authentication;
import com.esotericsoftware.kryonet.Server;

public class TCPServer
{
    
    private static Classroom classroom;
    private static Server server;
    
    public static void startTCPServer(Classroom classroom)
    {
	TCPServer.classroom = classroom;
	classroom.logVerbose("Starting TCPServer...");
	if(server != null)
	{
	    classroom.logVerbose("Old TCPServer Detected!");
	    stopTCPServer();
	}
	
	server = new Server();
	registerPacketTypes();
	server.start();
	try
	{
	    int port = classroom.getPropertiesHandler().getClassroomPort();
	    server.bind(port);
	    classroom.logVerbose("Server bound to TCP Port "+port+".");
	    server.addListener(new TCPListener(classroom));
	    classroom.logVerbose("Added TCPListener.");
	}
	catch(IOException e)
	{
	    classroom.logSevere("Could not start TCPServer! Output:");
	    classroom.logSevere(e.toString());
	}
	classroom.log("TCPServer Started.");
    }
    
    private static void registerPacketTypes()
    {
	//Data Types
	server.getKryo().register(int.class);
	server.getKryo().register(String.class);
	
	//Packets
	server.getKryo().register(Packet.class);
	server.getKryo().register(Request0Authentication.class);
	server.getKryo().register(Response0Authentication.class);
    }
    
    public static void stopTCPServer()
    {
	classroom.logVerbose("Stopping TCPServer...");
	if(server != null)
	    server.stop();
	classroom.log("TCPServer Stopped.");
    }
    
}
