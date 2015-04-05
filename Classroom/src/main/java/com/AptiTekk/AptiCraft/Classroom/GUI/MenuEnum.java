package com.AptiTekk.AptiCraft.Classroom.GUI;

public enum MenuEnum
{
    HOME,
    SERVERS,
    TEMPLATES,
    PLUGINS,
    SETTINGS;
    
    public String getCardName()
    {
	return this.name();
    }
}
