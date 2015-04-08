package com.AptiTekk.AptiCraft.Classroom.GUI.Cards;

import java.awt.Color;
import java.awt.Font;
import java.util.UUID;

import javax.swing.JPanel;

import com.AptiTekk.AptiCraft.Classroom.GUI.Workbench;

public class CardPanel extends JPanel
{
    protected static final Font vegurRegular = Workbench.getVegurRegular();
    protected static final Font vegurLight = Workbench.getVegurLight();

    private final String cardName;
    
    public CardPanel()
    {
        this.cardName = UUID.randomUUID().toString();
    }
    
    public String getCardName()
    {
        return this.cardName;
    }
    
    @Override
    public Color getBackground()
    {
        return Color.WHITE;
    }
    
}
