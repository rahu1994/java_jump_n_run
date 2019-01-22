package com.game.baer;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ItemSprite extends Sprite 
{
	//--------------------------------------------------------------------------------------------------------------------------
	//Konstruktor:	
		public ItemSprite(Fenster f, Point p)
		{
			typ = "item";
			width = 20;
			height = 20;
			dateiname = "img/coin.png";
			position = p;
			speed = 1;
			
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
