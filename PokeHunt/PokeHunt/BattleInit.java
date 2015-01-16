import java.util.*;

public class BattleInit
{
	private static boolean inBattle = false;
	private static boolean isDead;
	static Random random = new Random();
	static Pokemon pmon;
	static Player player;
	private static boolean isGrass = false;
	static boolean win = false;
	static boolean lose = false;



	public static void SetUpBattle(Pokemon [] PKMN, Player ply)//sets up a new pokemon battle
	{


		int p = random.nextInt(5);//chooses random pokemon

		pmon = new Pokemon(PKMN[p].getName(), PKMN[p].getATK()+player.getLevel()*5, PKMN[p].getHP()+ player.getLevel()*10, PKMN[p].getPic());

		player = ply;

	}

	public static void grassCheck(Player play) {//checks if  grass has pokemonb
		if(random.nextInt(5) == 1 && play.getY() > 185 ) {//random chance of getting a battle
			inBattle = true;
			player = play;
			SetUpBattle(initPokemon.getPKMN(), play);
		}


	}

	public static boolean inBattle() {
		return inBattle;
	}

	public static void changeState(boolean state) {
		inBattle = state;
	}



	public static void inflictDamage() {//inflicts damage on pokemon

		pmon.takeDamage(player.getCurrentGun().getDamage());
			if(!pmon.isDead()) {
			System.out.println(pmon.getHP());
		}
		else {
			System.out.println("You killed it");
			win = true;
			player.addKills(1);
			inBattle = false;
			player.addSkin(1);
		}
	}

	public static boolean wonBattle() {//win battle boolean
		return win;
	}

	public static boolean lostBattle() {//lose battle boolean
		return lose;
	}


	public static void takeDamage() {//inflicts damage on player
		if(!player.isDead()) {
			if(pmon.getATK() < 251)
				player.takeDamage(pmon.getATK());
			else
				player.takeDamage(250);
		}
		else {//actions on death
			System.out.println("You are dead.");
			player.setX(1);
			player.setY(81);
			player.removeSkins(player.getSkins());
			player.removeCoins(player.getCoins());
			lose = true;
			inBattle = false;
		}
	}

	public static Pokemon getPokemon() {
		return pmon;
	}

}


