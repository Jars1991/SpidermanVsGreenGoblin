/*
 * Help.java shows the necessary directions to play the game
 * Author: Jassael Ruiz
 * Version: 1.0
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

public class Help extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel panel;
	Container cont;
	JTextPane info;
	String message;
	JButton aceptar;
	boolean play, TranslucencySupported = true;
	Game game;

	public Help(String title, final Game g) {
		// class constructor
		game = g;
		play = true;
		setTitle(title);
		setSize(440, 410);
		cont = getContentPane();
		cont.setLayout(null);
		panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setSize(getSize());
		message = "<<< Instrucciones del juego >>>\n\n 1. Usa las teclas de direccion del teclado\n"
				+ "para mover al personaje de SpiderMan de derecha a izquierda\n"
				+ "evitando contacto con las bombas azules y las calabazas\n"
				+ "naranjas que lanza el duende verde.\n\n"
				+ "2. Usa la tecla Z del teclado para disparar con SpiderMan \n"
				+ "e intentar dañar al duende verde.\n\n"
				+ "3. Usa la barra espaciadora para lanzar el poder secreto de spiderman\n"
				+ "el cual destruira a 3 enemigos.\n\n"
				+ "4. Presiona F2 para cambiar al modo facil.\n\n"
				+ "5. Presiona F3 para cambiar al modo medio.\n\n"
				+ "6. Presiona F4 para cambiar al modo dificil.\n\n"
				+ "7. Presiona F5 para cambiar al modo muy dificil\n\n"
				+ "8. Presiona F1 para mostrar la ayuda";
		info = new JTextPane();
		info.setFont(new Font("Arial", Font.BOLD, 12));
		info.setBackground(Color.black);
		info.setForeground(Color.white);
		info.setSize(getSize());
		info.setText(message);
		info.setEditable(false);
		panel.add(info, BorderLayout.CENTER);
		cont.add(panel);
		aceptar = new JButton("START GAME");
		panel.add(aceptar);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				g.play();
				g.sound.stop("intro");
				setVisible(false);
			}
		});
	}

	private Help(String title) {
		// override constructor for internal testing
		play = true;
		setTitle(title);
		setSize(440, 410);
		cont = getContentPane();
		cont.setLayout(null);
		panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setSize(getSize());
		message = "<<< Instrucciones del juego >>>\n\n 1. Usa las teclas de direccion del teclado\n"
				+ "para mover al personaje de SpiderMan de derecha a izquierda\n"
				+ "evitando contacto con las bombas azules y las calabazas\n"
				+ "naranjas que lanza el duende verde.\n\n"
				+ "2. Usa la tecla Z del teclado para disparar con SpiderMan \n"
				+ "e intentar dañar al duende verde.\n\n"
				+ "3. Usa la barra espaciadora para lanzar el poder secreto de spiderman\n"
				+ "el cual destruira a 3 enemigos.\n\n"
				+ "4. Presiona F2 para cambiar al modo facil.\n\n"
				+ "5. Presiona F3 para cambiar al modo medio.\n\n"
				+ "6. Presiona F4 para cambiar al modo dificil.\n\n"
				+ "7. Presiona F5 para cambiar al modo muy dificil\n\n"
				+ "8. Presiona F1 para mostrar la ayuda";
		info = new JTextPane();
		info.setFont(new Font("Arial", Font.BOLD, 12));
		info.setBackground(Color.black);
		info.setForeground(Color.white);
		info.setSize(getSize());
		info.setText(message);
		info.setEditable(false);
		panel.add(info, BorderLayout.CENTER);
		cont.add(panel);
		aceptar = new JButton("START GAME");
		panel.add(aceptar);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				setVisible(false);
			}
		});
	}

	public void showHelp(final Help window) {
		// shows the JFrame that contains the game´s instructions

		// Determine if the GraphicsDevice supports translucency.
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		// If translucent windows aren't supported, exit.
		if (!gd.isWindowTranslucencySupported(TRANSLUCENT)) {
			JOptionPane.showMessageDialog(null,
					"Translucency is not supported", "Error",
					JOptionPane.ERROR_MESSAGE);
			TranslucencySupported = false;
		} else {
			// Translucency is Supported
			JFrame.setDefaultLookAndFeelDecorated(true);
			// Create the GUI on the event-dispatching thread
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					window.setOpacity(0.75f);
					window.setLocationRelativeTo(null);
					window.setVisible(true);
				}
			});
		}

	}

	public static void main(String[] args) {
		// Determine if the GraphicsDevice supports translucency.
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		// If translucent windows aren't supported, exit.
		if (!gd.isWindowTranslucencySupported(TRANSLUCENT)) {
			JOptionPane.showMessageDialog(null,
					"Translucency is not supported", "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
		// Create the GUI on the event-dispatching thread
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Help window = new Help("Ayuda");
				window.setOpacity(0.75f);
				window.setVisible(true);
			}
		});
	}
}
