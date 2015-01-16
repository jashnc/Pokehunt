/*Jashn Chhaya / Hanyao Zhang
Pokehunt
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
import java.applet.*;
import java.util.ArrayList;



public class app extends Applet implements Runnable, KeyListener{




	//double buffer images
	private Image dbImage;
	private Graphics dbg;
	private Image battle;
	private Image bar;
	private Image gun1;
	private Image gun2;
	private Image title;
	private Image health;
	Graphics g2;
	private Image gunAnim;
	AudioClip battleMusic;
	private int actionCounter;
	boolean fireGun = false;
	private Image gun;
	private Image bullet;
	int x = 320;
	int y = 0;
	private Image shop;
	boolean isShopping = false;
	private Image youWin;
	private Image youLose;
	private int outcome = 0;
	private int shopX = 145;
	private int shopY = 77;
	private boolean inMenu = true;

	private Image knife, M9, Uzi, P90, M4, MK3A2, M240b, M82, SMAW, Minigun;
	private Image inventory;


	int gunIndex = 0;

	boolean moveBack = false;
	boolean change = false;

	private Image bg;//background
	private Image playerSprite;//player sprite



	Player player;

	boolean isAnimating = false;

	int count =0;
	int actionCount = 0;


   	public void init() {
		//image initialization
		title = getImage(getCodeBase(), "title.gif");
		inventory = getImage(getCodeBase(), "inventory.gif");
		knife = getImage(getCodeBase(), "knife.gif");
		M9 = getImage(getCodeBase(), "M91.gif");
		Uzi = getImage(getCodeBase(), "Uzi1.gif");
		P90 = getImage(getCodeBase(), "P901.gif");
		M4 = getImage(getCodeBase(), "M4.gif");
		MK3A2 = getImage(getCodeBase(), "MK3A21.gif");
		M240b = getImage(getCodeBase(), "M240b1.gif");
		M82 = getImage(getCodeBase(), "M821.gif");
		SMAW = getImage(getCodeBase(), "SMAW1.gif");
		Minigun = getImage(getCodeBase(), "Minigun1.gif");
		youWin = getImage(getCodeBase(), "winner.gif");
		youLose = getImage(getCodeBase(), "died.gif");
		shop = getImage(getCodeBase(), "shop.gif");

		bullet = getImage(getCodeBase(), "bullet.gif");
		gun = getImage(getCodeBase(), "m4.gif");
		gunAnim = getImage(getCodeBase(), "m4anim.gif");
		bg = getImage(getCodeBase(), "map.gif");
		gun1 = getImage(getCodeBase(), "gun1.gif");
		gun2 = getImage(getCodeBase(), "gun2.gif");
		//playerSprite = getImage(getCodeBase(), "hi.gif");
		battle = getImage(getCodeBase(), "battle.gif");

		setSize(320,320);
		addKeyListener(this); //adds keyListener


		new initPokemon();//initializes pokemon
		new InitItems();//initializes items
		Shop.add();//adds items to shop
		player = new Player("Bob", 1, 77);//intiates new player

   	}



   	public void start() {

		Thread th = new Thread(this);
		th.start(); //starts new thread
   	}

   	public void update(Graphics g) {
		if (dbImage == null)
		{
		dbImage = createImage (this.getSize().width, this.getSize().height);
		dbg = dbImage.getGraphics ();
		}

		// clear screen in background
		dbg.setColor (getBackground ());
		dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

		// draw elements in background
		dbg.setColor (getForeground());
		paint (dbg);

		// draw image on the screen
		g.drawImage (dbImage, 0, 0, this);
	}

  	public void run() {
	   Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

	   while(true) { //animation loop
	   //
	   y++;
		x--;

		if(!moveBack)
			count++;
		else
			count--;

		if(count == 200)
			moveBack = true;
		else if(count == 150)
			moveBack = false;

		//printing winner/death screens
		if(BattleInit.wonBattle())
			if(outcome < 50) {
				outcome++;
				System.out.println(outcome);
			}
			else {
				outcome = 0;
				BattleInit.win = false;
			}

		if(BattleInit.lostBattle())
			if(outcome < 50)
				outcome++;
			else {
				outcome = 0;
				BattleInit.lose = false;
			}





	   //
		   repaint();
		   try {
			   Thread.sleep(20);//pause before each new repaint(frame).
		   } catch (InterruptedException ex) {
		   }
	   Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

	   }
  	 }

  	public void stop() {

  	}

   	public void destroy() {

   	}

    public void paint(Graphics g) {
 Image[] guns = {knife, M9, Uzi, P90, M4, MK3A2, M240b, M82, SMAW, Minigun}; //initializes new array of guns(for painting in battle screen)

	   if(BattleInit.inBattle() == true) {//if player is in battle these elements are painted(battle screen, pokemon, etc.)
			if(change) {//if player is changing weapon
			g.drawImage(inventory, 0, 0, this);

			player.drawInventory(g);
			}
			else {

		   g.drawImage(battle, 0, 0, this);
		   g.drawImage(BattleInit.getPokemon().getPic(), count, 15, this);

		   g.drawImage(guns[java.util.Arrays.asList(InitItems.getGUNS()).indexOf(player.getGun(gunIndex))], 22, 100, this);//after player changes weapon, uses appropriate sprite

		   //pokemon info
		   String healthamount = "HP: "+player.getHealth();
		   String pokemonHealth = "HP: "+BattleInit.getPokemon().getHP();
		   String pokemonName = ""+BattleInit.getPokemon().getName();
		   //draws pokemon info
		   g.drawString(healthamount, 120, 190);
		   g.drawString(pokemonHealth, 225, 175);
		   g.drawString(pokemonName, 205, 155);






		}
	   }
	   else if(BattleInit.wonBattle()) {//if player wins battle

		   g.drawImage(battle, 0, 0, this);
		   g.drawImage(BattleInit.getPokemon().getPic(), count, 15, this);

			//draws underneath winner screen
		   g.drawImage(guns[java.util.Arrays.asList(InitItems.getGUNS()).indexOf(player.getGun(gunIndex))], 22, 100, this);

		   String healthamount = "HP: "+player.getHealth();
		   String pokemonHealth = "HP: "+BattleInit.getPokemon().getHP();
		   String pokemonName = ""+BattleInit.getPokemon().getName();

		   g.drawString(healthamount, 120, 190);
		   g.drawString(pokemonHealth, 225, 175);
		   g.drawString(pokemonName, 205, 155);
		   g.drawImage(youWin, 80, 115, this);

		   System.out.println("won");

		   player.setHealth(300);//resets health
	   }
	   else if(BattleInit.lostBattle()) {
		   g.drawImage(youLose, 0, 0, this);
			player = new Player("Bob", 1, 77);//resets player after death
	   	   System.out.println("lost");

	   }
	   else if(inMenu)//start menu
	   	g.drawImage(title, 0, 0, this);

	   else if(isShopping && !BattleInit.inBattle()) {//shop interface

		   g.drawImage(shop, 0, 0, this);
		   	Shop.createInterface(g);
		   	g.drawString("Gold: "+player.getCoins(), 75, 130);
		   	g.drawString("Hides: "+player.getSkins(), 75, 145);
		   	g.drawString("[Z] to exit.", 75, 165);
		   	g.drawString("Press [B] to sell all hides. ",140, 260);
		   	g.drawString("Hides sell for 50g each.",140, 275);

		}

		else {

		//main interface
	   g.drawImage(bg, 0, 0, this);//draws background


	   g.drawImage(player.getPLAYERs(), player.getX(), player.getY(), this);//draws the playersprite.
   		}




  	 }
  	 /*Keyboard Listener*/
  	public void keyPressed(KeyEvent e) {
		char letter = e.getKeyChar();
		int key = e.getKeyCode();
		if(BattleInit.inBattle() == false && isShopping == false) {
		//when not inBattle
		 if(key == 37) {//keys are represented by numbers - (e.g., LEFT on Keyboard is 37).
		 	if(validLocation())//checks if location is valid
			 player.updateX(-16);//moves left
			 if(player.getX() < 1)
			 	player.setX(1);

			BattleInit.grassCheck(player);
			 System.out.println("left");


		 }
		 else if (key == 39) {
			 if(validLocation())
			 player.updateX(16);//moves right
			 	if(player.getX() > 305)
			 		player.setX(305);

			BattleInit.grassCheck(player);
			 System.out.println("right");

			 System.out.println(player.getX()+" "+player.getY());


		 }
		 else if (key == 40) {
			 if(validLocation())
		 	 player.updateY(16);//moves down
		 	 	if(player.getY() > 300)
		 	 		player.setY(300);

		 	BattleInit.grassCheck(player);
		 	 System.out.println("down");


		 }
		 else if (key == 38) {

			 if(validLocation())
		 	 player.updateY(-16);//moves up
		 	 	if(player.getY() < 76)
		 	 		player.setY(77);
		 	 BattleInit.grassCheck(player);
		 	 System.out.println("up");

		 }
		 else if(letter == 's') {

			 if(player.getX() == shopX && player.getY() == shopY) {//checks if player is by shopkeeper
				 System.out.println("shopping");
			 if(isShopping == false)
			 	isShopping = true;
			 else
			 	isShopping = false;
			}
		 }
	 }
	 if(change == true) {//if player is changing weapons
		 player.printItems();
		 if(key == KeyEvent.VK_0) {

			 player.changeGun(0);
			 player.getCurrentGun();
			 gunIndex = 0;
		 }
		  if(key == KeyEvent.VK_1) {

		 			 if(player.getGuns().size()>=2) {//checks if a gun actually exists at this index
		 			 player.changeGun(1);

		 			 player.getCurrentGun();
		 			 gunIndex = 1;
				 	}
		 }
		  if(key == KeyEvent.VK_2) {

		 			 if(player.getGuns().size()>=3) {
		 			 player.changeGun(2);
		 			 player.getCurrentGun();
		 			 gunIndex = 2;
				 }
		 }
		  if(key == KeyEvent.VK_3) {

		 			 if(player.getGuns().size()>=4) {
		 			 player.changeGun(3);
		 			 player.getCurrentGun();
		 			 gunIndex = 3;
				 }
		 }
		  if(key == KeyEvent.VK_4) {

		 			 if(player.getGuns().size()>=5) {
		 			 player.changeGun(4);
		 			 player.getCurrentGun();
		 			 gunIndex = 4;
				 }
		 }
		  if(key == KeyEvent.VK_5) {

		 			 if(player.getGuns().size()>=6) {
		 			 player.changeGun(5);
		 			 player.getCurrentGun();
		 			 gunIndex = 5;
				 }
		 }
		  if(key == KeyEvent.VK_6) {

		 			 if(player.getGuns().size()>=7) {
		 			 player.changeGun(6);
		 			 player.getCurrentGun();
		 			 gunIndex = 6;
				 }
		 }
		 if(key == KeyEvent.VK_7) {

		 		 			 if(player.getGuns().size()>=8) {
		 		 			 player.changeGun(7);
		 		 			 player.getCurrentGun();
		 		 			 gunIndex = 7;
						 }
		 }
		 if(key == KeyEvent.VK_8) {

		 		 			 if(player.getGuns().size()>=9) {
		 		 			 player.changeGun(8);
		 		 			 player.getCurrentGun();
		 		 			 gunIndex = 8;
						 }
		 }
		 if(key == KeyEvent.VK_9) {

		 		 			 if(player.getGuns().size()>=10) {
		 		 			 player.changeGun(9);
		 		 			 player.getCurrentGun();
		 		 			 gunIndex = 9;
						 }
		 }

	 }
	 if(inMenu) {//if player is in main menu
	 	if(key == KeyEvent.VK_ENTER) {
			inMenu = false;
		}

	}
	if(isShopping == true) {//if player is in shop interface
		if(key == KeyEvent.VK_1) {
			Shop.buyItem(player, 1);

		}
		if(key == KeyEvent.VK_Z) {

		isShopping = false;
		}
		if(key == KeyEvent.VK_2) {
			Shop.buyItem(player, 2);

		}
		if(key == KeyEvent.VK_3) {
					Shop.buyItem(player, 3);

		}
		if(key == KeyEvent.VK_4) {
					Shop.buyItem(player, 4);

		}
		if(key == KeyEvent.VK_5) {
					Shop.buyItem(player, 5);

		}
		if(key == KeyEvent.VK_6) {
					Shop.buyItem(player, 6);

		}
		if(key == KeyEvent.VK_7) {
					Shop.buyItem(player, 7);

		}
		if(key == KeyEvent.VK_8) {
					Shop.buyItem(player, 8);

		}
		if(key == KeyEvent.VK_9) {
					Shop.buyItem(player, 9);

		}
		if(key == KeyEvent.VK_B) {
			System.out.println(player.getCoins());
			System.out.println(player.getSkins());
			player.addCoins(player.getSkins() * 50);
			player.removeSkins(player.getSkins());
			System.out.println(player.getCoins());
		}
	}
	else {
	 	if(key == 81 && change == false) {
			if(BattleInit.inBattle()) {
			System.out.println("ATTACK");

			BattleInit.inflictDamage();//inflicts damage on pokemon
			System.out.println();

			BattleInit.takeDamage();//inflicts damage on player
			System.out.println(player.getHealth());//prints player health
			System.out.println(player.getCurrentGun().getName());//prints current gun
			}

		}

		else if(key == 87) {//changes weapon
			System.out.println("CHANGE WEAPON");
			if(change == false)
				change = true;
			else
				change = false;

		}

		else if(key == KeyEvent.VK_R)//run away from fight
			if(!change)
			BattleInit.changeState(false);
	}





	 }
	 public void keyReleased(KeyEvent e) {//override abstract KeyListener

	 }

	 public void keyTyped(KeyEvent e) {//override abstract KeyListener

	 }




	 private void freeze(int time) {
		 try {
			 Thread.sleep(time);
		 } catch(InterruptedException ex) {
		 }
	 }

	 public boolean validLocation(){
		 if(player.getY() < 77)
		 	return false;

		 return true;
	 }



}
