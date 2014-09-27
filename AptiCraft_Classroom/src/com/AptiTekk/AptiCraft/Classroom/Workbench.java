package com.AptiTekk.AptiCraft.Classroom;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Workbench extends JFrame implements MouseListener
{
    
    private HeaderPanel headerPanel;
    private JPanel menuPanel;
    private JPanel bodyPanel;
    private JPanel footerPanel;
    private BufferedImage logoIcon;
    private BufferedImage menuIcons;
    private BufferedImage headerImage;
    private MenuButton homeButton;
    private MenuButton serversButton;
    private MenuButton templatesButton;
    private MenuButton pluginsButton;
    private MenuButton settingsButton;
    
    public Workbench()
    {
	loadImages();
	initSysTray();
	
	JPanel contentPane = new JPanel(new BorderLayout());
	this.setContentPane(contentPane);
	
	this.setMinimumSize(new Dimension(700, 450));
	this.setMaximumSize(this.getMinimumSize());
	this.setPreferredSize(this.getMaximumSize());
	this.setResizable(false);
	this.getContentPane().setBackground(new Color(230, 230, 230));
	this.setIconImage(logoIcon);
	
	/* HEADER */
	
	this.headerPanel = new HeaderPanel(headerImage);
	headerPanel.setBackground(new Color(0, 0, 0, 0));
	this.add(headerPanel, BorderLayout.NORTH);
	
	/* MENU */
	
	this.menuPanel = new JPanel();
	menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
	menuPanel.setBackground(new Color(0, 0, 0, 0));
	menuPanel.setPreferredSize(new Dimension(60, 300));
	this.add(menuPanel, BorderLayout.WEST);
	
	this.homeButton = new MenuButton(menuIcons, MenuEnum.HOME, 60, 60, 0, 0);
	homeButton.setHighlighted(true);
	homeButton.addMouseListener(this);
	menuPanel.add(homeButton);
	
	this.serversButton = new MenuButton(menuIcons, MenuEnum.SERVERS, 60, 60, 60, 0);
	serversButton.setHighlighted(false);
	serversButton.addMouseListener(this);
	menuPanel.add(serversButton);
	
	this.templatesButton = new MenuButton(menuIcons, MenuEnum.TEMPLATES, 60, 60, 120, 0);
	templatesButton.setHighlighted(false);
	templatesButton.addMouseListener(this);
	menuPanel.add(templatesButton);
	
	this.pluginsButton = new MenuButton(menuIcons, MenuEnum.PLUGINS, 60, 60, 180, 0);
	pluginsButton.setHighlighted(false);
	pluginsButton.addMouseListener(this);
	menuPanel.add(pluginsButton);
	
	this.settingsButton = new MenuButton(menuIcons, MenuEnum.SETTINGS, 60, 60, 240, 0);
	settingsButton.setHighlighted(false);
	settingsButton.addMouseListener(this);
	menuPanel.add(settingsButton);
	
	/* BODY */
	
	this.bodyPanel = new JPanel();
	bodyPanel.setBackground(new Color(0, 0, 0, 0));
	this.add(bodyPanel, BorderLayout.CENTER);
	
	/* FOOTER */
	
	this.footerPanel = new JPanel();
	footerPanel.setBackground(new Color(179, 179, 179));
	footerPanel.setPreferredSize(new Dimension(700, 40));
	this.add(footerPanel, BorderLayout.SOUTH);
	
	/* END */
	
	this.pack();
	this.setVisible(false);
	
	System.out.println(headerPanel.getSize());
    }
    
    private void loadImages()
    {
	try
	{
	    logoIcon = ImageIO
		    .read(Workbench.class
			    .getResource("/assets/images/AptiCraft-Server-Logo-64px.png"));
	    menuIcons = ImageIO.read(Workbench.class
		    .getResource("/assets/images/gui/menu/Icons.png"));
	    headerImage = ImageIO.read(Workbench.class
		    .getResource("/assets/images/gui/header/Header.png"));
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
    
    @Override
    public void mouseClicked(MouseEvent arg0)
    {
    }
    
    @Override
    public void mouseEntered(MouseEvent arg0)
    {
	if(arg0.getSource() != null)
	    if(arg0.getSource() instanceof MenuButton)
	    {
		MenuButton button = (MenuButton) arg0.getSource();
		button.setHovering(true);
	    }
    }
    
    @Override
    public void mouseExited(MouseEvent arg0)
    {
	if(arg0.getSource() != null)
	    if(arg0.getSource() instanceof MenuButton)
	    {
		MenuButton button = (MenuButton) arg0.getSource();
		button.setHovering(false);
	    }
    }
    
    @Override
    public void mousePressed(MouseEvent arg0)
    {
	if(arg0.getSource() != null)
	    if(arg0.getSource() instanceof MenuButton)
	    {
		MenuButton button = (MenuButton) arg0.getSource();
		button.setShrunk(true);
	    }
    }
    
    @Override
    public void mouseReleased(MouseEvent arg0)
    {
	if(arg0.getSource() != null)
	    if(Arrays.asList(menuPanel.getComponents()).contains(
		    arg0.getSource()))
		if(arg0.getSource() instanceof MenuButton)
		{
		    MenuButton button = (MenuButton) arg0.getSource();
		    button.setShrunk(false);
		    if(!button.isHighlighted())
		    {
			for(Component otherButton : menuPanel.getComponents())
			{
			    if(otherButton instanceof MenuButton)
				((MenuButton) otherButton)
					.setHighlighted(false);
			}
			button.setHighlighted(true);
			headerPanel.setMenuLabel(button.getEnumType());
		    }
		}
    }
    
}
