package com.AptiTekk.AptiCraft.Classroom.Networking.Mojang;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.AptiTekk.AptiCraft.Classroom.Classroom;

public class MojangSession
{
    
    private Classroom classroom;
    private String accessToken;
    private String clientToken;

    public MojangSession(Classroom classroom)
    {
	this.classroom = classroom;
	this.accessToken = null;
	this.clientToken = null;
    }
    
    public boolean authenticate()
    {
	HttpClient httpClient = HttpClientBuilder.create().build();
	
	return false;
    }
    
}
