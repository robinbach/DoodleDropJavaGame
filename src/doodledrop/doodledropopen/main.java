package doodledrop.doodledropopen;

import doodledrop.doodledropopen.opening;
import doodledrop.doodledropopen.ending;


import javax.swing.*;

import java.awt.*;


public class main {
	public static opening win;

	  public static void main( String[] args )
	  {
	    win = new opening();
	    
	    win.setMinimumSize(new Dimension(600, 600));
	    win.pack();
	    win.setVisible(true);
	    win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    
	    
	    
	  }
}
