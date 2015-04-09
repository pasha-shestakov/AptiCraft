package com.AptiTekk.AptiCraft.Classroom.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.AptiTekk.AptiCraft.Classroom.GUI.Cards.CardPanel;
import com.AptiTekk.AptiCraft.Classroom.GUI.Cards.HomeCard;
import com.AptiTekk.AptiCraft.Classroom.GUI.Cards.PluginsCard;
import com.AptiTekk.AptiCraft.Classroom.GUI.Cards.SessionsCard;
import com.AptiTekk.AptiCraft.Classroom.GUI.Cards.SettingsCard;
import com.AptiTekk.AptiCraft.Classroom.GUI.Cards.TemplatesCard;

public class MenuPanel extends JPanel implements MouseListener
{
    
    private HashMap<MenuButton, CardPanel> menuButtonsMap = new HashMap<MenuButton, CardPanel>();
    
    private int borderHeight;
    private BufferedImage menuIcons;

    private Workbench workbench;
    
    public MenuPanel(Workbench workbench, BufferedImage menuIcons)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(235, 235, 235));
        
        this.workbench = workbench;
        this.menuIcons = menuIcons;
        this.borderHeight = 20;
        
        setBorder(new EmptyBorder(borderHeight, 15, 0, 0));
        
        addButton(new HomeCard(workbench), 60, 60, 0, 0);
        addButton(new SessionsCard(workbench), 60, 60, 60, 0);
        addButton(new TemplatesCard(workbench), 60, 60, 120, 0);
        addButton(new PluginsCard(workbench), 60, 60, 180, 0);
        addButton(new SettingsCard(workbench), 60, 60, 240, 0);
    }
    
    private void addButton(CardPanel card, int w, int h, int x, int y)
    {
        MenuButton button = new MenuButton(menuIcons, w, h, x, y);
        button.addMouseListener(this);
        button.setBorder(new EmptyBorder(0,0,borderHeight,0));
        this.add(button);
        workbench.addCard(card);
        
        if(menuButtonsMap.isEmpty())
        {
            workbench.setCard(card);
            button.setHighlighted(true);
        }
        menuButtonsMap.put(button, card);
    }
    
    @Override
    public void validate()
    {
        this.borderHeight = (this.getHeight() + 14 - (5 * 60)) / 6;
        this.setBorder(new EmptyBorder(borderHeight - 14, 15, 0, 0));
        for(MenuButton button : menuButtonsMap.keySet())
        {
            button.setBorder(new EmptyBorder(0, 0, borderHeight, 0));
        }
        super.validate();
    }
    
    private void clearButtons()
    {
        for(MenuButton button : menuButtonsMap.keySet())
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
                workbench.setCard(menuButtonsMap.get(button));
            }
        }
    }
}
