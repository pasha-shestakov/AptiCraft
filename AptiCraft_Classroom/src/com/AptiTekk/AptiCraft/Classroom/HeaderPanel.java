package com.AptiTekk.AptiCraft.Classroom;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel
{
    
    private BufferedImage headerImage;
    private ImageIcon logoIcon;
    private ImageIcon homeIcon;
    private ImageIcon serversIcon;
    private ImageIcon templatesIcon;
    private ImageIcon pluginsIcon;
    private ImageIcon settingsIcon;
    private JLabel menuLabel;
    
    public HeaderPanel(BufferedImage headerImage)
    {
	this.headerImage = headerImage;
	
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	this.setPreferredSize(new Dimension(694, 80));
	
	this.logoIcon = new ImageIcon(headerImage.getSubimage(0, 0, 694, 50));
	this.homeIcon = new ImageIcon(headerImage.getSubimage(0, 50, 694, 30));
	this.serversIcon = new ImageIcon(
		headerImage.getSubimage(0, 80, 694, 30));
	this.templatesIcon = new ImageIcon(headerImage.getSubimage(0, 110, 694,
		30));
	this.pluginsIcon = new ImageIcon(
		headerImage.getSubimage(0, 140, 694, 30));
	this.settingsIcon = new ImageIcon(headerImage.getSubimage(0, 170, 694,
		30));
	
	JLabel logoLabel = new JLabel();
	logoLabel.setIcon(logoIcon);
	this.add(logoLabel);
	
	this.menuLabel = new JLabel();
	menuLabel.setIcon(homeIcon);
	this.add(menuLabel);
    }
    
    public void setMenuLabel(MenuEnum menuEnum)
    {
	switch(menuEnum)
	{
	    case HOME:
		menuLabel.setIcon(homeIcon);
		break;
	    case SERVERS:
		menuLabel.setIcon(serversIcon);
		break;
	    case TEMPLATES:
		menuLabel.setIcon(templatesIcon);
		break;
	    case PLUGINS:
		menuLabel.setIcon(pluginsIcon);
		break;
	    case SETTINGS:
		menuLabel.setIcon(settingsIcon);
		break;
	    default:
		break;
	}
    }
    
}
