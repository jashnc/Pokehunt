import java.awt.*;

public class Item {

	Image p;
	String name;
	int ID;
	int cost;

	public Item(String name,  int cost) {//new item object with name and cost
		this.name = name;
		this.p = p;

		this.cost = cost;
	}

	public Image getPic() {
		return p;
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}
}