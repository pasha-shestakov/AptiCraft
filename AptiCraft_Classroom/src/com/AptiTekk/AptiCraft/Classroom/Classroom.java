package com.AptiTekk.AptiCraft.Classroom;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import com.AptiTekk.AptiCraft.Classroom.Logging.LoggingHandler;
import com.AptiTekk.AptiCraft.Classroom.Properties.PropertiesHandler;
import com.AptiTekk.AptiCraft.Classroom.TCP.TCPServer;

public class Classroom
{
    
    private LoggingHandler loggingHandler;
    private PropertiesHandler propertiesHandler;
    private Workbench workbench;
    BufferedImage logoIcon;
    
    public Classroom()
    {
	this.loggingHandler = new LoggingHandler();
	this.propertiesHandler = new PropertiesHandler(this);
	loggingHandler
		.setLogLevel((propertiesHandler.getVerboseLogging()) ? Level.ALL
			: Level.INFO);
	log("Initializing Classroom...");
	TCPServer.startTCPServer(this);
	logVerbose("Creating Workbench...");
	this.workbench = new Workbench(this,
		this.propertiesHandler.getOpenWorkbenchOnStartup());
	logVerbose("Loading Classroom Images...");
	loadImages();
	logVerbose("Initializing Classroom System Tray Icon...");
	initSysTray();
	log("Classroom Initialization Complete!");
    }
    
    private void loadImages()
    {
	try
	{
	    logoIcon = ImageIO
		    .read(Classroom.class
			    .getResource("/assets/images/AptiCraft-Server-Logo-64px.png"));
	}
	catch(IOException e)
	{
	    e.printStackTrace();
	}
    }
    
    private void initSysTray()
    {
	if(SystemTray.isSupported())
	{
	    final TrayIcon trayIcon = new TrayIcon(logoIcon,
		    "AptiCraft Classroom");
	    trayIcon.setImageAutoSize(true);
	    trayIcon.addActionListener(new ActionListener()
	    {
		public void actionPerformed(ActionEvent e)
		{
		    if(workbench != null)
			workbench.showWorkbench();
		}
	    });
	    SystemTray systemTray = SystemTray.getSystemTray();
	    
	    PopupMenu popup = new PopupMenu();
	    MenuItem workbenchItem = new MenuItem("Open Workbench");
	    workbenchItem.addActionListener(new ActionListener()
	    {
		@Override
		public void actionPerformed(ActionEvent e)
		{
		    if(workbench != null)
			workbench.showWorkbench();
		}
	    });
	    popup.add(workbenchItem);
	    
	    MenuItem exitItem = new MenuItem("Shutdown Classroom");
	    exitItem.addActionListener(new ActionListener()
	    {
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
		    shutdownClassroom();
		}
	    });
	    popup.add(exitItem);
	    
	    trayIcon.setPopupMenu(popup);
	    
	    try
	    {
		systemTray.add(trayIcon);
	    }
	    catch(AWTException e)
	    {
		logSevere("Could not Initialize Classroom System Tray Icon! Output:");
		e.printStackTrace();
	    }
	}
	else
	{
	    logWarning("System does not support Tray Icons!");
	}
    }
    
    protected void shutdownClassroom()
    {
	log("Shutting Down Classroom...");
	workbench.closeWorkbench();
	TCPServer.stopTCPServer();
	propertiesHandler.closeProperties();
	log("Classroom Shutdown Complete!");
	System.exit(0);
    }
    
    public Workbench getWorkbench()
    {
	return workbench;
    }
    
    public void log(String message)
    {
	this.loggingHandler.logMessage(message, Level.INFO);
    }
    
    public void logVerbose(String message)
    {
	this.loggingHandler.logMessage(message, Level.FINE);
    }
    
    public void logWarning(String message)
    {
	this.loggingHandler.logMessage(message, Level.WARNING);
    }
    
    public void logSevere(String message)
    {
	this.loggingHandler.logMessage(message, Level.SEVERE);
    }
    
}
