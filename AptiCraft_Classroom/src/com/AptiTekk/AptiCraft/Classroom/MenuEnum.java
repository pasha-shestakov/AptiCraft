package com.AptiTekk.AptiCraft.Classroom;

public enum MenuEnum
{
    HOME, SERVERS, TEMPLATES, PLUGINS, SETTINGS;
    
    public String getCardName()
    {
	return this.name();
    }
}
