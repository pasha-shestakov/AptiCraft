package com.AptiTekk.AptiCraft.Classroom.Logging;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.AptiTekk.AptiCraft.Classroom.Utilities;

public class LoggingHandler
{
    
    private Logger logger;
    
    public LoggingHandler()
    {
	this.logger = Logger.getLogger("AptiCraft Classroom");
	
	try
	{
	    String timeStamp = new SimpleDateFormat().format(new Date());
	    timeStamp = timeStamp.replaceAll(" ", "_").replaceAll("/", "-")
		    .replaceAll(":", "-");
	    if(!new File(Utilities.getRootDirectory(), "logs").exists())
		new File(Utilities.getRootDirectory(), "logs").mkdir();
	    else
	    {
		File[] unsortedFiles = new File(Utilities.getRootDirectory(),
			"logs").listFiles();
		if(unsortedFiles.length > 8)
		{
		    File[] sortedFiles = new File[unsortedFiles.length];
		    LinkedList<File> unsortedFilesList = new LinkedList<File>(Arrays.asList(unsortedFiles));
		    int lastModifiedFile = 0;
		    int i = 0;
		    while(unsortedFilesList.size() > 0)
		    {
			for(int j = 0; j < unsortedFilesList.size(); j++)
			{
			    File f = unsortedFilesList.get(j);
				if(f.lastModified() > unsortedFilesList.get(lastModifiedFile
					).lastModified())
				{
				    lastModifiedFile = j;
				}
			}
			sortedFiles[i] = unsortedFilesList.get(lastModifiedFile);
			unsortedFilesList.remove(lastModifiedFile);
			lastModifiedFile = 0;
			i++;
		    }
		    
		    for(i = 0; i < sortedFiles.length - 8; i++)
		    {
			sortedFiles[sortedFiles.length - 1 - i].delete();
		    }
		}
	    }
	    FileHandler handler = new FileHandler("logs/" + timeStamp
		    + "_%u_%g.log", 30000, 4, false);
	    handler.setFormatter(new SingleLineFormatter());
	    logger.addHandler(handler);
	    ConsoleHandler handler2 = new ConsoleHandler();
	    handler2.setFormatter(new SingleLineFormatter());
	    logger.addHandler(handler2);
	    logger.setUseParentHandlers(false);
	}
	catch(IOException e)
	{
	    logMessage("Failed to initialize logger handler.", Level.SEVERE);
	    logMessage("Exception:\n" + e.getMessage(), Level.SEVERE);
	}
	
	logMessage("Logger Initialized.", Level.INFO);
    }
    
    public void setLogLevel(Level level)
    {
	this.logger.setLevel(level);
	for(Handler handler : this.logger.getHandlers())
	    handler.setLevel(level);
    }
    
    public void logMessage(String message, Level logType)
    {
	if(logType.equals(Level.FINE))
	    this.logger.fine(message);
	else if(logType.equals(Level.INFO))
	    this.logger.info(message);
	else if(logType.equals(Level.WARNING))
	    this.logger.warning(message);
	else if(logType.equals(Level.SEVERE))
	    this.logger.severe(message);
    }
    
}
