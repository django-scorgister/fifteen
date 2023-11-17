package net.scorgister.game.fifteen.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JComponent;

import net.scorgister.game.fifteen.Game;
import net.scorgister.game.fifteen.core.Board;

public class GamePanel extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2519502445500039147L;
	
	private Board board;
	
	public GamePanel(Board board) {
		this.board = board;
		setBackground(Color.DARK_GRAY);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Roboto", 0, 20));
		for(int x = 0; x < board.getWidth(); x++) {
			for(int y = 0; y < board.getHeight(); y++) {
				int value = board.getValue(x, y);
				
				g.setColor(Color.DARK_GRAY);
				g.fillRect(0,0, Game.TILE_WIDTH, Game.TILE_HEIGHT);
				
				g.setColor(Color.WHITE);
				g.drawRect(0,0, Game.TILE_WIDTH, Game.TILE_HEIGHT - 1);
				if(value == 0) {
					g.translate(0, Game.TILE_HEIGHT);
					continue;
				}
				
				g.drawString(Integer.toString(value), Game.TILE_WIDTH / 2, Game.TILE_HEIGHT / 2);
				
				g.translate(0, Game.TILE_HEIGHT);
			}
			g.translate(Game.TILE_WIDTH, -Game.TILE_HEIGHT * board.getHeight());
		}
	}

}
