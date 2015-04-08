package com.AptiTekk.AptiCraft.Classroom.GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MenuButton extends JLabel
{
    private BufferedImage menuIcons;
    private ImageIcon enabledIcon;
    private ImageIcon shrunk_enabledIcon;
    private ImageIcon disabledIcon;
    private boolean highlighted = false;
    private boolean hovering = false;
    private boolean shrunk = false;
    private int w;
    private int h;
    private int x;
    private int y;
    
    public MenuButton(BufferedImage menuIcons, int w, int h,
	    int x, int y)
    {
	this.menuIcons = menuIcons;
	this.w = w;
	this.h = h;
	this.x = x;
	this.y = y;
	
	this.enabledIcon = new ImageIcon(menuIcons.getSubimage(x, y + h, w, h));
	this.shrunk_enabledIcon = new ImageIcon(menuIcons.getSubimage(x, y + h
		+ h, w, h));
	this.disabledIcon = new ImageIcon(menuIcons.getSubimage(x, y, w, h));
	
	this.setPreferredSize(new Dimension(w, h));
	this.setHighlighted(false);
    }
    
    public void setHighlighted(boolean highlighted)
    {
	this.setIcon((highlighted) ? enabledIcon : disabledIcon);
	this.highlighted = highlighted;
    }
    
    public boolean isHighlighted()
    {
	return this.highlighted;
    }
    
    public void setHovering(boolean hovering)
    {
	this.setIcon((highlighted || hovering) ? enabledIcon : disabledIcon);
	this.hovering = hovering;
    }
    
    public boolean isHovering()
    {
	return this.hovering;
    }
    
    public void setShrunk(boolean shrunk)
    {
	this.setIcon((shrunk) ? shrunk_enabledIcon
		: ((highlighted || hovering) ? enabledIcon : disabledIcon));
	this.shrunk = shrunk;
    }
    
    public boolean isShrunk()
    {
	return this.shrunk;
    }
    
    @Override
    public boolean equals(Object other)
    {
	if(other == null)
	    return false;
	if(!(other instanceof MenuButton))
	    return false;
	
	MenuButton otherButton = (MenuButton) other;
	
	if(otherButton.isHighlighted() != this.isHighlighted())
	    return false;
	if(otherButton.isHovering() != this.isHovering())
	    return false;
	if(otherButton.isShrunk() != this.isShrunk())
	    return false;
	
	return super.equals(other);
    }
    
}
