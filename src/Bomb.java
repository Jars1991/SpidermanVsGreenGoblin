/*
 * Bomb.java allows to create, control and draw a bit map with bomb shape that is used by the green goblin
 * Author: Jassael Ruiz
 * Version: 1.0
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.Vector;
import javax.swing.Timer;

public class Bomb {

	int bit_map[] = {};
	int colors_matrix[][] = {};
	int Bomb[] = { 0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
			0x00000000, 0x00000000, 0x00000000, 0x00010000, 0x00097C00,
			0x00056000, 0x003BA000, 0x005FF000, 0x00BFE000, 0x00874000,
			0x00892000, 0x00810000, 0x03E00000, 0x0FF80000, 0x1FFC0000,
			0x3FFE0000, 0x3FFE0000, 0x7FFF0000, 0x7FFF0000, 0x7FFF0000,
			0x7FFF0000, 0x7FFF0000, 0x3FFE0000, 0x3FFE0000, 0x1FFC0000,
			0x0FF80000, 0x03E00000 };
	int b_colors_matrix[][] = {
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 5, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 8, 8, 5, 8, 8, 6, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 6, 8, 5, 8, 6, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 8, 8, 6, 13, 6, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 5, 0, 5, 13, 13, 13, 5, 5, 5, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 0, 6, 13, 6, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 6, 8, 5, 8, 6, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 6, 8, 8, 5, 8, 8, 6, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 5, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 0, 4, 4, 4, 4, 4, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 0, 4, 4, 1, 1, 4, 4, 4, 4, 4, 4, 4, 0, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 0, 4, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 0, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 4, 4, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 0, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 4, 4, 4, 1, 1, 4, 4, 4, 4, 4, 4, 4, 2, 0, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 0, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 0, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 2, 0, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 0, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 2, 2, 0, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 0, 4, 4, 4, 4, 4, 4, 2, 2, 2, 0, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 0, 4, 2, 2, 2, 2, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 } };
	double position[] = { 0, 98 };
	int velocity[] = { 0, 0 };
	Vector<Color> colors = new Vector<Color>();
	int direction = 1;
	Rectangle2D r, r2;
	Random rand;
	Timer timer;
	int delay = 0;
	double WIDTH, HEIGHT;

	public Bomb(Dimension WINDOWS_SIZE, double x) {
		// class constructor, performe some initialization
		position[0] = x;
		r = new Rectangle2D.Double(position[0] + 13, position[1] + 15, 27, 20);
		r2 = new Rectangle2D.Double(position[0], position[1] + 35, 32, 30);
		rand = new Random();
		velocity[1] = 2 + rand.nextInt(10);
		velocity[0] = 1 + rand.nextInt(3);
		loadColors();
		loadBomb();
		delay = 700 + rand.nextInt(400);
		timer = new Timer(delay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				velocity[0] *= -1;
			}
		});
		WIDTH = WINDOWS_SIZE.getWidth();
		HEIGHT = WINDOWS_SIZE.getHeight();
		timer.start();
	}

	public void loadBomb() {
		// init the bit map and the colors matrix for the bomb
		bit_map = new int[Bomb.length];

		for (int ren = 0; ren < Bomb.length; ren++)
			bit_map[ren] = Bomb[ren];

		colors_matrix = new int[b_colors_matrix.length][b_colors_matrix.length];

		for (int ren = 0; ren < b_colors_matrix.length; ren++)
			for (int col = 0; col < b_colors_matrix[ren].length; col++)
				colors_matrix[ren][col] = b_colors_matrix[ren][col];
	}

	public void loadColors() {
		// load the necessary colors to draw the bitmap
		// spiderman colors
		colors.add(new Color(0, 0, 0));// black
		colors.add(new Color(255, 255, 255));// white
		colors.add(new Color(15, 19, 133));// blue 1
		colors.add(new Color(97, 101, 197));// blue 2
		colors.add(new Color(115, 154, 245));// blue 3
		colors.add(new Color(155, 33, 33));// red 1
		colors.add(new Color(202, 28, 28));// red 2
		colors.add(new Color(237, 113, 113));// red 3
		colors.add(new Color(209, 227, 236));// gray
		// green goblin colors
		colors.add(new Color(129, 8, 114));// purple1
		colors.add(new Color(195, 60, 151));// purple2
		colors.add(new Color(51, 151, 46));// green1
		colors.add(new Color(42, 193, 36));// green2
		colors.add(new Color(228, 202, 42));// yellow1
	}

	public void updatePosition() {
		// update and controls the position of the bit map
		if (position[0] + velocity[0] + r2.getBounds().getMaxX() + 15 > WIDTH
				|| position[0] - Math.abs(velocity[0]) < 0)
			velocity[0] *= -1;
		position[1] += velocity[1];
		position[0] += velocity[0];
	}

	public void draw(Graphics2D g2) {
		// draws the bit map on the canvas
		Line2D line = new Line2D.Double();
		double pos[] = position.clone();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// bomb bounds
		g2.setColor(new Color(209, 227, 236));
		g2.setStroke(new BasicStroke(1));
		// mecha
		r = new Rectangle2D.Double(position[0] + 13, position[1] + 15, 27, 20);
		// body
		r2 = new Rectangle2D.Double(position[0], position[1] + 35, 32, 30);
		for (int i = 0; i < bit_map.length; i++) {
			for (int j = 0; j < 32; j++) {
				int x = bit_map[i];
				int color = colors_matrix[i][j];
				Color c = colors.get(color);
				int operacion = x & (0x80000000 >>> j);

				if (operacion != 0) {
					g2.setStroke(new BasicStroke(3f));
					g2.setColor(c);
					line = new Line2D.Double(pos[0] + j, pos[1] + i,
							pos[0] + j, pos[1] + i);
					g2.draw(line);
				}
				pos[0] += 1;
			}
			pos[1] += 1;
			pos[0] = position[0];
		}
	}
}
