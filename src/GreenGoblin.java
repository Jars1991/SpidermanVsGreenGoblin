/*
 * GreenGoblin.java allows to create, control and draw a bit map that represents the enemy of spiderman
 * Author: Jassael Ruiz
 * Version: 1.0
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.Vector;

public class GreenGoblin {

	int bit_map[] = {};
	int colors_matrix[][] = {};
	int direction;
	int right_greengoblin[] = { 0x00000000, 0x00000000, 0x00000000, 0x00001F80,
			0x00003FC0, 0x00007FE0, 0x0000FFF0, 0x0000FFF0, 0x0001FFF8,
			0x0001FFF8, 0x0001FFF8, 0x0001FFF0, 0x0001FFF0, 0x0003FFC0,
			0x00037FE0, 0x0000FFF0, 0x0000FFF0, 0x0000FFF0, 0x00007FE0,
			0x00003FC0, 0x00003FC0, 0x000039C0, 0x00000000, 0x00000000,
			0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
			0x00000000, 0x00000000, 0x00000000 };
	int left_greengoblin[] = { 0x00000000, 0x00000000, 0x00000000, 0x01F80000,
			0x03FC0000, 0x07FE0000, 0x0FFF0000, 0x0FFF0000, 0x1FFF8000,
			0x1FFF8000, 0x1FFF8000, 0x0FFF8000, 0x0FFF8000, 0x03FFC000,
			0x07FEC000, 0x0FFF0000, 0x0FFF0000, 0x0FFF0000, 0x07FE0000,
			0x03FC0000, 0x03FC0000, 0x039C0000, 0x00000000, 0x00000000,
			0x00000000, 0x00000000, 0x00000000, 0x00000000, 0x00000000,
			0x00000000, 0x00000000, 0x00000000 };
	int lgg_colors_matrix[][] = {
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 0, 9, 9, 9, 9, 10, 10, 0, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 0, 9, 9, 9, 10, 10, 10, 10, 10, 0, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 9, 9, 11, 10, 10, 10, 12, 10, 10, 10, 0, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 9, 11, 12, 12, 12, 12, 12, 12, 10, 10, 0, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 0, 0, 11, 12, 13, 12, 12, 12, 13, 12, 12, 10, 0, 0, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 0, 11, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 0, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 0, 11, 11, 12, 12, 0, 0, 0, 12, 12, 12, 12, 12, 0, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 11, 11, 12, 12, 12, 12, 12, 12, 12, 12, 0, 10, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 0, 11, 11, 11, 11, 12, 12, 12, 12, 0, 0, 10, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 0, 0, 9, 9, 10, 10, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 0, 11, 3, 10, 10, 10, 10, 10, 12, 0, 8, 9, 9, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 11, 0, 9, 3, 10, 10, 10, 10, 0, 12, 0, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 11, 0, 9, 10, 3, 4, 10, 10, 0, 12, 0, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 9, 0, 9, 9, 10, 2, 2, 2, 0, 12, 0, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 3, 4, 4, 0, 0, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 0, 11, 12, 9, 10, 11, 12, 0, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 0, 9, 10, 0, 0, 9, 10, 0, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 0, 0, 0, 8, 8, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8,
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
	int rgg_colors_matrix[][] = {
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0,
					0, 0, 0, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 10, 10,
					9, 9, 9, 9, 0, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 10, 10, 10,
					10, 10, 9, 9, 9, 0, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 10, 10, 10,
					12, 10, 10, 10, 11, 9, 9, 0, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 10, 10, 12,
					12, 12, 12, 12, 12, 11, 9, 0, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 10, 12, 12,
					13, 12, 12, 12, 13, 12, 11, 0, 0, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 12, 12, 12, 12,
					12, 12, 12, 12, 12, 12, 12, 11, 0, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 12, 12, 12, 12,
					12, 0, 0, 0, 12, 12, 11, 11, 0, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 10, 0, 12, 12, 12,
					12, 12, 12, 12, 12, 11, 11, 0, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 10, 0, 0, 12, 12,
					12, 12, 11, 11, 11, 11, 0, 0, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 10, 10, 9, 9, 0, 0, 0,
					0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 8, 0, 12, 10, 10,
					10, 10, 10, 3, 11, 0, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 12, 0, 10, 10,
					10, 10, 3, 9, 0, 11, 0, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 12, 0, 10, 10,
					4, 3, 10, 9, 0, 11, 0, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 12, 0, 2, 2,
					2, 10, 9, 9, 0, 9, 0, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 4, 4, 3,
					0, 0, 0, 0, 0, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 12, 11,
					10, 9, 12, 11, 0, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 10, 9,
					0, 0, 10, 9, 0, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 8,
					8, 0, 0, 0, 8, 8, 8, 8, 8, 8 },
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
	double position[] = { 300, -10 };
	int velocity[] = { 0, 0 };
	double WIDTH, HEIGHT;
	Vector<Color> colors = new Vector<Color>();
	Rectangle2D r, r2;
	Random rand;

	public GreenGoblin(Dimension WINDOWS_SIZE) {
		// class constructor, perform some initialization
		WIDTH = WINDOWS_SIZE.width;
		HEIGHT = WINDOWS_SIZE.height;
		rand = new Random();
		direction = 1 + rand.nextInt(2);
		if (direction == 2) {
			r = new Rectangle2D.Double(position[0] + 10, position[1] + 10, 80,
					65);
			r2 = new Rectangle2D.Double(position[0] + 20, position[1] + 77, 55,
					32);
		} else {
			r2 = new Rectangle2D.Double(position[0] + 75, position[1] + 75, 63,
					35);
			r = new Rectangle2D.Double(position[0] + 65, position[1] + 10, 80,
					63);
		}
		loadColors();
		setVelocity();
	}

	public void restart() {
		// restart the position of the bit map to the originals values
		position[0] = 300;
		position[1] = -10;
	}

	private void setVelocity() {
		// set the bit map velocity
		if (direction == 2) {
			// left
			velocity[0] = -5;
			loadLeftGreengoblin();
		} else {
			// right
			velocity[0] = 5;
			loadRightGreengoblin();
		}
	}

	public void loadRightGreengoblin() {
		// load the right side of the bit map
		bit_map = new int[right_greengoblin.length];

		for (int ren = 0; ren < right_greengoblin.length; ren++)
			bit_map[ren] = right_greengoblin[ren];

		colors_matrix = new int[rgg_colors_matrix.length][rgg_colors_matrix.length];

		for (int ren = 0; ren < rgg_colors_matrix.length; ren++)
			for (int col = 0; col < rgg_colors_matrix[ren].length; col++)
				colors_matrix[ren][col] = rgg_colors_matrix[ren][col];
	}

	public void loadLeftGreengoblin() {
		// load the left side of the bit map
		bit_map = new int[left_greengoblin.length];

		for (int ren = 0; ren < left_greengoblin.length; ren++)
			bit_map[ren] = left_greengoblin[ren];

		colors_matrix = new int[lgg_colors_matrix.length][lgg_colors_matrix.length];

		for (int ren = 0; ren < lgg_colors_matrix.length; ren++)
			for (int col = 0; col < lgg_colors_matrix[ren].length; col++)
				colors_matrix[ren][col] = lgg_colors_matrix[ren][col];
	}

	public void loadColors() {
		// load the necessary colors to draw the bit map
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

	public void updatePosition(double width) {
		// update and control the position of the bit map
		checkBounds(direction);
		position[0] += velocity[0];
		position[1] += velocity[1];
	}

	private void checkBounds(int dir) {
		// control the direction of the bit map
		if (r.getX() - Math.abs(velocity[0]) <= 0
				|| r.getX() + Math.abs(velocity[0]) + r.getWidth() >= WIDTH) {
			velocity[0] *= -1;
			switch (dir) {
			case 2:
				loadRightGreengoblin();
				direction = 1;
				break;
			case 1:
				loadLeftGreengoblin();
				direction = 2;
				break;
			}
		}
	}

	public void draw(Graphics2D g2) {
		// draw the bit map on the canvas
		Line2D line = new Line2D.Double();
		double pos[] = position.clone();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// green goblin bounds
		g2.setStroke(new BasicStroke(1));
		g2.setColor(new Color(209, 227, 236));
		if (direction == 2) {
			// head
			g2.setStroke(new BasicStroke(1));
			r = new Rectangle2D.Double(position[0] + 10, position[1] + 10, 80,
					65);
			// body
			r2 = new Rectangle2D.Double(position[0] + 15, position[1] + 77, 65,
					32);
		} else {
			// head
			g2.setStroke(new BasicStroke(1));
			r = new Rectangle2D.Double(position[0] + 65, position[1] + 10, 80,
					63);
			// body
			r2 = new Rectangle2D.Double(position[0] + 75, position[1] + 75, 63,
					35);
		}
		for (int i = 0; i < bit_map.length; i++) {
			for (int j = 0; j < 32; j++) {
				int x = bit_map[i];
				int color = colors_matrix[i][j];
				Color c = colors.get(color);
				int operacion = x & (0x80000000 >>> j);

				if (operacion != 0) {
					g2.setStroke(new BasicStroke(5f));
					g2.setColor(c);
					line = new Line2D.Double(pos[0] + j, pos[1] + i,
							pos[0] + j, pos[1] + i);
					g2.draw(line);
				}
				pos[0] += 4;
			}
			pos[1] += 4;
			pos[0] = position[0];
		}
	}
}
