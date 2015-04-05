package com.AptiTekk.AptiCraft.Classroom.Properties;

public enum PropertiesEnum
{
    CLASSROOM_NAME("ClassroomName", "AptiCraft"),
    CLASSROOM_PASSWORD("ClassroomPassword", ""),
    CLASSROOM_PORT("ClassroomPort", "12000"),
    MULTICAST_IP("MulticastIP", "239.1.20.0"),
    MULTICAST_PORT("MulticastPort", "12010"),
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
