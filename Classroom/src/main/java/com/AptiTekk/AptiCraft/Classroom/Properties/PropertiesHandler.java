package com.AptiTekk.AptiCraft.Classroom.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.AptiTekk.AptiCraft.Classroom.Classroom;
import com.AptiTekk.AptiCraft.Classroom.Utilities.Utilities;

public class PropertiesHandler
{
    
    public static final String propertiesFileName = "Classroom.properties";
    public static final File propertiesFile = new File(
	    Utilities.getRootDirectory(), propertiesFileName);
    private Properties properties;
    private Classroom classroom;
    private FileInputStream inputStream;
    
    public PropertiesHandler(Classroom classroom)
    {
	this.classroom = classroom;
	loadProperties();
	classroom.logVerbose("Properties Loaded.");
	validateProperties();
	classroom.logVerbose("Properties Validated.");
    }
    
    private void loadProperties()
    {
	classroom.logVerbose("Loading Properties...");
	this.properties = new Properties();
	try
	{
	    this.inputStream = new FileInputStream(propertiesFile);
	    properties.load(inputStream);
	}
	catch(FileNotFoundException e)
	{
	    createNewProperties();
	}
	catch(IOException e)
	{
	    e.printStackTrace();
	}
    }
    
    private void createNewProperties()
    {
	classroom.logVerbose("Creating new Properties file...");
	for(PropertiesEnum key : PropertiesEnum.values())
	{
	    setProperty(key, key.getDefaultValue());
	}
	saveProperties();
    }
    
    private void validateProperties()
    {
	classroom.logVerbose("Validating Properties...");
	boolean needsSave = false;
	for(PropertiesEnum key : PropertiesEnum.values())
	{
	    if(!this.properties.containsKey(key.getKey()))
	    {
		setProperty(key, key.getDefaultValue());
		needsSave = true;
	    }
	}
	if(needsSave)
	    saveProperties();
    }
    
    public void closeProperties()
    {
	classroom.logVerbose("Closing Properties...");
	saveProperties();
	if(this.inputStream != null)
	    try
	    {
		this.inputStream.close();
	    }
	    catch(IOException e)
	    {
		e.printStackTrace();
	    }
    }
    
    private void setProperty(PropertiesEnum key, String value)
    {
	this.properties.setProperty(key.getKey(), value);
	this.saveProperties();
    }
    
    private void setProperty(PropertiesEnum key, boolean value)
    {
	this.properties.setProperty(key.getKey(), (value) ? "true" : "false");
	this.saveProperties();
    }
    
    private void setProperty(PropertiesEnum key, int value)
    {
	this.properties.setProperty(key.getKey(), value + "");
	this.saveProperties();
    }
    
    private void saveProperties()
    {
	classroom.logVerbose("Saving Properties...");
	try
	{
	    FileOutputStream outputStream = new FileOutputStream(propertiesFile);
	    this.properties.store(outputStream, null);
	    outputStream.close();
	}
	catch(FileNotFoundException e)
	{
	    e.printStackTrace();
	}
	catch(IOException e)
	{
	    e.printStackTrace();
	}
    }
    
    private String parseString(PropertiesEnum key)
    {
	return this.properties.getProperty(key.getKey(), key.getDefaultValue());
    }
    
    private boolean parseBoolean(PropertiesEnum key)
    {
	return this.properties.getProperty(key.getKey(), key.getDefaultValue())
		.equals("true") ? true : false;
    }
    
    private int parseInteger(PropertiesEnum key)
    {
	int parsed = 0;
	
	try
	{
	    parsed = Integer.parseInt(this.properties.getProperty(key.getKey(),
		    key.getDefaultValue()));
	}
	catch(NumberFormatException e)
	{
	    parsed = Integer.parseInt(key.getDefaultValue());
	}
	
	return parsed;
    }
    
    public String getClassroomName()
    {
	return parseString(PropertiesEnum.CLASSROOM_NAME);
    }
    
    public void setClassroomName(String name)
    {
	setProperty(PropertiesEnum.CLASSROOM_NAME, name);
    }
    
    public String getClassroomPassword()
    {
	return parseString(PropertiesEnum.CLASSROOM_PASSWORD);
    }
    
    public void setClassroomPassword(String password)
    {
	setProperty(PropertiesEnum.CLASSROOM_PASSWORD, password);
    }
    
    public int getClassroomPort()
    {
	return parseInteger(PropertiesEnum.CLASSROOM_PORT);
    }
    
    public void setClassroomPort(int port)
    {
	setProperty(PropertiesEnum.CLASSROOM_PORT, port);
    }
    
    public String getMulticastIP()
    {
	return parseString(PropertiesEnum.MULTICAST_IP);
    }
    
    public void setMulticastIP(String IP)
    {
	setProperty(PropertiesEnum.MULTICAST_IP, IP);
    }
    
    public int getMulticastPort()
    {
	return parseInteger(PropertiesEnum.MULTICAST_PORT);
    }
    
    public void setMulticastPort(int port)
    {
	setProperty(PropertiesEnum.MULTICAST_PORT, port);
    }
    
    public boolean getOpenWorkbenchOnStartup()
    {
	return parseBoolean(PropertiesEnum.OPEN_WORKBENCH_ON_STARTUP);
    }
    
    public void setOpenWorkbenchOnStartup(boolean open)
    {
	setProperty(PropertiesEnum.OPEN_WORKBENCH_ON_STARTUP, open);
    }
    
    public boolean getVerboseLogging()
    {
	return parseBoolean(PropertiesEnum.VERBOSE_LOGGING);
    }
    
    public void setVerboseLogging(boolean verbose)
    {
	setProperty(PropertiesEnum.VERBOSE_LOGGING, verbose);
    }
    
}
