package com.AptiTekk.AptiCraft.Classroom.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.AptiTekk.AptiCraft.Classroom.Utilities;

public class PropertiesHandler
{
    
    public static final String propertiesFileName = "Classroom.properties";
    public static final File propertiesFile = new File(
	    Utilities.getRootDirectory(), propertiesFileName);
    private Properties properties;
    
    public PropertiesHandler()
    {
	loadProperties();
	validateProperties();
    }
    
    private void createNewProperties()
    {
	for(PropertiesEnum key : PropertiesEnum.values())
	{
	    setProperty(key, key.getDefaultValue());
	}
	saveProperties();
    }
    
    private void validateProperties()
    {
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
    
    private boolean parseBoolean(PropertiesEnum key)
    {
	return this.properties.getProperty(key.getKey(), key.getDefaultValue())
		.equals("true") ? true : false;
    }
    
    private void setProperty(PropertiesEnum key, String value)
    {
	this.properties.setProperty(key.getKey(), value);
    }
    
    private void setProperty(PropertiesEnum key, boolean value)
    {
	this.properties.setProperty(key.getKey(), (value) ? "true" : "false");
    }
    
    private void saveProperties()
    {
	try
	    {
		this.properties.store(new FileOutputStream(propertiesFile),
			null);
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
    
    private void loadProperties()
    {
	this.properties = new Properties();
	try
	{
	    properties.load(new FileInputStream(propertiesFile));
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
    
    private String parseString(PropertiesEnum key)
    {
	return this.properties.getProperty(key.getKey(), key.getDefaultValue());
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
    
    public boolean getOpenWorkbenchOnStartup()
    {
	return parseBoolean(PropertiesEnum.OPEN_WORKBENCH_ON_STARTUP);
    }
    
    public void setOpenWorkbenchOnStartup(boolean open)
    {
	setProperty(PropertiesEnum.OPEN_WORKBENCH_ON_STARTUP, open);
    }
    
}
