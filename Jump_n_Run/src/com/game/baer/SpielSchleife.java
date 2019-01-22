package com.game.baer;


public class SpielSchleife implements Runnable
{
	//--------------------------------------------------------------------------------------------------------------------------
	
	int MAX_GAME_SPEED = 5;
	float fall_speed = 0;
	Fenster fenster;
	Steuerung steuerung;

	Sprite spieler;
	Sprite coll_item;
	
	boolean cont = false;
	
	AudioPlayer ap = new AudioPlayer();
	
	int time_count; //für die 2 Sekundenaktualisierung
	int i;
	
	int muenz_anz;
	
	//--------------------------------------------------------------------------------------------------------------------------	
	//Konstruktor
	public SpielSchleife(Fenster f)
	{
		fenster = f;
		steuerung = fenster.steuerung;
		
		//Objekte zum verändern zuweisen
		spieler = fenster.spieler_obj;
	}
	
	//--------------------------------------------------------------------------------------------------------------------------
	//Kollisionserkennung
	
	public Sprite checkcollission(int x,int y, int w, int h, String ty)
	{
		int l1 = x;
		int t1 = y;
		int r1 = x + w;
		int b1 = y + h;
		
		Sprite ret = null;
		if (ty == "erde")
		{
			for(Sprite s: fenster.erde_obj)
			{
				int l2 = s.position.x;
				int t2 = s.position.y;
				int r2 = s.position.x + s.width;
				int b2 = s.position.y + s.height;
				
				if (s.typ == ty)
				{
					if (b1<t2)
					{
					}
					else if (t1>b2)
					{
					}
					else if (r1<l2)
					{
					}
					else if (l1>r2)
					{
					}
					else
					{
						ret = s;
						break;
					}
				}
			}
		}
		else if (ty == "item")
		{
			for(Sprite s: fenster.item_obj)
			{
				int l2 = s.position.x;
				int t2 = s.position.y;
				int r2 = s.position.x + s.width;
				int b2 = s.position.y + s.height;
				
				if (s.typ == ty)
				{
					if (b1<t2)
					{
					}
					else if (t1>b2)
					{
					}
					else if (r1<l2)
					{
					}
					else if (l1>r2)
					{
					}
					else
					{
						ret = s;
						break;
					}
				}
			}
		}
		return ret;
	}
	
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	public void run()
	{
		while(true)
		{
 
			float START = System.currentTimeMillis();

			//Game Logic
						
						fall_speed += 0.1;
						
						if (checkcollission(spieler.position.x+10, spieler.position.y+54,44,10, "erde") != null)
						{
							fall_speed = 0;
							
							cont = true;
							
							while (checkcollission(spieler.position.x+10, spieler.position.y+54,44,9, "erde") != null)
							{
								spieler.position.y -= 1 ;
							}
						}
						
						
						if(steuerung.jump == true)
						{
							if (cont == true)
							{
								cont = false;
								fall_speed = -6f;
							}
						}
						
						
						if (fall_speed < 0)
						{
							if (checkcollission(spieler.position.x+10, spieler.position.y,44,10, "erde") == null)
							{
								spieler.position.y += fall_speed;
							}
							else
							{
								fall_speed = 0;
							}
						}
						else
						{
							spieler.position.y += fall_speed;
						}
						
						
						
						if(steuerung.links == true)
						{
							if (checkcollission(spieler.position.x, spieler.position.y+5,10,54, "erde") == null)
							{
								spieler.position.x -= 1;
							}
							spieler.anim_mode = 1;
						}
						//Rechts
						else if(steuerung.rechts == true)
						{
							if (checkcollission(spieler.position.x+54, spieler.position.y+5,10,54, "erde") == null)
							{
								spieler.position.x += 1;
							}
							spieler.anim_mode = 2;
						}
						
						//--------------------------------------------------------------------------------------------------------------------------
						// Tasten loslassen
						if(steuerung.jump == false)
						{
							if(steuerung.runter == false)
							{
								if(steuerung.links == false)
								{
									if(steuerung.rechts == false)
									{
										spieler.anim_mode = 0;
									}
								}
							}
						}
						//--------------------------------------------------------------------------------------------------------------------------
						//Münzenberührung:
						
						coll_item = checkcollission(spieler.position.x, spieler.position.y,64,64, "item");
						if (coll_item != null)
						{
							if (coll_item.alive)
							{
								coll_item.alive = false;
								ap.play("audio/coin.aif");
								fenster.muenz_anz++;
							}
						}
						//-------------------------------------------------------------------------------------------------------------------------
						
						
						//Alle 2 sek. collision_buffer aktualisieren
	
						if (time_count<400)
						{
							time_count++;
						}
						else
						{
							synchronized(fenster.collision_buffer) // Sorgt dafür, dass nicht 2 Threads auf das selbe Objekt gleichzeitig zugreifen
							{
								fenster.collision_buffer.clear();
								
								fenster.collision_buffer.add(fenster.spieler_obj); // Spieler zuerst hinzufügen
								
								for(Sprite s: fenster.counter_anz_obj)
								{
									fenster.collision_buffer.add(s); 
								}
								
								for(Sprite s: fenster.sprites)
								{
									if (s.position.x > spieler.position.x-1000 && s.position.x < spieler.position.x+1000 && s.typ != "spieler" && s.typ != "counter")
									{
										fenster.collision_buffer.add(s);
										i++;
									}
								}
								time_count = 0;
							}
						}
						
			//--------------------------------------------------------------------------------------------------------------------------
			
			//Frameberechnung:			
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