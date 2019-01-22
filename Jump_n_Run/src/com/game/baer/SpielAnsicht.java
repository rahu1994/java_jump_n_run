package com.game.baer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class SpielAnsicht extends JPanel implements Runnable
{
	
	//--------------------------------------------------------------------------------------------------------------------------
	// Variablen:
		private static final long serialVersionUID = 4904485127755415691L;
		
		Fenster fenster;
		
		Rectangle view = new Rectangle(800,600); // Fenster im Spiel - Skizze: [..Spiel..[view]....Spiel...]
		Point rel_pos; // wird aus Positione des sprites mit view errechnet
		
		final int MAX_GAME_SPEED = 5;

		float einer;
		float zehner;
	
	//--------------------------------------------------------------------------------------------------------------------------
	//Konstruktor:	
		public SpielAnsicht(Fenster f)
		{
			//Fenster zuweisen
			fenster = f;
			
			setPreferredSize(new Dimension(f.getWidth(),f.getWidth()));
			setDoubleBuffered(true);
			this.setOpaque(false); //transparenter Hintergrund (und keine Streifen)
		}
	
	//--------------------------------------------------------------------------------------------------------------------------
	//Repaint-Aufruf des JPanels
		public void paintComponent(Graphics g)
		{
			zeichneSprites(g);
		}
	
	//--------------------------------------------------------------------------------------------------------------------------
	//Zeichne Sprites aus der "Sprite-Liste":
		public void zeichneSprites(Graphics g)
		{
			synchronized(fenster.collision_buffer)// Sorgt dafür, dass nicht 2 Threads auf das selbe Objekt gleichzeitig zugreifen
			{
				for (Sprite s : fenster.collision_buffer)
				{
					if (s.typ == "spieler")
					{
						view.x = s.position.x-300; //view links ist der linke bildschirmrand 300px links vom spieler
						view.y = s.position.y-400;
						rel_pos = new Point(s.position.x-view.x,s.position.y-view.y); //relative position zum zeichen aus absoluten koords herausfinden
						g.drawImage(s.getImage(),rel_pos.x, rel_pos.y, this);
					}
					else if (s.typ == "counter")
					{
						
						if (s.position.x < 620)
						{
							zehner = Math.round(((fenster.muenz_anz/10) - 0.5f));
							s.setImage((int)zehner);
							
						}
						else 
						{
							einer = fenster.muenz_anz - (zehner*10);
							s.setImage((int)einer);
						}
						g.drawImage(s.getImage(),s.position.x, s.position.y, this); //Position des Objekts absolut, da Anzeigeobj. => fest auf bildschirm
					}
					else
					{
						rel_pos = new Point(s.position.x-view.x,s.position.y-view.y); //relative position zum zeichen aus absoluten koords herausfinden
						g.drawImage(s.getImage(),rel_pos.x, rel_pos.y, this);
					
					}
				}
				
			}
			
		}
		
	//--------------------------------------------------------------------------------------------------------------------------
	//Repaint und Frameberechnung
	@Override
	public void run() 
	{
		while(true)
		{
			float START = System.currentTimeMillis();
			
			//--REPAINT--
				repaint();
			//-----------
				
			float AUSFUEHR = System.currentTimeMillis()-START;
			if(MAX_GAME_SPEED>AUSFUEHR)
			{
				try 
				{
					
					Thread.sleep(MAX_GAME_SPEED-(int)AUSFUEHR);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}
