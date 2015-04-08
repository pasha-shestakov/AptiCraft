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
        JLabel classroomNameLabel = new JLabel(workbench.getClassroom().getPropertiesHandler().getClassroomName());
        classroomNameLabel.setFont(vegurRegular.deriveFont(24f));
        classroomNameLabel.setForeground(new Color(0,120,255));
        classroomNameLabel.setBorder(new EmptyBorder(0,20,0,0));
        gridPanel.add(classroomNameLabel, gbc);
        
        gbc.anchor = GridBagConstraints.CENTER;
        try
        {
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridx = 0;
            gbc.gridy = 1;
            
            JPanel infoPanel = new JPanel(new GridLayout(2,2));
            infoPanel.setBackground(getBackground());
            gridPanel.add(infoPanel, gbc);
            
            JLabel classroomAddrHeaderLabel = new JLabel("Classroom Address:");
            classroomAddrHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
            classroomAddrHeaderLabel.setFont(vegurRegular.deriveFont(18f));
            classroomAddrHeaderLabel.setForeground(new Color(128,128,128));
            infoPanel.add(classroomAddrHeaderLabel);
            
            JLabel classroomPortHeaderLabel = new JLabel("Classroom Port:");
            classroomPortHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
            classroomPortHeaderLabel.setFont(vegurRegular.deriveFont(18f));
            classroomPortHeaderLabel.setForeground(new Color(128,128,128));
            infoPanel.add(classroomPortHeaderLabel);
            
            JLabel classroomAddrLabel = new JLabel(InetAddress.getLocalHost().getHostAddress());
            classroomAddrLabel.setHorizontalAlignment(SwingConstants.CENTER);
            classroomAddrLabel.setFont(vegurLight.deriveFont(36f));
            classroomAddrLabel.setForeground(new Color(0,120,255));
            infoPanel.add(classroomAddrLabel);
            
            JLabel classroomPortLabel = new JLabel(""+workbench.getClassroom().getPropertiesHandler().getClassroomPort());
            classroomPortLabel.setHorizontalAlignment(SwingConstants.CENTER);
            classroomPortLabel.setFont(vegurLight.deriveFont(36f));
            classroomPortLabel.setForeground(new Color(0,120,255));
            infoPanel.add(classroomPortLabel);
        }
        catch(UnknownHostException e)
        {
            e.printStackTrace();
        }
        
    }
    
}
