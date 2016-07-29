public class Pokemon
{
	private String name;
	private int x;
	private int y;
	private int captureRate = 75;
	private int runRate = 20;
	private String type;
	private int healthPoints = 20;
	private Move[] moves = new Move[1];

	enum type
	{
		FIRE, WATER, GRASS, FLYING
	};

	public Pokemon(String name)
	{
		this.name = name;
		setPokemonDefaults();
	}

	public Pokemon(String name, int x, int y)
	{
		this(name);
		this.x = x;
		this.y = y;
	}

	public Pokemon(String name, int x, int y, int captureRate)
	{
		this(name, x, y);
		this.captureRate = captureRate;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public String getName()
	{
		return name;
	}
	
	public int getHP()
	{
		return healthPoints;
	}

	public int getCaptureRate()
	{
		return captureRate;
	}

	public int getRunRate()
	{
		return runRate;
	}
	
	public Move[] getMoves()
	{
		return moves;
	}

	private void setPokemonDefaults()
	{
		if (name.equals("Pidgey"))
		{
			moves[0] = new Move("Gust", "Flying", 5);
			healthPoints = 20;

		} else if (name.equals("Psyduck"))
		{
			moves[0] = new Move("Water Gun", "Water", 7);
			healthPoints = 30;

		} else if (name.equals("Charmander"))
		{
			moves[0] = new Move("Ember", "Fire", 12);
			healthPoints = 40;

		} else if (name.equals("Bulbasaur"))
		{
			moves[0] = new Move("Vine Whip", "Grass", 12);
			healthPoints = 40;

		} else if (name.equals("Squirtle"))
		{
			moves[0] = new Move("Water Gun", "Water", 12);
			healthPoints = 40;

		}
	}

	public void dealDamage(int damage)
	{
		healthPoints -= damage;
	}

}
