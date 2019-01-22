package com.game.baer;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ErdeSprite extends Sprite
{
	
//--------------------------------------------------------------------------------------------------------------------------
// Konstruktor:
	public ErdeSprite(Fenster f, Point p)
	{
		typ = "erde";
		width = 64;
		height = 64;
		dateiname = "img/erde.png";
		position = p;
		speed = 20;
		
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
