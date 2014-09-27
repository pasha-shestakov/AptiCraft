package com.AptiTekk.AptiCraft.Classroom.Properties;

public enum PropertiesEnum
{
    CLASSROOM_NAME("classroomName", "AptiCraft"),
    CLASSROOM_PASSWORD("classroomPassword", ""),
    OPEN_WORKBENCH_ON_STARTUP("openWorkbenchOnStartup", "true");
    
    private String key;
    private String defaultValue;

    private PropertiesEnum(String key, String defaultValue)
    {
	this.key = key;
	this.defaultValue = defaultValue;
    }
    
    public String getKey()
    {
	return key;
    }
    
    public String getDefaultValue()
    {
	return defaultValue;
    }
    
}
