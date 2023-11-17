package net.scorgister.game.fifteen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import net.scorgister.game.fifteen.core.Board;
import net.scorgister.game.fifteen.core.Direction;
import net.scorgister.game.fifteen.ui.GamePanel;
import net.scorgister.game.fifteen.ui.StatusPanel;

public class Game {
	
	public static final int TILE_WIDTH = 75;
	public static final int TILE_HEIGHT = TILE_WIDTH;

	private Board board;
	private JFrame fen;
	private GamePanel panel;
	private StatusPanel statusPanel;
	
	private boolean freezed = false;
	
	private int count = 0;
	
	public Game(int w, int h) {
		this.board = Board.genBoard(w, h);
		this.fen = new JFrame("Fifteen game");
		this.panel = new GamePanel(board);
		this.statusPanel = new StatusPanel();
		
		fen.setDefaultCloseOperation(3);
		fen.setSize(w * TILE_WIDTH + w * 2 + 1, h * TILE_HEIGHT + h * 2 + 59);
		fen.setResizable(false);
		fen.setLocationRelativeTo(null);
		fen.setLayout(new BorderLayout());
		
		fen.add(BorderLayout.CENTER, panel);
		fen.add(BorderLayout.SOUTH, statusPanel);
		fen.setBackground(Color.DARK_GRAY);
		
		fen.addKeyListener(getKeyListener());
		
	}
	
	private KeyListener getKeyListener() {
		return new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(freezed)
					return;
				
				switch(e.getKeyCode()) {
					case KeyEvent.VK_UP:
						if(board.move(Direction.NORTH))
							count++;
						break;
					case KeyEvent.VK_LEFT:
						if(board.move(Direction.WEST))
							count++;
						break;
					case KeyEvent.VK_DOWN:
						if(board.move(Direction.SOUTH))
							count++;
						break;
					case KeyEvent.VK_RIGHT:
						if(board.move(Direction.EAST))
							count++;
						break;
				}
				
				if(board.isWin()) {
					freezed = true;
					statusPanel.changeStatus("Your score: " + count);
				}else 
					statusPanel.changeStatus("Move" + (count > 1?"s":"") + ": " + count);
				
				SwingUtilities.invokeLater(() -> {
					panel.repaint();					
				});
			}
		};
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void show() {
		fen.setVisible(true);
	}
	
	public JFrame getFrame() {
		return fen;
	}

}
