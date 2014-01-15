/*
 * Power.java allows to create, control and draw a bit map that represents the spiderman´s power
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

public class Power {

	int bit_map[] = {};
	int colors_matrix[][] = {};

	int Power[] = { 0x00FFC000, 0x01FFE000, 0x03FFF000, 0x07FFF800, 0x0FFFFC00,
			0x1FFFFE00, 0x3FFFFF00, 0x3FFFFF00, 0x3FFFFF00, 0x3FFFFF00,
			0x3FFFFF00, 0x3FFFFF00, 0x3FFFFF00, 0x3FFFFF00, 0x3FFFFF00,
			0x3FFFFF00, 0x1FFFFE00, 0x0FFFFC00, 0x07FFF800, 0x03FFF000,
			0x01FFE000, 0x00FFD000, 0x00000000, 0x00000000, 0x00000000,
			0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
			0x00000000, 0x00000000 };
	int p_colors_matrix[][] = {
			{ 8, 8, 8, 8, 8, 8, 8, 8, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,
					18, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 18, 18, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 18, 18,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 18, 18, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 18,
					18, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 18, 18, 1, 1, 1, 1, 1, 1, 1, 1, 18, 18, 18, 18, 1, 1,
					18, 18, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 18, 18, 1, 1, 1, 1, 1, 1, 1, 18, 18, 18, 18, 18, 18, 1,
					1, 1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 18, 18, 1, 1, 1, 1, 1, 1, 18, 18, 18, 18, 18, 18, 18, 18,
					1, 1, 1, 1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 18, 18, 1, 1, 1, 1, 1, 1, 18, 18, 18, 18, 18, 18, 18, 18,
					1, 1, 1, 1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 18, 18, 1, 1, 1, 1, 1, 18, 18, 18, 18, 18, 18, 18, 18, 18,
					1, 1, 1, 1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 18, 18, 1, 1, 1, 1, 18, 18, 18, 18, 18, 18, 18, 1, 1, 1, 1,
					1, 1, 1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 18, 18, 1, 1, 1, 1, 18, 18, 18, 18, 18, 18, 18, 1, 1, 1, 1,
					1, 1, 1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 18, 18, 1, 1, 18, 18, 18, 18, 18, 1, 1, 18, 18, 18, 18, 18,
					1, 1, 1, 1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 18, 18, 1, 1, 18, 18, 18, 1, 1, 1, 1, 18, 18, 18, 18, 18,
					1, 1, 1, 1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 18, 18, 1, 1, 18, 18, 18, 1, 1, 1, 1, 1, 1, 18, 18, 18, 1,
					1, 1, 1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 18, 18, 1, 1, 18, 18, 18, 18, 1, 1, 1, 1, 1, 18, 18, 18, 1,
					1, 1, 1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 18, 18, 1, 1, 1, 1, 18, 18, 1, 1, 1, 18, 18, 18, 18, 18, 1,
					1, 1, 1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 18, 18, 1, 1, 1, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,
					1, 1, 1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 18, 18, 1, 1, 18, 18, 18, 18, 18, 18, 1, 1, 1, 1, 1,
					1, 18, 18, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 18, 18, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 18,
					18, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 18, 18, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 18, 18,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,
					18, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
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

	public Power(Dimension WINDOWS_SIZE, double x, double y) {
		// class constructor, perform some initialization
		position[0] = x;
		position[1] = y;
		r = new Rectangle2D.Double(position[0], position[1], 25, 20);
		rand = new Random();
		velocity[1] = -1 * (8 + rand.nextInt(10));
		loadColors();
		loadPower();
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

	public void loadPower() {
		// initializes the bit map and the colors matrix for the spiderman´s
		// power
		bit_map = new int[Power.length];

		for (int ren = 0; ren < Power.length; ren++)
			bit_map[ren] = Power[ren];

		colors_matrix = new int[p_colors_matrix.length][p_colors_matrix.length];

		for (int ren = 0; ren < p_colors_matrix.length; ren++)
			for (int col = 0; col < p_colors_matrix[ren].length; col++)
				colors_matrix[ren][col] = p_colors_matrix[ren][col];
	}

	public void loadColors() {
		// load necessary colors to draw the bit map
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
		// pumpking colors
		colors.add(new Color(57, 168, 116));// green1
		colors.add(new Color(102, 255, 184));// green2
		colors.add(new Color(214, 137, 28));// orange1
		colors.add(new Color(251, 211, 156));// orange2
		// power spider man colors
		colors.add(new Color(135, 236, 255));// light blue
	}

	public void updatePosition() {
		// update and control the velocity and position of the bit map
		if (position[0] + velocity[0] + r.getBounds().getMaxX() + 15 > WIDTH
				|| position[0] - Math.abs(velocity[0]) < 0)
			velocity[0] *= -1;
		position[1] += velocity[1];
		position[0] += velocity[0];
	}

	public void draw(Graphics2D g2) {
		// draw the bit map on the canvas
		Line2D line = new Line2D.Double();
		double pos[] = position.clone();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// fire bounds
		g2.setColor(new Color(209, 227, 236));
		g2.setStroke(new BasicStroke(1));
		r = new Rectangle2D.Double(position[0], position[1] - 2, 50, 47);
		for (int i = 0; i < bit_map.length; i++) {
			for (int j = 0; j < 32; j++) {
				int x = bit_map[i];
				int color = colors_matrix[i][j];
				Color c = colors.get(color);
				int operacion = x & (0x80000000 >>> j);

				if (operacion != 0) {
					g2.setStroke(new BasicStroke(2.5f));
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
