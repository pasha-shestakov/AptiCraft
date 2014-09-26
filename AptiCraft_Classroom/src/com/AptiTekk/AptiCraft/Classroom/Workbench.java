package com.AptiTekk.AptiCraft.Classroom;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Workbench extends JFrame
{
    
    private BufferedImage logoIcon;
    
    public Workbench()
    {
	initSysTray();
	
	JPanel contentPane = new JPanel(new BorderLayout());
	this.setContentPane(contentPane);
	
	this.setMinimumSize(new Dimension(800, 600));
	this.setMaximumSize(this.getMinimumSize());
	this.setPreferredSize(this.getMaximumSize());
	this.setResizable(false);
	
	//Insert Content Here
	
	this.pack();
	this.setVisible(false);
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
	          showWorkbench();
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
    
    private void showWorkbench()
    {
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }
    
}
