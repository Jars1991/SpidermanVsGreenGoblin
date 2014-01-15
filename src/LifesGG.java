/*
 * LifesGG.java allows to create, control and draw a bit map that represents the green goblin´s lifes
 * Author: Jassael Ruiz
 * Version: 1.0
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.util.Vector;

public class LifesGG {

	int bit_map[] = {};
	int colors_matrix[][] = {};
	int Life[] = { 0x00000000, 0x0F81F000, 0x1FC3F800, 0x3FE7FC00, 0x7FFFFE00,
			0x7FFFFE00, 0x7FFFFE00, 0x7FFFFE00, 0x7FFFFE00, 0x7FFFFE00,
			0x3FFFFC00, 0x3FFFFC00, 0x1FFFF800, 0x0FFFF000, 0x07FFE000,
			0x03FFC000, 0x01FF8000, 0x00FF0000, 0x007E0000, 0x003C0000,
			0x00180000, 0x1E000000, 0x1E000000, 0x1E000000, 0x00000000,
			0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
			0x00000000, 0x00000000 };
	int l_colors_matrix[][] = {
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 0, 9, 9, 9, 9, 9, 0, 8, 8, 8, 8, 0, 9, 9, 9, 9, 9, 0, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 0, 9, 9, 9, 9, 9, 9, 9, 0, 8, 8, 0, 9, 9, 9, 9, 9, 9, 9, 0,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 9, 9, 1, 1, 1, 9, 9, 9, 9, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9,
					0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 9, 9, 1, 10, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
					9, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 9, 9, 1, 1, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
					0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
					0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 9, 9, 1, 1, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
					0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 9, 9, 1, 10, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9,
					9, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 9, 9, 1, 1, 1, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 0, 9, 9, 9, 9, 9, 9, 9, 9, 0, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 0, 9, 9, 9, 9, 9, 9, 0, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 9, 9, 9, 9, 0, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 9, 9, 0, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8,
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
	Vector<Color> colors = new Vector<Color>();
	int direction = 1;

	public LifesGG(double x, double y) {
		// class constructor, perform some initialization
		position[0] = x;
		position[1] = y;
		loadColors();
		loadLife();
	}

	public void loadLife() {
		// initializes the bit map and the colors matrix for the green goblin´s
		// lifes
		bit_map = new int[Life.length];

		for (int ren = 0; ren < Life.length; ren++)
			bit_map[ren] = Life[ren];

		colors_matrix = new int[l_colors_matrix.length][l_colors_matrix.length];

		for (int ren = 0; ren < l_colors_matrix.length; ren++)
			for (int col = 0; col < l_colors_matrix[ren].length; col++)
				colors_matrix[ren][col] = l_colors_matrix[ren][col];
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

	public void draw(Graphics2D g2) {
		// draw the bit map on the canvas
		Line2D line = new Line2D.Double();
		double pos[] = position.clone();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		for (int i = 0; i < bit_map.length - 11; i++) {
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
