package com.AptiTekk.AptiCraft.Classroom;

import com.AptiTekk.AptiCraft.Classroom.Properties.PropertiesHandler;

public class Classroom
{
    
    private Workbench workbench;
    private PropertiesHandler propertiesHandler;
    
    public Classroom()
    {
	this.propertiesHandler = new PropertiesHandler();
	this.workbench = new Workbench(this.propertiesHandler.getOpenWorkbenchOnStartup());
    }
    
    public Workbench getWorkbench()
    {
	return workbench;
    }
    
}
