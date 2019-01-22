package com.game.baer;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpielerSprite extends Sprite 
{

	String dateiname;

	//--------------------------------------------------------------------------------------------------------------------------
	// Konstruktor:
		public SpielerSprite(Fenster f, Point p)
		{
			
			typ = "spieler";
			width = 64;
			height = 64;
			dateiname = "img/player.png";
			position = p;
			speed = 10;
			
			
			try 
			{
				charset=ImageIO.read(new File(dateiname));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}
		
	//--------------------------------------------------------------------------------------------------------------------------
}
