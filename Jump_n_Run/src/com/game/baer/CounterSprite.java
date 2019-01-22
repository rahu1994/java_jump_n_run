package com.game.baer;

import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CounterSprite extends Sprite
{
	
//--------------------------------------------------------------------------------------------------------------------------
// Konstruktor:
	public CounterSprite(Fenster f, Point p)
	{
		typ = "counter";
		width = 64;
		height = 64;
		dateiname = "img/0.png";
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
	@Override
	public void setImage(int zahl)
	{
		switch(zahl)
		{
		case 0:
			dateiname = "img/0.png";
			break;
		case 1:
			dateiname = "img/1.png";
			break;
		case 2:
			dateiname = "img/2.png";
			break;
		case 3:
			dateiname = "img/3.png";
			break;
		case 4:
			dateiname = "img/4.png";
			break;
		case 5:
			dateiname = "img/5.png";
			break;
		case 6:
			dateiname = "img/6.png";
			break;
		case 7:
			dateiname = "img/7.png";
			break;
		case 8:
			dateiname = "img/8.png";
			break;
		case 9:
			dateiname = "img/9.png";
			break;
		}
		
		try 
		{
			charset=ImageIO.read(new File(dateiname));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
