package com.AptiTekk.AptiCraft.Classroom.GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import org.jdesktop.swingx.JXPanel;

public class HeaderPanel extends JXPanel
{
    
    public HeaderPanel(BufferedImage headerImage)
    {
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
	JLabel logoLabel = new JLabel();
	logoLabel.setIcon(new ImageIcon(headerImage));
	this.add(logoLabel);
    }
    
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(800, 84);
    }
    
}
