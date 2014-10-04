package com.AptiTekk.AptiCraft.Classroom.TCP;

import java.io.IOException;

import com.AptiTekk.AptiCraft.Classroom.Classroom;
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
	server.start();
	try
	{
	    server.bind(12000);
	    classroom.logVerbose("Server bound to TCP Port 12000.");
	    server.addListener(new TCPListener());
	    classroom.logVerbose("Added TCPListener.");
	}
	catch(IOException e)
	{
	    classroom.logSevere("Could not start TCPServer! Output:");
	    e.printStackTrace();
	}
	classroom.log("TCPServer Started.");
    }
    
    public static void stopTCPServer()
    {
	classroom.logVerbose("Stopping TCPServer...");
	if(server != null)
	    server.stop();
	classroom.log("TCPServer Stopped.");
    }
    
}
