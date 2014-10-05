package com.AptiTekk.AptiCraft.Student;

import com.AptiTekk.AptiCraft.Student.TCP.TCPClient;
import com.AptiTekk.AptiCraft.Student.TCP.Packets.Requests.Request0Authentication;

public class Student
{
    
    public Student()
    {
	System.out.println("Testing...");
	TCPClient.sendPacketToServer(Request0Authentication.class);
    }
    
}
