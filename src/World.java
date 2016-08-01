package com.pokemon;

import java.util.ArrayList;
import java.util.Scanner;

public class World {
	static Scanner in = new Scanner(System.in);

	private ArrayList<Pokemon> pokemonInWorld = new ArrayList<>();
	private Player player;
	boolean done = false;

	public World() {
		addPlayer();
		chooseStarterPokemon();
		addPokemonToWorld(new Pokemon("Pidgey", 4, 5));
		addPokemonToWorld(new Pokemon("Psyduck", 3, 1, 50));
		addPokemonToWorld(new Pokemon("Charmander", 1, 4, 20));
		addPokemonToWorld(new Pokemon("Pidgey", 3, 5));
		addPokemonToWorld(new Pokemon("Squirtle", 7, 3));
		addPokemonToWorld(new Pokemon("Zapdos", 8, 8, 70));
		addPokemonToWorld(new Pokemon("Mew", 0, 9, 65));

		gameLoop();
	}

	public void addPlayer() {
		player = new Player(3, 3);
	}

	public void checkPlayerCollision() {
		for (Pokemon p : pokemonInWorld)
			if (player.getX() == p.getX() && player.getY() == p.getY()) {
				encounterPokemon(p);
				pokemonInWorld.remove(p);
				break;
			}
	}

	private void encounterPokemon(Pokemon wildPokemon) {
		boolean inEncounter = true;
		Pokemon firstInParty = player.getNextHealthyPokemon();
		System.out.format("\nA wild %s (%d) appeared! Go %s (%d)!\n", wildPokemon.getName(), wildPokemon.getHP(),
				firstInParty.getName(), firstInParty.getHP());

		while (inEncounter) {
			System.out.println("\tBATTLE\t  BALL\t  RUN");
			String input = checkInput(in.nextLine());

			if (input.toLowerCase().equals("battle")) {
				ArrayList<Move> firstPokemonMoves = firstInParty.getMoves();
				System.out.print("Choose a move: ");
				firstInParty.printMoves();
				String moveInput = in.nextLine().toLowerCase();

				for (int i = 0; i < firstPokemonMoves.size(); i++)
					if (moveInput.equals(firstPokemonMoves.get(i).getMoveName().toLowerCase())) {
						System.out.format("%s dealt %d damage to %s. ", firstPokemonMoves.get(i).getMoveName(),
								firstPokemonMoves.get(i).getDamage(), wildPokemon.getName());
						wildPokemon.dealDamage(firstPokemonMoves.get(i).getDamage());
						System.out.format("%s has %d HP remaining.\n", wildPokemon.getName(), wildPokemon.getHP());
					}

				if (wildPokemon.getHP() < 1) {
					System.out.format("Wild %s has fainted!\n", wildPokemon.getName());
					inEncounter = false;
				}
			}

			else if (input.toLowerCase().equals("ball")) {
				if (wildPokemon.getCaptureRate() > (int) (Math.random() * 100 + 1)) {
					System.out.println("\nCaught! " + wildPokemon.getName() + " added to party!\n");
					player.addPokemonToParty(wildPokemon);
					inEncounter = false;
				} else {
					System.out.println(wildPokemon.getName() + " escaped the ball!");
				}
			}

			else if (input.toLowerCase().equals("run")) {
				System.out.println("Got away safely!");
				inEncounter = false;
			}

			// wild pokemon runs or attacks
			if (inEncounter && wildPokemon.getHP() > 0) {
				if (wildPokemon.getRunRate() > (int) (Math.random() * 100 + 1)) {
					System.out.println(wildPokemon.getName() + " ran away!");
					inEncounter = false;
				} else {
					Move wildPokemonMove = wildPokemon.getMoves()
							.get((int) (Math.random() * wildPokemon.getMoves().size()));
					System.out.format("Wild %s used %s. %s dealt %d damage to %s. ", wildPokemon.getName(),
							wildPokemonMove.getMoveName(), wildPokemonMove.getMoveName(), wildPokemonMove.getDamage(),
							firstInParty.getName());
					firstInParty.dealDamage(wildPokemonMove.getDamage());
					System.out.format("%s has %d HP remaining.\n", firstInParty.getName(), firstInParty.getHP());
				}
			}

			// switch out fainted pokemon or game over
			if (firstInParty.getHP() < 1) {
				System.out.format("%s has fainted.\n", firstInParty.getName());
				firstInParty = player.getNextHealthyPokemon();
				if (firstInParty != null)
					System.out.format("Go %s (%d)!\n", firstInParty.getName(), firstInParty.getHP());
				else {
					done = true;
					break;
				}
			}
		}
	}

	private String checkInput(String nextLine) {
		while (!nextLine.equals("battle") && !nextLine.equals("ball") && !nextLine.equals("run")) {
			System.out.println("Try again...");
			nextLine = in.nextLine();
		}
		return nextLine;
	}

	private void addPokemonToWorld(Pokemon p) {

		pokemonInWorld.add(p);
	}

	private void chooseStarterPokemon() {
		System.out.println("Choose a starter Pokemon:\n\tCharmander\tBulbasaur\tSquirtle");
		String pokemonStarter = in.nextLine().toLowerCase();
		if (pokemonStarter.equals("charmander"))
			player.addPokemonToParty(new Pokemon("Charmander", 4, 5));
		else if (pokemonStarter.equals("bulbasaur"))
			player.addPokemonToParty(new Pokemon("Bulbasaur", 4, 5));
		else if (pokemonStarter.equals("squirtle"))
			player.addPokemonToParty(new Pokemon("Squirtle", 4, 5));
		else if (pokemonStarter.equals("quit")) {
			System.out.println("k");
			done = true;
		} else {
			System.out.print("Try again... ");
			chooseStarterPokemon();
		}
	}

	public static void main(String[] args) {
		World world = new World();

	}

	private void gameLoop() {
		String input;

		while (!done) {
			System.out.format("You are at position: (%d, %d)\n", player.getX()+1, player.getY()+1);
			System.out.println("\tMove UP, DOWN, LEFT, or RIGHT? /// PARTY or QUIT?");
			printGrid();
			input = in.nextLine();

			if (input.toLowerCase().equals("quit")) {
				done = true;
				break;
			}

			player.processInput(input.toLowerCase());

			checkPlayerCollision();
		}

		System.out.println("Game over!");
	}

	private void printGrid() {
		boolean isMarkerPlaced = false;
		for (int i = 9; i >= 0; i--) {
			for (int j = 0; j < 10; j++) {
				for (Pokemon p : pokemonInWorld) {
					if (j == p.getX() && i == p.getY()) {
						System.out.print("O ");
						isMarkerPlaced = true;
						break;
					}
				}
				if (isMarkerPlaced == false){
					if(j == player.getX() && i == player.getY())
						System.out.print("U ");
					else
					System.out.print("x ");
				}
				isMarkerPlaced = false;
			}
			System.out.println();
		}
	}
}
