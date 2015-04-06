package com.AptiTekk.AptiCraft.Classroom.GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuPanel extends JPanel implements MouseListener
{
    
    private ArrayList<MenuButton> menuButtons = new ArrayList<MenuButton>();
    
    private int borderHeight;
    private BufferedImage menuIcons;
    
    public MenuPanel(BufferedImage menuIcons)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(235, 235, 235));
        
        this.menuIcons = menuIcons;
        this.borderHeight = 20;
        
        setBorder(new EmptyBorder(borderHeight, 15, 0, 0));
        
        addButton(MenuEnum.HOME, 60, 60, 0, 0);
        addButton(MenuEnum.SERVERS, 60, 60, 60, 0);
        addButton(MenuEnum.TEMPLATES, 60, 60, 120, 0);
        addButton(MenuEnum.PLUGINS, 60, 60, 180, 0);
        addButton(MenuEnum.SETTINGS, 60, 60, 240, 0);
        
        clearButtons();
        menuButtons.get(0).setHighlighted(true);
    }
    
    private void addButton(MenuEnum enom, int w, int h, int x, int y)
    {
        MenuButton button = new MenuButton(menuIcons, enom, w, h, x, y);
        button.addMouseListener(this);
        button.setBorder(new EmptyBorder(0,0,borderHeight,0));
        this.add(button);
        menuButtons.add(button);
    }
    
    @Override
    public void validate()
    {
        this.borderHeight = (this.getHeight() - (5 * 60)) / 6;
        this.setBorder(new EmptyBorder(borderHeight, 15, 0, 0));
        for(MenuButton button : menuButtons)
        {
            button.setBorder(new EmptyBorder(0, 0, borderHeight, 0));
        }
        super.validate();
    }
    
    private void clearButtons()
    {
        for(MenuButton button : menuButtons)
        {
            button.setHighlighted(false);
            button.setShrunk(false);
            button.setHovering(false);
        }
    }
    
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(90, 400);
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
        {
            MenuButton button = (MenuButton) arg0.getSource();
            button.setShrunk(false);
            if(!button.isHighlighted())
            {
                clearButtons();
                button.setHighlighted(true);
            }
        }
    }
}
