package com.game.baer;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MapGen 
{
	int x,y;
	int c;
	int  red  ; 
	int  green ;
	int  blue  ;
	int i = 0;
	int j = 0;
	int k = 0;
	int raster_x, raster_y;
	int spx,spy;
	
	boolean spieler_gesetzt = false;
	
	BufferedImage map;
	
	Fenster fenster;
	public MapGen(Fenster f)
	{
		fenster = f;
		try 
		{
			map = ImageIO.read(new File("img/map.png"));
		} 
		catch (IOException fehler) 
		{
			fehler.printStackTrace();
		}
		
		raster_x = 64; 
		raster_y = 64;
		
		for (x=0;x<map.getWidth();x++)
		{
			for (y=0;y<map.getHeight();y++)
			{
				c = map.getRGB(x, y);
				red   = (c & 0x00ff0000) >> 16;
				green = (c & 0x0000ff00) >> 8;
				blue  =  c & 0x000000ff;
				
				
				if (blue < 10 && red > 200 && green > 200)
				{
					f.item_obj.add(new ItemSprite(f,new Point(raster_x*x,raster_y*y)));
					f.sprites.add(f.item_obj.get(i));
					i+=1;
				}
				if (blue < 10 && red < 10 && green < 10)
				{
					f.erde_obj.add(new ErdeSprite(f,new Point(raster_x*x,raster_y*y)));
					f.sprites.add(f.erde_obj.get(j));
					j+=1;
				}
				if (blue < 10 && red < 10 && green > 200)
				{
					if (spieler_gesetzt == false)
					{
						spieler_gesetzt = true;
						f.spieler_obj = new SpielerSprite(f,new Point(raster_x*x,raster_y*y));
						f.sprites.add(f.spieler_obj);
						spx = raster_x*x;
						spy = raster_y*y;
					}
				}
				
			}
	
		}
		//-------------------------------------------------------------------------
		//Anzeigeelemente:
			f.counter_anz_obj.add(new CounterSprite(f,new Point(600,20)));
			f.sprites.add(f.counter_anz_obj.get(k));
			k++;
			f.counter_anz_obj.add(new CounterSprite(f,new Point(664,20)));
			f.sprites.add(f.counter_anz_obj.get(k));
			k++;
		
		System.out.print(spx);
		//Buffer einmalig setzen:
		fenster.collision_buffer.clear();
		
		fenster.collision_buffer.add(fenster.spieler_obj); // Spieler zuerst hinzufügen
		
		for(Sprite s: fenster.counter_anz_obj)
		{
			fenster.collision_buffer.add(s); 
		}
		
		for(Sprite s: fenster.sprites)
		{
			if (s.position.x > spx-1000 && s.position.x < spx+1000 && s.typ != "spieler" && s.typ != "counter")
			{
				fenster.collision_buffer.add(s);
				i++;
			}
		}
		//--------------------------------------------------------------------------------------
	}
	public void generate()
	{
		
	}
}
