import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.*;
import java.applet.*;
import java.util.ArrayList;

public class Player
{
	private String name;
	private int[] data;
	private Image MAPsprite;
	private Image BATTLEsprite;
	private Image PLAYERsprite;
	private int kills;
	ArrayList<Item> items;
	ArrayList<Guns> guns;
	Guns currentGun;


	//starting coords
	private int x;
	private int y;
	private int health;
	private int coins;
	private int skins;

	public Player(String n, int x, int y)//sets up player with name, and x,y coords for lcoation
	{
		skins = 0;
		name = n;
		items = new ArrayList<Item>();
		guns = new ArrayList<Guns>();
		coins = 0;
		addGun(0);
		currentGun = InitItems.getGun(0);
		this.x = x;
		this.y = y;
		URL url = getClass().getResource("hi.gif");
		health = 300;
		kills = 0;
		try {
		PLAYERsprite = ImageIO.read(url);
		} catch (IOException e) {
		}
	}

	public Player(String n, int[] stuffs)//never used
	{
		name = n;
		data = stuffs;
	}

	public String getName()
	{
		return name;
	}

	public void changeGun(int id) {//changes gun
		if(id < guns.size())
		currentGun = guns.get(id);
	}
	public ArrayList<Guns> getGuns() {//returns guns
		return guns;
	}

	public int getKills()//returns kills
	{
		return kills;
	}

	public void addKills(int x)//adds kills
	{
		kills += x;
	}


	public Guns getCurrentGun() {//returns equipped gun
		//System.out.println(currentGun.getName());
		return currentGun;
	}

	public int getLevel()//level is kill/10
	{
		return getKills()/10;
	}

	public int getSkins() {//return number of skins
		return skins;
	}

	public void addSkin(int amount) {//adds skins
		skins+=amount;
	}

	public void removeSkins(int amount) {//removes skins
		skins -=amount;
	}

	public void addCoins(int amount) {//adds coins
		coins += amount;
	}

	public int getCoins() {//returns number of coins
		return coins;
	}


	public void removeCoins(int amount) {//removes coins
		coins -= amount;
	}

	public int[] getData()//never used
	{
		return data;
	}

	public int getHealth() {//returns health
		return health;
	}
	public void setHealth(int x)//sets health
	{
		health = x;
	}

	public void addGun(int id) {//adds gun to inventory
		guns.add(InitItems.getGun(id));
	}

	public Guns getGun(int id) {//returns specific gun in inventory
		return guns.get(id);
	}
	public void removeItem(String name) {//removes item, never used
		for(int i = 0; i < items.size(); i ++) {
			if(items.get(i).equals(name)) {
			items.remove(i);
			break;
			}
		}
	}

	public int getAmount(String name) {//gets amount of a specific item, never used
		int amount = 0;
		for(int i = 0; i < items.size(); i++) {
			if(name.equals(items.get(i)));
				amount++;
		}
		return amount;
	}

	public boolean isDead() {//checks if player is dead
		if(health <= 0)
			return true;

		return false;
	}

	public void printItems() {//prints ivnentory
		for(int i = 0; i < guns.size(); i++) {
			System.out.println(guns.get(i).getName());
		}
	}

	public boolean hasItem(String name) {//checks if player has specific gun
		for(int i = 0; i < guns.size(); i++) {
			if(guns.get(i).getName().equals(name))
				return true;
		}

		return false;
	}

	public ArrayList<Item> getItems() {//returns items
		return items;
	}

	public void takeDamage(int health) {//player takes damage
		this.health -= health;
	}

	public void drawInventory(Graphics d) {//draws inventory on screen(switching weapons interface)
		int count = 0;
		for(int i = 1; i <=10; i++){
			for(int j = 0; j <= 10; j++){
		if(count < guns.size()) {
			d.drawString(guns.get(count).getName() + " - [" + count + "]",(15*i) +20, (15*j)+65);
			//System.out.println(items.get(count).getName());
			count++;
			}
		}
		}

	}
	//get methods
	public Image getMAPs()
	{
		return MAPsprite;
	}

	public Image getBATTLEs()
	{
		return BATTLEsprite;
	}

	public Image getPLAYERs() {
		return PLAYERsprite;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void updateX(int change) {
		x+=change;
	}

	public void updateY(int change) {
		y+=change;
	}

	public void setX(int x) {
		this. x = x;

	}

	public void setY(int y) {
		this.y = y;

	}

}
