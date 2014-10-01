package com.AptiTekk.AptiCraft.Classroom.Properties;

public enum PropertiesEnum
{
    CLASSROOM_NAME("ClassroomName", "AptiCraft"),
    CLASSROOM_PASSWORD("ClassroomPassword", ""),
    OPEN_WORKBENCH_ON_STARTUP("OpenWorkbenchOnStartup", "true"),
    VERBOSE_LOGGING("VerboseLogging", "false");
    
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
