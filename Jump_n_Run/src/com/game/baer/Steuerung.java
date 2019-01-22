package com.game.baer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
 
public class Steuerung implements KeyListener{
	
	//--------------------------------------------------------------------------------------------------------------------------
	//Variablen:
		boolean bewegung = false;
		
		//Tasten Bools
		public boolean hoch		=	false;
		public boolean runter	=	false;
		public boolean links	=	false;
		public boolean rechts	=	false;
		
		public boolean jump	    =	false;
 
	//--------------------------------------------------------------------------------------------------------------------------
	//Tasten gedrückt:	
		@Override
		public void keyPressed(KeyEvent e) 
		{
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					jump=true;
					break;
				case KeyEvent.VK_DOWN:
					runter=true;
					break;
				case KeyEvent.VK_LEFT:
					links=true;
					break;
				case KeyEvent.VK_RIGHT:
					rechts=true;
					break;
			}
		}
	
	//--------------------------------------------------------------------------------------------------------------------------
	//Tasten losgelassen:	
		@Override
		public void keyReleased(KeyEvent e) 
		{
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_UP:
					jump=false;
					break;
				case KeyEvent.VK_DOWN:
					runter=false;
					break;
				case KeyEvent.VK_LEFT:
					links=false;
					break;
				case KeyEvent.VK_RIGHT:
					rechts=false;
					break;
			}
		}
	//--------------------------------------------------------------------------------------------------------------------------
	//Tasten getippt:
		@Override
		public void keyTyped(KeyEvent e) 
		{
			
		}
	
	//--------------------------------------------------------------------------------------------------------------------------
}
