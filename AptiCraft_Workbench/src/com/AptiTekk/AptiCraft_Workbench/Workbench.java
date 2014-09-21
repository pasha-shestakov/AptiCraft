package com.AptiTekk.AptiCraft_Workbench;

import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Workbench
{
    
    public static Workbench self;
    
    private BufferedImage logoIcon;
    
    public static void main(String[] args)
    {
	self = new Workbench();
    }
    
    public Workbench()
    {
	initSysTray();
    }
    
    private void initSysTray()
    {
	try
	{
	    logoIcon = ImageIO.read(Workbench.class.getResource("/assets/images/AptiCraft-Server-Logo-64px.png"));
	}
	catch(IOException e)
	{
	    e.printStackTrace();
	}
	if(SystemTray.isSupported())
	{
	    final TrayIcon trayIcon = new TrayIcon(logoIcon, "AptiCraft Classroom");
	    trayIcon.setImageAutoSize(true);
	    trayIcon.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          trayIcon.displayMessage("Double Click!", "Some action performed", TrayIcon.MessageType.INFO);
	        }
	      });
	    SystemTray systemTray = SystemTray.getSystemTray();
	    try
	    {
		systemTray.add(trayIcon);
	    }
	    catch(AWTException e)
	    {
		e.printStackTrace();
	    }
	    	
	}
    }
    
}
