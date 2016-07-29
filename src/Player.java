import java.util.ArrayList;

public class Player
{
	private int x;
	private int y;
	private ArrayList<Pokemon> pokemonParty = new ArrayList<>();

	public Player(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void moveUp()
	{
		y++;
	}

	public void moveDown()
	{
		y--;
	}

	public void moveRight()
	{
		x++;
	}

	public void moveLeft()
	{
		x--;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void addPokemonToParty(Pokemon p)
	{
		pokemonParty.add(p);
	}
	
	public ArrayList<Pokemon> getPokemonParty()
	{
		return pokemonParty;
	}

	public void processInput(String input)
	{
		if (input.equals("up"))
		{
			moveUp();
		} else if (input.equals("down") && y > 0 && y < 10)
		{
			moveDown();
		} else if (input.equals("left") && x > 0 && x < 10)
		{
			moveLeft();
		} else if (input.equals("right"))
		{
			moveRight();
		} else if (input.equals("party"))
		{
			printPokemonParty();
		} else
			System.out.println("COMMAND UNKNOWN");
	}

	private void printPokemonParty()
	{
		if (pokemonParty.size() > 0)
		{
			System.out.format("Pokemon in party: %s (%d)", pokemonParty.get(0).getName(), pokemonParty.get(0).getHP());
			for (int i = 1; i < pokemonParty.size(); i++)
				System.out.format(", %s (%d)", pokemonParty.get(i).getName(), pokemonParty.get(i).getHP());
			System.out.println();
		} else
		{
			System.out.println("Uh oh! No pokemon in party!");
		}
	}
}