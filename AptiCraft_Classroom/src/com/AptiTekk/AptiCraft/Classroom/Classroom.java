package com.AptiTekk.AptiCraft.Classroom;

import java.util.logging.Level;

import com.AptiTekk.AptiCraft.Classroom.Logging.LoggingHandler;
import com.AptiTekk.AptiCraft.Classroom.Properties.PropertiesHandler;

public class Classroom
{
    
    private LoggingHandler loggingHandler;
    private PropertiesHandler propertiesHandler;
    private Workbench workbench;
    
    public Classroom()
    {
	this.loggingHandler = new LoggingHandler();
	this.propertiesHandler = new PropertiesHandler(this);
	loggingHandler.setLogLevel((propertiesHandler.getVerboseLogging()) ? Level.ALL : Level.INFO);
	logVerbose("Creating Workbench...");
	this.workbench = new Workbench(this, 
		this.propertiesHandler.getOpenWorkbenchOnStartup());
    }
    
    public Workbench getWorkbench()
    {
	return workbench;
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
