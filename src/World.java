import java.util.ArrayList;
import java.util.Scanner;

public class World
{
	static Scanner in = new Scanner(System.in);

	private ArrayList<Pokemon> pokemonInWorld = new ArrayList<>();
	private Player player;
	boolean done = false;

	public void addPlayer()
	{
		player = new Player(3, 3);
	}

	public void checkPlayerCollision()
	{
		for (Pokemon p : pokemonInWorld)
			if (player.getX() == p.getX() && player.getY() == p.getY())
			{
				encounterPokemon(p);
				pokemonInWorld.remove(p);
				break;
			}
	}

	private void encounterPokemon(Pokemon wildPokemon)
	{
		boolean inEncounter = true;
		System.out.println("\nA wild " + wildPokemon.getName() + " appeared! Go " + player.getPokemonParty().get(0).getName());

		while (inEncounter)
		{
			System.out.println("\tBATTLE\t  BALL\t  RUN");
			String input = in.nextLine();
			Move[] firstPokemonMoves = player.getPokemonParty().get(0).getMoves();
			
			if (input.toLowerCase().equals("battle"))
			{
				System.out.format("Choose a move: %s\n", firstPokemonMoves[0].getMoveName());
				String moveInput = in.nextLine().toLowerCase();

				for (int i = 0; i < firstPokemonMoves.length; i++)
					if (moveInput.equals(firstPokemonMoves[i].getMoveName().toLowerCase()))
					{
						System.out.format("%s dealt %d damage to %s\n", firstPokemonMoves[i].getMoveName(), firstPokemonMoves[i].getDamage(), wildPokemon.getName());
						wildPokemon.dealDamage(firstPokemonMoves[i].getDamage());
						System.out.format("%s has %d HP remaining.\n", wildPokemon.getName(), wildPokemon.getHP());
					}
				
				if(wildPokemon.getHP() < 1){
					System.out.println(wildPokemon.getName() + " has fainted!");
					inEncounter = false;
				}
			}

			else if (input.toLowerCase().equals("ball"))
			{
				if (wildPokemon.getCaptureRate() > (int) (Math.random() * 100 + 1))
				{
					System.out.println("\nCaught! " + wildPokemon.getName() + " added to party!\n");
					player.addPokemonToParty(wildPokemon);
					inEncounter = false;
				} else
				{
					System.out.println(wildPokemon.getName() + " escaped the ball!");
				}
			}

			else if (input.toLowerCase().equals("run"))
			{
				System.out.println("Got away safely!");
				inEncounter = false;
			}

			if (inEncounter && wildPokemon.getHP() > 0 && wildPokemon.getRunRate() > (int) (Math.random() * 100 + 1))
			{
				System.out.println(wildPokemon.getName() + " ran away!");
				inEncounter = false;
			}
		}
	}

	private void addPokemonToWorld(Pokemon p)
	{

		pokemonInWorld.add(p);
	}

	public static void main(String[] args)
	{
		World world = new World();
		world.addPlayer();
		world.chooseStarterPokemon();
		world.addPokemonToWorld(new Pokemon("Pidgey", 4, 5));
		world.addPokemonToWorld(new Pokemon("Psyduck", 3, 2, 50));

		world.gameLoop();
	}

	private void chooseStarterPokemon()
	{
		System.out.println("Choose a starter Pokemon:\n\tCharmander\tBulbasaur\tSquirtle");
		String pokemonStarter = in.nextLine().toLowerCase();
		if (pokemonStarter.equals("charmander"))
			player.addPokemonToParty(new Pokemon("Charmander", 4, 5));
		else if (pokemonStarter.equals("bulbasaur"))
			player.addPokemonToParty(new Pokemon("Bulbasaur", 4, 5));
		else if (pokemonStarter.equals("squirtle"))
			player.addPokemonToParty(new Pokemon("Squirtle", 4, 5));
		else if (pokemonStarter.equals("quit"))
		{
			System.out.println("u suck");
			done = true;
		} else
		{
			System.out.print("Try again... ");
			chooseStarterPokemon();
		}
	}

	private void gameLoop()
	{
		String input;

		while (!done)
		{
			System.out.format("Player is at position: (%d, %d)\n", player.getX(), player.getY());
			System.out.println("\tMove UP, DOWN, LEFT, or RIGHT? /// PARTY or QUIT?");
			input = in.nextLine();

			if (input.toLowerCase().equals("quit"))
			{
				System.out.println("Done!");
				done = true;
				break;
			}

			player.processInput(input.toLowerCase());

			checkPlayerCollision();
		}
	}
}
