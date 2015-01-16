import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;

public class initPokemon //initializes a list of all pokemon in the game
{
	private static Pokemon[] PKMN = new Pokemon[5]; //for now, only 10

	public initPokemon() {
		PKMN[0] = new Pokemon ("Pikachu", 5, 10, readImg("pikachu.gif"));
		PKMN[1] = new Pokemon ("Charmander", 5, 10, readImg("charmander.gif"));
		PKMN[2] = new Pokemon ("Squirtle", 5, 10, readImg("squirtle.gif"));
		PKMN[3] = new Pokemon ("Bulbasaur", 5, 10, readImg("bulbasaur.gif"));
		PKMN[4] = new Pokemon ("Mew", 5, 10, readImg("mew.gif"));

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

	public static Pokemon[] getPKMN ()
	{
		return PKMN;
	}
}




