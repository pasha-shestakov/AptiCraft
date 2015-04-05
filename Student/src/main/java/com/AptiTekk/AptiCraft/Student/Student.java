package com.AptiTekk.AptiCraft.Student;

import java.util.logging.Level;

import com.AptiTekk.AptiCraft.Student.Logging.LoggingHandler;
import com.AptiTekk.AptiCraft.Student.Networking.Multicast.LANMulticaster;
import com.AptiTekk.AptiCraft.Student.Networking.TCP.TCPClient;
import com.AptiTekk.AptiCraft.Student.Networking.TCP.Packets.Requests.Request0Authentication;

public class Student
{
    
    private LoggingHandler loggingHandler;

    public Student()
    {
        this.loggingHandler = new LoggingHandler();
        LANMulticaster.startMulticaster(this);
	TCPClient.initialize();
	TCPClient.connectToClassroom("127.0.0.1", 12000);
	TCPClient.sendPacketToServer(Request0Authentication.class);
    }
    
    public void log(String message)
    {
        this.loggingHandler.logMessage(message, Level.INFO);
    }
    
    public void logVerbose(String message)
    {
        this.loggingHandler.logMessage(message, Level.FINE);
    }
    
    public void logWarning(String message)
    {
        this.loggingHandler.logMessage(message, Level.WARNING);
    }
    
    public void logSevere(String message)
    {
        this.loggingHandler.logMessage(message, Level.SEVERE);
    }
    
}
