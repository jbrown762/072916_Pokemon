package com.pokemon;

public class Move {
	private String moveName = "";
	private int damage = 0;
	private String type = "";

	public Move() {
	}
	
	public Move(String moveName, String type, int damage) {
		this.moveName = moveName;
		this.damage = damage;
		this.type = type;
	}

	public String getMoveName() {
		return moveName;
	}

	public int getDamage() {
		return damage;
	}
}
