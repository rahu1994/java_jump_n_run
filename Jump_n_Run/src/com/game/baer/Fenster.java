package com.game.baer;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Fenster extends JFrame 
{

	private static final long serialVersionUID = -8798796965859194871L;
	
	//--------------------------------------------------------------------------------------------------------------------------
	//Variablen:
		Steuerung steuerung;
	
		//Objectliste
		Sprite spieler_obj;
		ArrayList<Sprite> counter_anz_obj = new ArrayList<Sprite>();
		ArrayList<Sprite> erde_obj = new ArrayList<Sprite>();
		ArrayList<Sprite> item_obj = new ArrayList<Sprite>();
		//Spielvariablen:
		int muenz_anz;
		
		
		SpielAnsicht ansicht;
		SpielSchleife schleife;
		
		Thread zeichenThread;
		Thread schleifenThread;
		
		int i;
		
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		ArrayList<Sprite> collision_buffer = new ArrayList<Sprite>();
	
	//--------------------------------------------------------------------------------------------------------------------------
	//Konstruktor:	
		public Fenster()
		{
			
			steuerung = new Steuerung();
			this.addKeyListener(steuerung);
			
			this.setSize(1024, 768);
			this.setTitle("Super Bär");
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			Color c = new Color(40,255,150);
			Container con = this.getContentPane();
			con.setBackground(c); 
			
			MapGen m = new MapGen(this);
			m.generate();
			
			ansicht = new SpielAnsicht(this); //Malfläche erzeugen, auf die der Spieler gez. wird
			
			schleife = new SpielSchleife(this);
			
			this.add(ansicht);
			
			zeichenThread=new Thread(ansicht);
			schleifenThread=new Thread(schleife);
			zeichenThread.start();
			schleifenThread.start();	
		}
	//--------------------------------------------------------------------------------------------------------------------------	
		
}
