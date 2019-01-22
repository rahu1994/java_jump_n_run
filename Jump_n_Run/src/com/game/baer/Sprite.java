package com.game.baer;

import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class Sprite 
{
	//--------------------------------------------------------------------------------------------------------------------------
	//Variablen für Animation:
		String dateiname;
		BufferedImage charset;
		int subimg_anz = 1;     
		int speed;
		int z = 0;	
		int aktu_bild = 0;
		int anim_mode = 0;
		boolean alive = true;
	
	//--------------------------------------------------------------------------------------------------------------------------
	//Variablen für Kollision etc.:
		Point position;//hier ist es in der map
		String typ;
		int height,width;	

	//--------------------------------------------------------------------------------------------------------------------------
	// Bild Abholen:
		public BufferedImage getImage() 
		{
			
			if (alive == true)
			{
			
				BufferedImage b;
				
				b = charset.getSubimage(width*aktu_bild,anim_mode*height,width,height);	
				
				if ((z % speed) == 0)
				{
					aktu_bild++;   //nur bei jedem "speedsten" Frame nächstes Bild
				}
				
				subimg_anz = charset.getWidth() / width; // Anzahl der Subimages
				
				if (aktu_bild == subimg_anz)
				{
					aktu_bild = 0;
					z = 0;
				}
				z++;
				
				return b;
			}
			else
			{
				return null;
			}
		}
	//--------------------------------------------------------------------------------------------------------------------------
	public void setImage(int zahl)
	{
		
	}
}
