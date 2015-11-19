/**
 * 
 */
package com.godinez.pong;

import java.util.ArrayList;
import java.util.List;

/**
 * Round-robin tournament algorithm.
 * @author Ernesto Espinosa.
 */
public class Tournament {
	
	private int n;
	private List<List<Pair>> rounds;
	
	private class Pair { Pair(int a, int b) { this.a = a; this.b = b; } int a,b; }
	
	public Tournament(int n) {
		int k;
		int[] array;
		List<Pair> round;
		
		k = 0;
		if (n % 2 == 1) n++;
		this.n = n;
		array = new int[n];
		for (int i = 0; i < this.n/2; i++) {
			array[i] = i + 1;
			array[(this.n/2)+i] = this.n - i;
		}
		rounds = new ArrayList<List<Pair>>();
		
		while (k < this.n - 1) {
			round = new ArrayList<Tournament.Pair>();
			rounds.add(round);
			for (int i = 0; i < n/2; i++) round.add(new Pair(array[i],array[(n/2)+i]));
			moveForward(array);
			k++;
		}
	}
	
	private static void moveForward(int[] array) {
		int aux;
		for (int i = 2; i < array.length / 2; i++) {
			aux = array[1];
			array[1] = array[i];
			array[i] = aux;
		}
		
		aux = array[1];
		array[1] = array[array.length/2];
		array[array.length/2] = aux;
		
		for (int i = array.length/2 + 1; i < array.length; i++) {
			aux = array[array.length/2];
			array[array.length/2] = array[i];
			array[i] = aux;
		}
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int i = 1;
		for (List<Pair> round : this.rounds) {
			builder.append(String.format("Round %d:\n", i++));
			for (Pair pair : round) {
				builder.append(String.format("%d vs. %d\n", pair.a, pair.b));
			}
		}
		return builder.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(new Tournament(5));
	}
}
