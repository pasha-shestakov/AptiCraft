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
	validateProperties();
    }
    
    private void createNewProperties()
    {
	for(PropertiesEnum key : PropertiesEnum.values())
	{
	    this.properties.setProperty(key.getKey(), key.getDefaultValue());
	}
	try
	{
	    this.properties.store(new FileOutputStream(propertiesFile), null);
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
    
    private void validateProperties()
    {
	boolean needsSave = false;
	for(PropertiesEnum key : PropertiesEnum.values())
	{
	    if(!this.properties.containsKey(key.getKey()))
	    {
		this.properties
			.setProperty(key.getKey(), key.getDefaultValue());
		needsSave = true;
	    }
	}
	if(needsSave)
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
    
    private boolean parseBoolean(PropertiesEnum key)
    {
	return this.properties.getProperty(key.getKey(), key.getDefaultValue())
		.equals("true") ? true : false;
    }
    
    private String parseString(PropertiesEnum key)
    {
	return this.properties.getProperty(key.getKey(), key.getDefaultValue());
    }
    
    public String getClassroomName()
    {
	return parseString(PropertiesEnum.CLASSROOM_NAME);
    }
    
    public String getClassroomPassword()
    {
	return parseString(PropertiesEnum.CLASSROOM_PASSWORD);
    }
    
    public boolean shouldOpenWorkbenchOnStartup()
    {
	return parseBoolean(PropertiesEnum.OPEN_WORKBENCH_ON_STARTUP);
    }
    
}
