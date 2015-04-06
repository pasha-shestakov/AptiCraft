package com.AptiTekk.AptiCraft.Classroom.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.border.DropShadowBorder;

public class HeaderPanel extends JXPanel
{
    
    private BufferedImage headerImage;
    private ImageIcon logoIcon;
    
    public HeaderPanel(BufferedImage headerImage)
    {
	this.headerImage = headerImage;
	
	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
	DropShadowBorder shadow = new DropShadowBorder();
	
	shadow.setShadowColor(Color.BLACK);
	shadow.setShadowSize(10);
	shadow.setShowLeftShadow(false);
	shadow.setShowRightShadow(false);
	shadow.setShowTopShadow(false);
	shadow.setShowBottomShadow(true);
	
	this.setBorder(shadow);
	
	this.logoIcon = new ImageIcon(headerImage.getSubimage(0, 0, 694, 50));
	
	JLabel logoLabel = new JLabel();
	logoLabel.setIcon(logoIcon);
	this.add(logoLabel);
    }
    
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(800, 75);
    }
    
}
