package com.AptiTekk.AptiCraft.Classroom.GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class FontLabel extends JLabel
{
    
    public FontLabel(Font font, float fontSize, Color fontColor, String text)
    {
        super(text);
        this.setFont(font.deriveFont(fontSize));
        this.setForeground(fontColor);
        this.setBorder(new EmptyBorder(-5,0,-5,0));
    }
    
}
