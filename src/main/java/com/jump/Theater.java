package com.jump;

import java.util.Scanner;

public class Theater {
	final int rows, columns;
	Seat[][] purchasedSeats;

	Scanner in = new Scanner(System.in);

	public Theater(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		purchasedSeats = new Seat[rows][columns];
	}

	void reserveSeat(Seat seat) throws SeatAlreadyBookedException {
		if (null == purchasedSeats[seat.row][seat.column]) {
			purchasedSeats[seat.row][seat.column] = seat;
		} else {
			throw new SeatAlreadyBookedException();
		}
	}

	void displaySeats() {
		StringBuilder rowsHeader = new StringBuilder();
		rowsHeader.append("""
				=============
				SEATS
				=============

				  \s""");
		for (int i = 1; i <= rows; i++) {
			rowsHeader.append(" ").append(i);
		}
		rowsHeader.append("\n   ").append("--".repeat(rows)).append("\n");
		for (int i = 0; i < rows; i++) {
			rowsHeader.append(i + 1).append(" | ");
			for (int j = 0; j < columns; j++) {
				if (null == purchasedSeats[i][j]) {
					rowsHeader.append("o ");
				} else {
					rowsHeader.append("x ");
				}
			}
			rowsHeader.append("\n");
		}
		rowsHeader.append("\n");
		// less flush, better performance
		System.out.println(rowsHeader);
	}

	void reserveSeatDialog() {
		System.out.println("Which seat do you want to reserve\nRow:");
		try {
			int row = in.nextInt();
			System.out.println("Column:");
			int column = in.nextInt();
			System.out.println("Name of the person sitting here");
			String name = in.next();
			Seat newSeat = new Seat(row, column, name);
			reserveSeat(newSeat);
			System.out.println("seat [" + row + "," + column + "] for: " + name + " reserved");
		}	catch (SeatAlreadyBookedException e) {
				System.out.println("[ERROR] Seat Already Booked");
		} catch (Exception e) {
			System.out.println("\n[ERROR] not a valid number");
		}
	}

	void reservationDialog() {
		System.out.println("Are you finished reserving seats? (Y/n)");
		String answer = in.next();
		if (answer.equalsIgnoreCase("y") || answer.equals("")) {
			System.out.println("This is the status of the Seats:");
			displaySeats();
			System.out.println("\nBye, have a nice day!");
		} else if (answer.equalsIgnoreCase("n")) {
			reserveSeatDialog();
			reservationDialog();
		} else {
			System.out.println("\nOption not supported\n");
			reservationDialog();
		}
	}
}
