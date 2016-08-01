package com.pokemon;

import java.util.ArrayList;

public class Pokemon {
	private String name;
	private int x;
	private int y;
	private int captureRate = 75;
	private int runRate = 10;
	private String type;
	private int healthPoints = 20;
	private ArrayList<Move> moves = new ArrayList<>();

	enum type {
		FIRE, WATER, GRASS, FLYING
	};

	public Pokemon(String name) {
		this.name = name;
		for (Move m : moves)
			m = new Move();
		setPokemonDefaults();
	}

	public Pokemon(String name, int x, int y) {
		this(name);
		this.x = x;
		this.y = y;
	}

	public Pokemon(String name, int x, int y, int captureRate) {
		this(name, x, y);
		this.captureRate = captureRate;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public int getHP() {
		return healthPoints;
	}

	public int getCaptureRate() {
		return captureRate;
	}

	public int getRunRate() {
		return runRate;
	}

	public ArrayList<Move> getMoves() {
		return moves;
	}

	private void setPokemonDefaults() {
		if (name.equals("Pidgey")) {
			moves.add(new Move("Gust", "Flying", 5));
			healthPoints = (int) (Math.random() * 9) + 14;
		} else if (name.equals("Psyduck")) {
			moves.add(new Move("Water Gun", "Water", 7));
			healthPoints = 30;
		} else if (name.equals("Charmander")) {
			moves.add(new Move("Ember", "Fire", 12));
			moves.add(new Move("Fire Blast", "Fire", 25));
			healthPoints = 40;
		} else if (name.equals("Bulbasaur")) {
			moves.add(new Move("Vine Whip", "Grass", 12));
			moves.add(new Move("Tackle", "Normal", 8));
			moves.add(new Move("Leaf Blade", "Grass", 15));
			healthPoints = 40;
		} else if (name.equals("Squirtle")) {
			moves.add(new Move("Water Gun", "Water", 12));
			moves.add(new Move("Splash", "Water", 0));
			healthPoints = 30;
		}
		else if (name.equals("Zapdos")) {
			moves.add(new Move("Thunderbolt", "Electric", 20));
			moves.add(new Move("Thunder", "Electric", 100));
			healthPoints = 80;
		}
		else if (name.equals("Mew")) {
			moves.add(new Move("Psychic", "Psychic", 30));
			healthPoints = 50;
		}
	}

	public void dealDamage(int damage) {
		healthPoints -= damage;
		if (healthPoints < 0)
			healthPoints = 0;
	}

	public void printMoves() {
		System.out.format("%s (%d)", moves.get(0).getMoveName(), moves.get(0).getDamage());
		for (int i = 1; i < moves.size(); i++)
			System.out.format(", %s (%d)", moves.get(i).getMoveName(), moves.get(i).getDamage());
		System.out.println();
	}

}
