/*
 * SpiderMan.java allows to create, control and draw a bit map that represents the spiderman character
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
import java.util.Vector;

public class SpiderMan {

	int bit_map[] = {};
	int colors_matrix[][] = {};
	int right_spiderman[] = { 0x000F8000, 0x003FC000, 0x007FC000, 0x00FFE000,
			0x01FFE000, 0x01FFE000, 0x01FFF000, 0x01FFF000, 0x01FFF000,
			0x00FFE000, 0x01FFE000, 0x03FFF000, 0x07FFF800, 0x3FFFFC00,
			0x7FFFFE00, 0xFFFFFE00, 0xFFFFFE00, 0xFFFFFC00, 0xFEFFE000,
			0x6CFFF000, 0x01FFF800, 0x07FFF800, 0x0FF0F800, 0x1FE1FC00,
			0x3FC1FE00, 0x3F81FE00, 0x1F00FC00, 0x00000000, 0x00000000,
			0x00000000, 0x00000000, 0x00000000 };
	int left_spiderman[] = { 0x0001F000, 0x0003FC00, 0x0003FE00, 0x0007FF00,
			0x0007FF80, 0x0007FF80, 0x000FFF80, 0x000FFF80, 0x000FFF80,
			0x0007FF00, 0x0007FF80, 0x000FFFC0, 0x001FFFE0, 0x003FFFFC,
			0x007FFFFE, 0x007FFFFF, 0x007FFFFF, 0x003FFFFF, 0x0007FF7F,
			0x000FFF36, 0x001FFF80, 0x001FFFE0, 0x001F0FF0, 0x003F87F8,
			0x007F83FC, 0x007F81FC, 0x003F00F8, 0x00000000, 0x00000000,
			0x00000000, 0x00000000, 0x00000000 };
	int lsp_colors_matrix[][] = {
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 7, 7, 5, 7, 7, 0, 0,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 7, 5, 6, 0, 6, 5, 6,
					0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 7, 5, 6, 0, 1, 0, 6, 6,
					6, 0, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 5, 0, 1, 1, 0, 5, 5,
					5, 6, 0, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 1, 0, 1, 1, 1, 0, 6, 6,
					6, 6, 0, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 7, 5, 0, 1, 1, 1, 0, 5, 5,
					5, 5, 0, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 5, 7, 6, 0, 1, 1, 0, 5, 6,
					6, 6, 0, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 6, 6, 6, 6, 0, 0, 6, 6, 5,
					6, 6, 0, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 6, 5, 6, 5, 6, 6,
					5, 0, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 5, 6, 5, 5, 6, 6, 5, 6,
					0, 0, 0, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 5, 0, 7, 6, 5, 6, 6, 6, 0,
					7, 7, 5, 0, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 7, 5, 6, 0, 0, 0, 0, 6, 6, 6,
					5, 6, 5, 6, 0, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 5, 3, 3, 0, 0, 0, 0, 6, 5, 5, 6,
					7, 5, 6, 5, 6, 0, 0, 0, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 7, 7, 5, 0, 0, 0, 0, 7, 5, 6, 5, 6,
					0, 7, 3, 3, 6, 7, 5, 6, 0, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 7, 5, 6, 0, 5, 0, 0, 0, 6, 4, 4, 4,
					2, 0, 2, 3, 7, 5, 6, 5, 5, 0 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 5, 6, 6, 0, 0, 7, 0, 6, 6, 4, 3, 3,
					3, 2, 0, 7, 5, 7, 5, 6, 6, 0 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 7, 6, 5, 0, 5, 6, 3, 2,
					2, 2, 0, 0, 5, 7, 6, 5, 5, 0 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 2, 3, 5, 6, 6, 5, 6, 5,
					6, 0, 8, 0, 6, 5, 0, 6, 6, 0 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 2, 2, 3, 3, 6, 6, 5, 6, 3,
					2, 0, 8, 8, 0, 0, 8, 0, 0, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 3, 4, 4, 3, 2, 2, 2, 3, 3, 3,
					3, 3, 0, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 3, 4, 3, 3, 0, 0, 0, 0, 2, 3,
					4, 4, 4, 0, 0, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 2, 3, 3, 0, 8, 8, 8, 8, 0, 2,
					3, 4, 4, 3, 0, 0, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 7, 5, 5, 3, 2, 0, 8, 8, 8, 8, 0,
					2, 3, 2, 7, 5, 6, 0, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 7, 5, 6, 5, 6, 6, 0, 8, 8, 8, 8, 8,
					0, 0, 7, 5, 7, 5, 6, 0, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 5, 6, 6, 5, 6, 6, 0, 8, 8, 8, 8, 8,
					8, 0, 6, 5, 6, 5, 6, 0, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8,
					8, 8, 0, 0, 0, 0, 0, 8, 8, 8 },
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
	int rsp_colors_matrix[][] = {
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 7, 7, 5, 7, 7, 0, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 6, 5, 6, 0, 6, 5, 7, 0, 8, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 0, 6, 6, 6, 0, 1, 0, 6, 5, 7, 0, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 0, 6, 5, 5, 5, 0, 1, 1, 0, 5, 0, 0, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 0, 6, 6, 6, 6, 0, 1, 1, 1, 0, 1, 0, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 0, 5, 5, 5, 5, 0, 1, 1, 1, 0, 5, 7, 0, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 0, 6, 6, 6, 5, 0, 1, 1, 0, 6, 7, 5, 0, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 0, 6, 6, 5, 6, 6, 0, 0, 6, 6, 6, 6, 0, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 8, 0, 5, 6, 6, 5, 6, 5, 6, 0, 0, 0, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 6, 5, 6, 6, 5, 5, 6, 5, 0, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 0, 5, 7, 7, 0, 6, 6, 6, 5, 6, 7, 0, 5, 0, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 0, 6, 5, 6, 5, 6, 6, 6, 0, 0, 0, 0, 6, 5, 7, 0, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 0, 0, 0, 6, 5, 6, 5, 7, 6, 5, 5, 6, 0, 0, 0, 0, 3, 3, 5, 0,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 6, 5, 7, 6, 3, 3, 7, 0, 6, 5, 6, 5, 7, 0, 0, 0, 0, 5, 7, 7,
					0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 0, 5, 5, 6, 5, 7, 3, 2, 0, 2, 4, 4, 4, 6, 0, 0, 0, 5, 0, 6, 5, 7,
					0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 0, 6, 6, 5, 7, 5, 7, 0, 2, 3, 3, 3, 4, 6, 6, 0, 7, 0, 0, 6, 6, 5,
					0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 0, 5, 5, 6, 7, 5, 0, 0, 2, 2, 2, 3, 6, 5, 0, 5, 6, 7, 0, 0, 0, 0,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 0, 6, 6, 0, 5, 6, 0, 8, 0, 6, 5, 6, 5, 6, 6, 5, 3, 2, 0, 8, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 0, 0, 8, 0, 0, 8, 8, 0, 2, 3, 6, 5, 6, 6, 3, 3, 2, 2, 0, 8, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 8, 8, 0, 3, 3, 3, 3, 3, 2, 2, 2, 3, 4, 4, 3, 0, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 8, 0, 0, 4, 4, 4, 3, 2, 0, 0, 0, 0, 3, 3, 4, 3, 0, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 8, 0, 0, 3, 4, 4, 3, 2, 0, 8, 8, 8, 8, 0, 3, 3, 2, 0, 8,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 0, 6, 5, 7, 2, 3, 2, 0, 8, 8, 8, 8, 0, 2, 3, 5, 5, 7, 0,
					8, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 0, 6, 5, 7, 5, 7, 0, 0, 8, 8, 8, 8, 8, 0, 6, 6, 5, 6, 5, 7,
					0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 0, 6, 5, 6, 5, 6, 0, 8, 8, 8, 8, 8, 8, 0, 6, 6, 5, 6, 6, 5,
					0, 8, 8, 8, 8, 8, 8, 8, 8, 8 },
			{ 8, 8, 8, 0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 0, 0,
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
	double position[] = { 300, 0 };
	int velocity[] = { 7, 0 };
	int WIDTH, HEIGHT;
	Vector<Color> colors = new Vector<Color>();
	int direction = 1;
	Rectangle2D r, r2;

	public SpiderMan(Dimension WINDOWS_SIZE) {
		// class constructor, perform some initialization
		WIDTH = WINDOWS_SIZE.width;
		HEIGHT = WINDOWS_SIZE.height;
		loadColors();
		loadRightSpiderman();
		if (direction == 1) {
			// head
			r = new Rectangle2D.Double(position[0] + 20, position[1] - 2, 40,
					32);
			// body
			r2 = new Rectangle2D.Double(position[0] - 3, position[1] + 32, 70,
					47);
		} else {
			// head
			r = new Rectangle2D.Double(position[0] + 35, position[1] - 2, 40,
					32);
			// body
			r2 = new Rectangle2D.Double(position[0] + 25, position[1] + 32, 70,
					47);
		}
	}

	public void loadRightSpiderman() {
		// load the right side of the bit map
		bit_map = new int[right_spiderman.length];

		for (int ren = 0; ren < right_spiderman.length; ren++)
			bit_map[ren] = right_spiderman[ren];

		colors_matrix = new int[rsp_colors_matrix.length][rsp_colors_matrix.length];

		for (int ren = 0; ren < rsp_colors_matrix.length; ren++)
			for (int col = 0; col < rsp_colors_matrix[ren].length; col++)
				colors_matrix[ren][col] = rsp_colors_matrix[ren][col];
	}

	public void loadLeftSpiderman() {
		// load the left side of the bit map
		bit_map = new int[left_spiderman.length];

		for (int ren = 0; ren < left_spiderman.length; ren++)
			bit_map[ren] = left_spiderman[ren];

		colors_matrix = new int[lsp_colors_matrix.length][lsp_colors_matrix.length];

		for (int ren = 0; ren < lsp_colors_matrix.length; ren++)
			for (int col = 0; col < lsp_colors_matrix[ren].length; col++)
				colors_matrix[ren][col] = lsp_colors_matrix[ren][col];
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
	}

	public void updatePosition(int direction, Dimension WINDOWS_SIZE) {
		// update and control the position of the bit map
		if (this.direction == 2 && direction == 1) {
			position[0] += 23;
		}

		if (this.direction == 1 && direction == 2) {
			position[0] -= 23;
		}

		this.direction = direction;

		if (direction == 1) {
			if (r2.getMaxX() + velocity[0] > WINDOWS_SIZE.width - 5) {
				velocity[0] = 0;
			}
			position[0] += velocity[0];
			position[1] += velocity[1];
		} else {
			if (r2.getX() - Math.abs(velocity[0]) < 0) {
				velocity[0] = 0;
			}
			position[0] -= velocity[0];
			position[1] -= velocity[1];
		}
	}

	public void draw(Graphics2D g2) {
		// draw the bit map on the canvas
		Line2D line = new Line2D.Double();
		double pos[] = position.clone();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(new Color(209, 227, 236));
		g2.setStroke(new BasicStroke(1));
		if (direction == 1) {
			// head
			r = new Rectangle2D.Double(position[0] + 20, position[1] - 2, 40,
					32);
			// body
			r2 = new Rectangle2D.Double(position[0] - 3, position[1] + 32, 70,
					47);
		} else {
			// head
			r = new Rectangle2D.Double(position[0] + 35, position[1] - 2, 40,
					32);
			// body
			r2 = new Rectangle2D.Double(position[0] + 25, position[1] + 32, 70,
					47);
		}

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
				pos[0] += 2;
			}
			pos[1] += 2;
			pos[0] = position[0];
		}
	}
}
