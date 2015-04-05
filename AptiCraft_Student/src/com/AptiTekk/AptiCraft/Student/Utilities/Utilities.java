package com.AptiTekk.AptiCraft.Student.Utilities;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.io.FileUtils;

public class Utilities
{
    
    private static String OS = System.getProperty("os.name").toLowerCase();
    
    /**
     * Determines if the current Operating System is Windows
     * 
     * @return True if Windows.
     */
    public static boolean isWindows()
    {
	
	return(OS.indexOf("win") >= 0);
	
    }
    
    /**
     * Determines if the current Operating System is Mac
     * 
     * @return True if Mac.
     */
    public static boolean isMac()
    {
	
	return(OS.indexOf("mac") >= 0);
	
    }
    
    /**
     * Determines if the current Operating System is Unix
     * 
     * @return True if Unix.
     */
    public static boolean isUnix()
    {
	
	return(OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS
		.indexOf("aix") > 0);
	
    }
    
    /**
     * Determines if the current Operating System is Solaris
     * 
     * @return True if Solaris.
     */
    public static boolean isSolaris()
    {
	
	return(OS.indexOf("sunos") >= 0);
	
    }
    
    /**
     * Compares two files to see if they are the same.
     * 
     * @param f1
     *            File 1
     * @param f2
     *            File 2
     * @return True if files are the same.
     */
    public static boolean compareFiles(File f1, File f2)
    {
	
	boolean same = false;
	try
	{
	    same = FileUtils.contentEquals(f1, f2);
	}
	catch(IOException e)
	{
	    e.printStackTrace();
	}
	
	return same;
    }
    
    /**
     * Gets the root directory of the running jar.
     * 
     * @return File of root directory of running jar.
     */
    public static File getRootDirectory()
    {
	try
	{
	    File root = new File(Utilities.class.getProtectionDomain()
		    .getCodeSource().getLocation().toURI().getPath())
		    .getParentFile().getAbsoluteFile();
	    return root;
	}
	catch(URISyntaxException e)
	{
	    return null;
	}
    }
    
}
