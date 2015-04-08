package com.AptiTekk.AptiCraft.Classroom.GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class FontLabel extends JLabel
{
    
    public FontLabel(Font font, float fontSize, Color fontColor, String text)
    {
        super(text);
        this.setFont(font.deriveFont(fontSize));
        this.setForeground(fontColor);
    }
    
}
