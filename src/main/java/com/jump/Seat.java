package com.jump;

public class Seat {
	public final int row, column; // <- this is the minimum
	private final String name;

	public Seat(int row, int column, String name) {
		this.row = row - 1;
		this.column = column - 1;
		this.name = name;
	}
}
