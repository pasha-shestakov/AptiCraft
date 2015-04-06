package com.AptiTekk.AptiCraft.Classroom.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.AptiTekk.AptiCraft.Classroom.Classroom;

@SuppressWarnings("serial")
public class Workbench extends JFrame
{
    
    private final Classroom classroom;
    private HeaderPanel headerPanel;
    private JPanel menuPanel;
    private JPanel bodyPanel;
    private BufferedImage logoIcon;
    private BufferedImage menuIcons;
    private BufferedImage headerImage;
    
    public Workbench(final Classroom classroom, boolean openAutomatically)
    {
	this.classroom = classroom;
	
	loadImages();
	
	this.setLayout(new BorderLayout());
	
	this.setSize(new Dimension(800, 525));
	
	this.setResizable(false);
	this.getContentPane().setBackground(Color.WHITE);
	this.setIconImage(logoIcon);
	
	/* HEADER */
	
	this.headerPanel = new HeaderPanel(headerImage);
	headerPanel.setBackground(new Color(235, 235, 235));
	this.add(headerPanel, BorderLayout.NORTH);
	
	/* MENU */
	
	this.menuPanel = new MenuPanel(menuIcons);
	this.add(menuPanel, BorderLayout.WEST);
	
	
	/* BODY */
	
	this.bodyPanel = new JPanel();
	bodyPanel.setBackground(new Color(0, 0, 0, 0));
	this.add(bodyPanel, BorderLayout.CENTER);
	
	/* FOOTER */
	/*
	this.footerPanel = new JPanel();
	footerPanel.setBackground(new Color(179, 179, 179));
	footerPanel.setPreferredSize(new Dimension(700, 40));
	this.add(footerPanel, BorderLayout.SOUTH);*/
	
	/* END */
	
	this.pack();
	menuPanel.validate();
	
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	
	addWindowListener(new WindowAdapter()
	{
	    @Override
	    public void windowClosing(WindowEvent e)
	    {
		closeWorkbench();
	    }
	});
	
	if(openAutomatically)
	    this.showWorkbench();
	else
	    this.setVisible(false);
    }
    
    private void loadImages()
    {
	try
	{
	    logoIcon = classroom.logoIcon;
	    menuIcons = ImageIO.read(Workbench.class
		    .getResource("/images/gui/menu/Icons.png"));
	    headerImage = ImageIO.read(Workbench.class
		    .getResource("/images/gui/header/Header.png"));
	}
	catch(IOException e)
	{
	    e.printStackTrace();
	}
    }
    
    public void showWorkbench()
    {
	this.classroom.logVerbose("Opening Workbench...");
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }
    
    public void closeWorkbench()
    {
	classroom.logVerbose("Closing Workbench...");
	setVisible(false);
	dispose();
    }
    
}
