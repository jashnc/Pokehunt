import java.awt.*;
import java.util.ArrayList;

public class Shop {



	static boolean draw = true;

	private static ArrayList<Guns> guns = new ArrayList<Guns>();//arraylist of gusn the store

	public static void add() {//adds guns to store
		guns.add(InitItems.getGun(1));
		guns.add(InitItems.getGun(2));
		guns.add(InitItems.getGun(3));
		guns.add(InitItems.getGun(4));
		guns.add(InitItems.getGun(5));
		guns.add(InitItems.getGun(6));
		guns.add(InitItems.getGun(7));
		guns.add(InitItems.getGun(8));
		guns.add(InitItems.getGun(9));

	}

	public static void createInterface(Graphics d) {//draws shop inferface

		int count = 0;

		for(int i = 1; i <=10; i++){
			for(int j = 0; j <= 10; j++){
				if(count < guns.size()) {
					d.drawString(guns.get(count).getName() + " ($" + guns.get(count).getCost() +")",(15*i) +165, (15*j)+55);
					//System.out.println(items.get(count).getName());
					count++;
				}
			}
		}

	}

	public static void buyItem(Player player, int ID) {//buys a gun
		if(player.getCoins() >= InitItems.getGun(ID).getCost()) {




		for(int i = 0; i < guns.size(); i++) {
			if(guns.get(i).getName().equals(InitItems.getGun(ID).getName())) {
				player.removeCoins(50);
				player.addGun(ID);
				guns.remove(i);
				System.out.println("hi");
				break;
			}

		}

		}
		else{
			System.out.println("not enough money");
		}


	}

	/*public static void sellItem(Player player, int ID) { //never actually used
		if(player.hasItem(InitItems.getItem(ID).getName())) {
			player.removeItem(InitItems.getItem(ID).getName());
			items.add(InitItems.getItem(ID));
		}
	}*/

	public static void printItems() {//prints items in stores(debugging purposes)
		for(int i = 0; i < guns.size(); i++) {
			System.out.println(guns.get(i).getName());
		}
	}
}