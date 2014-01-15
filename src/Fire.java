/*
 * Fire.java allows to create, control and draw a bit map that represents a spiderman´s bullet
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

public class Fire {

	int bit_map[] = {};
	int colors_matrix[][] = {};
	int Fire[] = { 0x00000000, 0x00000000, 0x00FFF000, 0x01FFF800, 0x03FFFC00,
			0x07FFFE00, 0x0FFFFF00, 0x0FFFFF00, 0x0FFFFF00, 0x0FFFFF00,
			0x0FFFFF00, 0x0FFFFF00, 0x07FFFE00, 0x03FFFC00, 0x01FFF800,
			0x00FFF000, 0x007FE000, 0x003FC000, 0x001F8000, 0x00060000,
			0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
			0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
			0x00000000, 0x00000000 };
	int f_colors_matrix[][] = {
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 0, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 3,
					0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3,
					3, 0, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 3, 3, 4, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 4, 3,
					3, 0, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 3, 3, 4, 4, 6, 7, 7, 7, 7, 7, 7, 7, 7, 6, 4, 4, 3,
					3, 0, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 3, 3, 4, 4, 6, 7, 13, 13, 13, 13, 13, 13, 7, 6, 4,
					4, 3, 3, 0, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 3, 3, 4, 4, 6, 7, 13, 1, 1, 1, 1, 13, 7, 6, 4, 4,
					3, 3, 0, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 3, 3, 4, 4, 6, 7, 13, 1, 1, 1, 1, 13, 7, 6, 4, 4,
					3, 3, 0, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 0, 3, 3, 4, 4, 6, 7, 7, 13, 13, 7, 7, 6, 4, 4, 3,
					3, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 0, 3, 3, 4, 4, 6, 6, 7, 7, 6, 6, 4, 4, 3, 3, 0,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 0, 3, 3, 4, 4, 4, 6, 6, 4, 4, 4, 3, 3, 0, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 0, 3, 3, 4, 4, 4, 4, 4, 4, 3, 3, 0, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 3, 3, 3, 4, 4, 3, 3, 3, 0, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 3, 3, 3, 3, 3, 3, 0, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 3, 3, 0, 0, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 8, 8, 8, 8, 8, 8, 8,
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
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 } };
	double position[] = { 0, 0 };
	int velocity[] = { 0, 0 };
	Vector<Color> colors = new Vector<Color>();
	int direction = 1;
	Rectangle2D r;
	Random rand;
	Timer timer2;
	int delay2 = 0;
	double WIDTH, HEIGHT;

	public Fire(Dimension WINDOWS_SIZE, double x, double y) {
		// class constructor, perform some initialization
		position[0] = x;
		position[1] = y;
		r = new Rectangle2D.Double(position[0], position[1], 25, 20);
		rand = new Random();
		velocity[1] = -1 * (8 + rand.nextInt(10));
		loadColors();
		loadFire();
		delay2 = 700 + rand.nextInt(400);
		timer2 = new Timer(delay2, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				velocity[0] *= -1;
			}
		});
		WIDTH = WINDOWS_SIZE.getWidth();
		HEIGHT = WINDOWS_SIZE.getHeight();
		timer2.start();
	}

	public void loadFire() {
		// initializes the bit map and the colors matrix for the Fire
		bit_map = new int[Fire.length];

		for (int ren = 0; ren < Fire.length; ren++)
			bit_map[ren] = Fire[ren];

		colors_matrix = new int[f_colors_matrix.length][f_colors_matrix.length];

		for (int ren = 0; ren < f_colors_matrix.length; ren++)
			for (int col = 0; col < f_colors_matrix[ren].length; col++)
				colors_matrix[ren][col] = f_colors_matrix[ren][col];
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
		// green globin colors
		colors.add(new Color(129, 8, 114));// purple1
		colors.add(new Color(195, 60, 151));// purple2
		colors.add(new Color(51, 151, 46));// green1
		colors.add(new Color(42, 193, 36));// green2
		colors.add(new Color(228, 202, 42));// yellow1
	}

	public void updatePosition() {
		// update and controls the position of the bit map
		if (position[0] + velocity[0] + r.getBounds().getMaxX() + 15 > WIDTH
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
		// fire bounds
		g2.setColor(new Color(209, 227, 236));
		g2.setStroke(new BasicStroke(1));
		r = new Rectangle2D.Double(position[0], position[1], 25, 20);
		for (int i = 0; i < bit_map.length; i++) {
			for (int j = 0; j < 32; j++) {
				int x = bit_map[i];
				int color = colors_matrix[i][j];
				Color c = colors.get(color);
				int operacion = x & (0x80000000 >>> j);

				if (operacion != 0) {
					g2.setStroke(new BasicStroke(2f));
					g2.setColor(c);
					line = new Line2D.Double(pos[0] + j, pos[1] + i,
							pos[0] + j, pos[1] + i);
					g2.draw(line);
				}
			}
			pos[0] = position[0];
		}
	}
}
