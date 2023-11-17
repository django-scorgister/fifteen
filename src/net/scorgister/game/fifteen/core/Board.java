package net.scorgister.game.fifteen.core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	
	private int[][] board;
	
	public Board(int[][] board) {
		this.board = board;
	}
	
	public boolean move(Direction dir) {
		Point origin = getEmptyTilePoint();
		if(origin == null)
			throw new NullPointerException("No empty case");
		
		switch(dir) {
			case NORTH:
				origin.y += 1;
				if(origin.y > 0 && origin.y < board[origin.x].length) {
					switchPlace(origin, dir);
				}else
					return false;
				break;
				
			case WEST:
				origin.x += 1;
				if(origin.x > 0 && origin.x < board.length) {
					switchPlace(origin, dir);
				}else
					return false;
				break;
				
			case SOUTH:
				origin.y -= 1;
				if(origin.y >= 0 && origin.y < board[origin.x].length) {
					switchPlace(origin, dir);
				}else
					return false;
				break;
				
			case EAST:
				origin.x -= 1;
				if(origin.x >= 0 && origin.x < board.length) {
					switchPlace(origin, dir);
				}else
					return false;
				break;
		}
		
		return true;
	}
	
	private Point getEmptyTilePoint() {
		for(int x = 0; x < board.length; x++)
			for(int y = 0; y < board[x].length; y++)
				if(board[x][y] == 0)
					return new Point(x, y);
		return null;
	}
	
	private void switchPlace(Point tile, Direction dir) {
		int tmp = 0;
		switch(dir) {
			case NORTH:
				tmp = board[tile.x][tile.y];
				board[tile.x][tile.y] = 0;
				board[tile.x][tile.y - 1] = tmp;
				break;
			case WEST:
				tmp = board[tile.x][tile.y];
				board[tile.x][tile.y] = 0;
				board[tile.x - 1][tile.y] = tmp;
				break;
			case SOUTH:
				tmp = board[tile.x][tile.y];
				board[tile.x][tile.y] = 0;
				board[tile.x][tile.y + 1] = tmp;
				break;
			case EAST:
				tmp = board[tile.x][tile.y];
				board[tile.x][tile.y] = 0;
				board[tile.x + 1][tile.y] = tmp;
				break;
		}
	}
	
	public boolean isWin() {
		int a = 0;
main:	for(int y = 0; y < board[0].length; y++)
			for(int x = 0; x < board.length; x++) {
				if(a == getWidth() *  getHeight() - 1)
					break main;
				
				if(board[x][y] != a + 1)
					return false;
				
				a = board[x][y];
			}
		return true;
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public int getWidth() {
		return board.length;
	}
	
	public int getHeight() {
		return board[0].length;
	}
	
	public int getValue(int x, int y) {
		return board[x][y];
	}
	
	
	public static Board genBoard(int w, int h) {
		Random random = new Random();
		
		int startX = random.nextInt(0, w);
		int startY = random.nextInt(0, h);
		
		int[][] board = new int[w][h];
		List<Integer> used = new ArrayList<Integer>();
		for(int x = 0; x < w; x++) {
			for(int y = 0; y < h; y++) {
				if(startX == x && startY == y) {
					board[x][y] = 0;
					continue;
				}
				int rand = 0;
				do {
					rand = random.nextInt(1,  w * h);
				}while(used.contains(rand));
				used.add(rand);
				board[x][y] = rand;
			}
		}
	/*
		int[][] board = new int[4][4];
		board[0][0] = 1;
		board[1][0] = 2;
		board[2][0] = 3;
		board[3][0] = 4;
		board[0][1] = 5;
		board[1][1] = 6;
		board[2][1] = 7;
		board[3][1] = 8;
		board[0][2] = 9;
		board[1][2] = 10;
		board[2][2] = 11;
		board[3][2] = 12;
		board[0][3] = 13;
		board[1][3] = 14;
		board[2][3] = 0;
		board[3][3] = 15;
		*/
		return new Board(board);
	}
	
	@Override
	public String toString() {
		String lines = "";
		for(int y = 0; y < board[0].length; y++) {
			lines += "[";
			for(int x = 0; x < board.length; x++) {
				lines += board[x][y] + ", ";
			}
			
			lines += "]\n";
		}
		return lines;
	}

}
