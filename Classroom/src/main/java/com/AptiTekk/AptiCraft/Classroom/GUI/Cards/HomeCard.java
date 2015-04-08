package com.AptiTekk.AptiCraft.Classroom.GUI.Cards;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.AptiTekk.AptiCraft.Classroom.GUI.FontLabel;
import com.AptiTekk.AptiCraft.Classroom.GUI.Workbench;

public class HomeCard extends CardPanel
{
    
    public HomeCard(Workbench workbench)
    {
        this.setLayout(new BorderLayout());
        
        JPanel gridPanel = new JPanel(new GridBagLayout());
        gridPanel.setBackground(getBackground());
        this.add(gridPanel, BorderLayout.NORTH);
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 1;
        JLabel classroomNameLabel = new FontLabel(vegurRegular, 24f, new Color(0, 120, 255), workbench.getClassroom().getPropertiesHandler().getClassroomName());
        classroomNameLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        gridPanel.add(classroomNameLabel, gbc);
        
        gbc.anchor = GridBagConstraints.CENTER;
        try
        {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 1;
            
            JPanel infoPanel = new JPanel(new GridLayout(2, 2));
            infoPanel.setBackground(getBackground());
            gridPanel.add(infoPanel, gbc);
            
            JLabel classroomAddrHeaderLabel = new FontLabel(vegurRegular, 18f, new Color(128, 128, 128), "Classroom Address:");
            classroomAddrHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoPanel.add(classroomAddrHeaderLabel);
            
            JLabel classroomPortHeaderLabel = new FontLabel(vegurRegular, 18f, new Color(128, 128, 128), "Classroom Port:");
            classroomPortHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoPanel.add(classroomPortHeaderLabel);
            
            JLabel classroomAddrLabel = new FontLabel(vegurLight, 36f, new Color(0, 120, 255), InetAddress.getLocalHost().getHostAddress());
            classroomAddrLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoPanel.add(classroomAddrLabel);
            
            JLabel classroomPortLabel = new FontLabel(vegurLight, 36f, new Color(0, 120, 255), "" + workbench.getClassroom().getPropertiesHandler().getClassroomPort());
            classroomPortLabel.setHorizontalAlignment(SwingConstants.CENTER);
            infoPanel.add(classroomPortLabel);
        }
        catch(UnknownHostException e)
        {
            e.printStackTrace();
        }
        
    }
    
}
