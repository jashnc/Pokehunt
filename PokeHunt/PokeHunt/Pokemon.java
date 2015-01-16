import java.awt.*;

public class Pokemon
{
	private String name;
	private int atk;
	private int hp;
	Image pic;

	public Pokemon(String n, int a, int h, Image p)//sets up new pokemon object with name, attack, hp, and picture attributes
	{
		name = n; atk = a; hp = h; pic = p;
	}

	public String getName()
	{
		return name;
	}

	public int getATK()
	{
		return atk;
	}

	public int getHP()
	{
		return hp;
	}

	public void takeDamage(int amount) {//reduces hp of pokemon
		hp -= amount;


	}

	public boolean isDead() {//checks if pokemon is dead
		if(hp<= 0)
			return true;

		return false;
	}

	public Image getPic()
	{
		return pic;
	}
}
