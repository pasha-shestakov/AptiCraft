package com.AptiTekk.AptiCraft.Student.Networking.TCP.Packets.Responses;

import com.AptiTekk.AptiCraft.Student.Networking.TCP.Packets.Packet;

public class Response0Authentication extends Packet
{
    public boolean correctPassword;
    public String accessToken;
    public String clientToken;
}
