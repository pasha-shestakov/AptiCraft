package com.AptiTekk.AptiCraft.Classroom;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
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
	
	this.setMinimumSize(new Dimension(700, 450));
	this.setMaximumSize(this.getMinimumSize());
	this.setPreferredSize(this.getMaximumSize());
	this.setResizable(false);
	this.getContentPane().setBackground(new Color(230, 230, 230));
	this.setIconImage(logoIcon);
	
	JPanel headerPanel = new JPanel();
	headerPanel.setBackground(new Color(0,0,0,0));
	headerPanel.setPreferredSize(new Dimension(700,50));
	this.add(headerPanel, BorderLayout.NORTH);
	
	JPanel menuPanel = new JPanel();
	menuPanel.setBackground(new Color(0,0,0,0));
	menuPanel.setPreferredSize(new Dimension(60,334));
	this.add(menuPanel, BorderLayout.WEST);
	
	JPanel bodyPanel = new JPanel();
	bodyPanel.setBackground(new Color(0,0,0,0));
	this.add(bodyPanel, BorderLayout.CENTER);
	
	JPanel footerPanel = new JPanel();
	footerPanel.setBackground(new Color(0,0,0,0));
	footerPanel.setPreferredSize(new Dimension(700,40));
	this.add(footerPanel, BorderLayout.SOUTH);
	
	//Insert Content Here
	
	this.pack();
	this.setVisible(false);
	
	System.out.println("Header: "+headerPanel.getSize());
	System.out.println("Menu: "+menuPanel.getSize());
	System.out.println("Body: "+bodyPanel.getSize());
	System.out.println("Footer: "+footerPanel.getSize());
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
