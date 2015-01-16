import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

public class InitItems //initializes a list of all pokemon in the game
{
	private static Guns[] guns = new Guns[10];
	private static Item[] items = new Item[10];



	public InitItems() {//intializes guns
		//(String name,int damage, int cost)
		guns[0] = new Guns("Knife", 10, 0);
		guns[1] = new Guns("M9", 30, 50);
		guns[2] = new Guns("Uzi", 50, 500);
		guns[3] = new Guns("P90", 80, 1200);
		guns[4] = new Guns("M4", 150, 2500);
		guns[5] = new Guns("MK3A2", 300, 7000);
		guns[6] = new Guns("M240b", 500, 12000);
		guns[7] = new Guns("M82", 600, 15000);
		guns[8] = new Guns("SMAW", 2000, 50000);
		guns[9] = new Guns("Minigun", 10000, 8000000);
	}


	public static Image readImg(String name)
	{
		Image img = null;

		try
		{
			img = ImageIO.read(new File(name));
		}
		catch (IOException e)
		{}

		return img;
	}

	public static Item[] getItems ()//returns items array
	{
		return items;
	}

	public static Guns[] getGUNS ()//returns guns array
	{
		return guns;
	}

	public static Item getItem(int id) {//return specific item
		return items[id];
	}

	public static Guns getGun(int id) {//returns specific gun
		return guns[id];
	}
}
