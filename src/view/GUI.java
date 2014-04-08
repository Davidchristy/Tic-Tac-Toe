package view;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Game;

public class GUI extends JFrame {

	// images for graphic view
	private static BufferedImage ClearTile;
	private static BufferedImage CircleTile;
	private static BufferedImage CrossTile;
	private static Game game;
	private static BoardAsImage boardImage;
	private static BufferedImage CirleCursor;
	private static BufferedImage CrossCursor;
	private static Cursor cursor;
	private static Toolkit toolkit = Toolkit.getDefaultToolkit();
	JPanel mainPanel;

	static {
		// try to read in all of our images
		// this block is the blueprint to be used for all images
		try {
			
			
//			System.out.println(Thread.currentThread()
//					.getContextClassLoader().getResource("img/ClearTile.png"));
			ClearTile = ImageIO.read(Thread.currentThread()
					.getContextClassLoader()
					.getResource("img/ClearTile.png"));
			
			CircleTile = ImageIO.read(Thread.currentThread()
					.getContextClassLoader()
					.getResource("img/CircleTile.png"));
			
			CrossTile = ImageIO.read(Thread.currentThread()
					.getContextClassLoader()
					.getResource("img/CrossTile.png"));
			
			CirleCursor = ImageIO.read(Thread.currentThread()
					.getContextClassLoader()
					.getResource("img/CircleCursor.png"));
			CrossCursor = ImageIO.read(Thread.currentThread()
					.getContextClassLoader()
					.getResource("img/CrossTile.png"));
			
		} catch (IOException e) {
			System.out.println("Could not find an image.");
		}
	}

	public static void main(String[] args) {
		new GUI().setVisible(true);
	}

	GUI() {

		game = new Game();
		boardImage = new BoardAsImage();

		// lay out GUI
		this.setSize(625, 650); // size may need to change
		// this.setPreferredSize(new Dimension(735, 550)); // this does not work
		// this.setSize(735, 550);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Tic Tac Toe");

		initializeGUIComponents();
		addGUIComponents();

	}

	private void addGUIComponents() {
		// TODO Auto-generated method stub

	}

	private void initializeGUIComponents() {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.ORANGE);
		this.add(mainPanel);
		mainPanel.add(boardImage);
		mainPanel.addMouseListener(new MouseEventListener());
		cursor = toolkit.createCustomCursor(CrossCursor, new Point(
				mainPanel.getX(), mainPanel.getY()), "img");
		boardImage.setCursor(cursor);
	}

	private class MouseEventListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int clickedY = e.getX(); // save the x position of our click
			int clickedX = e.getY(); // save the y position of our click
			int X = (clickedX - 7) / 200;
			int Y = (clickedY - 7) / 200;
			if (X > -1 && X < 3 && Y > -1 && Y < 3)
				game.clickedTile(X, Y);
			if (game.isXTurn()) {
				cursor = toolkit.createCustomCursor(CrossCursor, new Point(
						mainPanel.getX(), mainPanel.getY()), "img");
				boardImage.setCursor(cursor);
			} else {
				cursor = toolkit.createCustomCursor(CirleCursor, new Point(
						mainPanel.getX(), mainPanel.getY()), "img");
				boardImage.setCursor(cursor);
			}

			boardImage.paintComponent(boardImage.getGraphics());
			boardImage.repaint();

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class BoardAsImage extends JPanel {

		BoardAsImage() {
			this.setPreferredSize(new Dimension(600, 600));
		}

		public void paintComponent(Graphics g) { // here is where we paint it
			super.paintComponent(g); // normal business
			Graphics2D g2d = (Graphics2D) g;

			BufferedImage newImage; // instantiate our image for painting
			for (int r = 0; r < 3; r++) { // for every row
				for (int c = 0; c < 3; c++) { // for every column
					newImage = chooseImageBackGround(r, c);
					g2d.drawImage(newImage, c * 200, r * 200, this);
				}
			}
		}

		public BufferedImage chooseImageBackGround(int r, int c) {
			if (game.getTile(r, c) == 0) {
				return ClearTile;
			} else if (game.getTile(r, c) == 2) {
				return CircleTile;
			} else {
				return CrossTile;
			}
		}

	}

}
