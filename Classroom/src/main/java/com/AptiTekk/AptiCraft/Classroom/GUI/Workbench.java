package com.AptiTekk.AptiCraft.Classroom.GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.AptiTekk.AptiCraft.Classroom.Classroom;
import com.AptiTekk.AptiCraft.Classroom.GUI.Cards.CardPanel;

@SuppressWarnings("serial")
public class Workbench extends JFrame
{
    
    private final Classroom classroom;
    private HeaderPanel headerPanel;
    private JPanel menuPanel;
    private JPanel bodyPanel;
    private BufferedImage logoIcon;
    private BufferedImage menuIcons;
    private BufferedImage headerImage;
    
    private static Font vegurRegular;
    private static Font vegurLight;
    
    public Workbench(final Classroom classroom, boolean openAutomatically)
    {
	this.classroom = classroom;
	
	loadImages();
	loadFonts();
	
	this.setLayout(new BorderLayout());
	
	this.setSize(new Dimension(800, 525));
	
	this.setResizable(false);
	this.getContentPane().setBackground(Color.WHITE);
	this.setIconImage(logoIcon);
	
	/* HEADER */
	
	this.headerPanel = new HeaderPanel(headerImage);
	headerPanel.setBackground(new Color(235, 235, 235));
	this.add(headerPanel, BorderLayout.NORTH);
	
	/* BODY */ // Must be initialized before Menu
        
        this.bodyPanel = new JPanel();
        bodyPanel.setLayout(new CardLayout());
        bodyPanel.setBackground(Color.WHITE);
        this.add(bodyPanel, BorderLayout.CENTER);
	
	/* MENU */
	
	this.menuPanel = new MenuPanel(this, menuIcons);
	this.add(menuPanel, BorderLayout.WEST);
	
	
	/* END */
	
	this.pack();
	menuPanel.validate();
	
	this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	
	addWindowListener(new WindowAdapter()
	{
	    @Override
	    public void windowClosing(WindowEvent e)
	    {
		closeWorkbench();
	    }
	});
	
	if(openAutomatically)
	    this.showWorkbench();
	else
	    this.setVisible(false);
    }
    
    private void loadImages()
    {
	try
	{
	    logoIcon = classroom.logoIcon;
	    menuIcons = ImageIO.read(Workbench.class
		    .getResource("/images/gui/menu/Icons.png"));
	    headerImage = ImageIO.read(Workbench.class
		    .getResource("/images/gui/header/Header.png"));
	}
	catch(IOException e)
	{
	    e.printStackTrace();
	}
    }
    
    private void loadFonts()
    {
        try
        {
            vegurRegular = Font.createFont(Font.PLAIN, getClass().getResourceAsStream("/fonts/Vegur-Regular.ttf")).deriveFont(20f);
            vegurLight = Font.createFont(Font.PLAIN, getClass().getResourceAsStream("/fonts/Vegur-Light.ttf")).deriveFont(20f);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(FontFormatException e)
        {
            e.printStackTrace();
        }
    }
    
    public void showWorkbench()
    {
	this.classroom.logVerbose("Opening Workbench...");
	this.setLocationRelativeTo(null);
	this.setVisible(true);
    }
    
    public void closeWorkbench()
    {
	classroom.logVerbose("Closing Workbench...");
	setVisible(false);
	dispose();
    }
    
    public static Font getVegurRegular()
    {
        return vegurRegular;
    }
    
    public static Font getVegurLight()
    {
        return vegurLight;
    }

    public void addCard(CardPanel card)
    {
        this.bodyPanel.add(card, card.getCardName());
    }
    
    public void setCard(CardPanel card)
    {
        ((CardLayout) bodyPanel.getLayout()).show(bodyPanel, card.getCardName());
    }

    public Classroom getClassroom()
    {
        return this.classroom;
    }
    
}
