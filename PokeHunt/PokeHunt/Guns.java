import java.awt.*;

public class Guns extends Item{//inherits from item

	private int damage;

	public Guns(String name, int damage, int cost) {//new gun object with damage and cost attributes
		super(name, cost);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}

	public int getCost()
	{
		return cost;
	}
}