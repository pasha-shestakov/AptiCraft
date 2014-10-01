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
	this.propertiesHandler = new PropertiesHandler();
	loggingHandler.setLogLevel((propertiesHandler.getVerboseLogging()) ? Level.ALL : Level.INFO);
	this.workbench = new Workbench(
		this.propertiesHandler.getOpenWorkbenchOnStartup());
    }
    
    public Workbench getWorkbench()
    {
	return workbench;
    }
    
}
