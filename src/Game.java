/*
 * Spiderman VS Green Goblin es un sencillo juego en 2D al estilo bit map, 
 * en el cual se enfrentan Spiderman y el Duende Verde.
 * Game.java it´s the main class that runs the program
 * Author: Jassael Ruiz
 * Version: 1.0
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	Thread anima;
	JFrame mainWindow;
	JPanel cont;
	Dimension WINDOWS_SIZE;
	SpiderMan spider;
	GreenGoblin globin;
	BufferStrategy strategy;
	CopyOnWriteArrayList<Pumpkin> explosion;
	CopyOnWriteArrayList<Bomb> bombs;
	CopyOnWriteArrayList<Pumpkin> pks;
	CopyOnWriteArrayList<Fire> bullets;
	CopyOnWriteArrayList<Power> powers;
	CopyOnWriteArrayList<Lifes> lifes;
	CopyOnWriteArrayList<LifesGG> lifesGG;
	Timer timer;
	int delay = 2000, delay2 = 5000, cont_delay2 = 0;
	private int t = 0;
	int num_lifes = 5, max_lifesGG = 20, kill_pk = 50, score = 0,
			hit_globin = 500, kill_bomb = 25, num_kills = 0, num_hits = 0,
			max_pks = 1, maxBullets = 3;
	boolean pause = true, dead = false, restarting = false, win = false;
	boolean run_angry = false, run = false, directions = true, playRisa = true,
			playWin = true;
	private boolean left, right;
	Rectangle2D limit, limit_sup;
	Random rand;
	TimerTask timerTask_angry, timerTask;
	Timer angry;
	Fire fire;
	String level = "EASY";
	private BufferedImage background;
	final Help ayuda;
	Sounds sound;

	private void splash_delay() {
		// delays the apparition of the components
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public Game(String window_title) {
		// class constructor, perform some initialization
		splash_delay();
		sound = new Sounds();
		ayuda = new Help("Ayuda", this);
		mainWindow = new JFrame(window_title);
		mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		// setBackground(new Color(209, 227, 236));
		setFocusable(true);
		addKeyListener(this);
		cont = (JPanel) mainWindow.getContentPane();
		cont.setLayout(null);
		Toolkit tk = Toolkit.getDefaultToolkit();
		WINDOWS_SIZE = tk.getScreenSize();
		cont.setSize(WINDOWS_SIZE);
		setBounds(0, 0, WINDOWS_SIZE.width, WINDOWS_SIZE.width);
		cont.add(this);
		spider = new SpiderMan(WINDOWS_SIZE);
		globin = new GreenGoblin(WINDOWS_SIZE);
		explosion = new CopyOnWriteArrayList<Pumpkin>();
		lifes = new CopyOnWriteArrayList<Lifes>();
		lifesGG = new CopyOnWriteArrayList<LifesGG>();
		pks = new CopyOnWriteArrayList<Pumpkin>();
		loadSpidermanLifes();
		loadLifesGG();
		bombs = new CopyOnWriteArrayList<Bomb>();
		bullets = new CopyOnWriteArrayList<Fire>();
		powers = new CopyOnWriteArrayList<Power>();
		mainWindow.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				timer.cancel();
				angry.cancel();
				stop();
				stop1();
				stop2();
				System.exit(0);
			}
		});
		mainWindow.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				WINDOWS_SIZE = mainWindow.getSize();
				globin.WIDTH = WINDOWS_SIZE.getWidth();
				globin.position[0] = WINDOWS_SIZE.getWidth() / 2;
				spider.position[1] = WINDOWS_SIZE.getHeight() - 140;
				spider.position[0] = WINDOWS_SIZE.getHeight() / 2;
				for (Lifes life : lifes)
					life.position[1] = WINDOWS_SIZE.getHeight() - 57;
				for (LifesGG life : lifesGG)
					life.position[1] = WINDOWS_SIZE.getHeight() - 57;
			}
		});
		mainWindow.setMinimumSize(new Dimension(500, 500));
		setAngry();
		setTimer();
		rand = new Random();
		background = getImage("background.gif");
		mainWindow.setVisible(true);
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		if (ayuda.TranslucencySupported) {
			ayuda.showHelp(ayuda);
			sound.loop("intro");
			sound.play("intro");
		}
		start();
	}

	private void setTimer() {
		// add new bomb to the array of bombs depends of the delay variable
		timer = new Timer();
		timerTask = new TimerTask() {
			public void run() {
				if (run) {
					addBomb();
				}
			}
		};
		timer.scheduleAtFixedRate(timerTask, 0, delay);
	}

	private void setAngry() {
		// add new group of pumpkins to the array of explosion depends of the
		// delay variable
		angry = new Timer();
		timerTask_angry = new TimerTask() {
			public void run() {
				if (run_angry) {
					for (int i = 0; i < max_pks; i++)
						explosion.add(new Pumpkin(WINDOWS_SIZE, 200 + rand
								.nextInt(WINDOWS_SIZE.width) - 200));
					cont_delay2++;
				}
			}
		};
		angry.scheduleAtFixedRate(timerTask_angry, 0, delay2);
	}

	private void addBullet() {
		// add new bullet to the array of bullets
		if (bullets.size() < maxBullets) {
			bullets.add(new Fire(WINDOWS_SIZE, spider.r.getX(),
					spider.r.getY() - 25));
			sound.stop("bullet");
			sound.play("bullet");
		}
	}

	private void firePower() {
		// add new power to the array of powers
		if (powers.size() < 1) {
			powers.add(new Power(WINDOWS_SIZE, spider.r.getX(),
					spider.r.getY() - 55));
			sound.stop("power");
			sound.play("power");
		}
	}

	public void run1() {
		// start the timer that allows to add new bombs
		run = true;
	}

	private void stop1() {
		// stop the timer that allows to add new bombs
		run = false;
	}

	public void run2() {
		// start the timer that allows to add new pumpkins
		run_angry = true;
	}

	private void stop2() {
		// stop the timer that allows to add new pumpkins
		run_angry = false;
	}

	private void loadSpidermanLifes() {
		// load spiderman´s lifes
		if (!lifes.isEmpty())
			lifes.clear();
		for (int i = 0, x = 60; i < num_lifes; i++, x += 30)
			lifes.add(new Lifes(x, WINDOWS_SIZE.getHeight() - 56));
	}

	private void loadLifesGG() {
		// load green goblin´s lifes
		if (!lifesGG.isEmpty())
			lifesGG.clear();
		for (int i = 0, x = 280; i < max_lifesGG; i++, x += 30)
			lifesGG.add(new LifesGG(x, WINDOWS_SIZE.getHeight() - 56));
	}

	private void addExplosion() {
		// add the group of pumpkins stored in explosion to the array of pumpkin
		stop2();
		cont_delay2 = 0;
		for (Pumpkin pk : explosion)
			pks.add(pk);
		sound.stop("risa");
		sound.play("risa");
	}

	public void start() {
		// start the main thread that runs the program
		if (anima == null) {
			anima = new Thread(this);
			anima.start();
		}
	}

	@SuppressWarnings("deprecation")
	public void stop() {
		// stop the main thread of the application
		if (anima != null) {
			anima.stop();
			anima = null;
		}
	}

	private void checkCollisionBs() {
		// spider vs green goblin´s bombs
		Rectangle2D bomb_mecha, bomb_body, spider_head, spider_body;
		for (Bomb bomb : bombs) {
			bomb_mecha = bomb.r;
			bomb_body = bomb.r2;
			spider_head = spider.r;
			spider_body = spider.r2;
			boolean collision = detectCollision(bomb_mecha, bomb_body,
					spider_head, spider_body);
			if (collision) {
				bombs.remove(bomb);
				removeLife();
				sound.stop("bomb");
				sound.play("bomb");
			}
		}
	}

	private void checkCollisionSpb() {
		// spiderman´s power vs green goblin´s bombs
		Rectangle2D bomb_mecha, bomb_body, pow, pkg, glob_b, glob_h;
		for (Bomb bomb : bombs) {
			bomb_mecha = bomb.r;
			bomb_body = bomb.r2;
			for (Power p : powers) {
				pow = p.r;
				boolean collision_pb = detectCollisionPb(bomb_mecha, bomb_body,
						pow);
				if (collision_pb) {
					bombs.remove(bomb);
					num_kills++;
					if (num_kills == 3) {
						num_kills = 0;
						powers.remove(p);
					}
					score += kill_bomb;
					sound.stop("explosion");
					sound.play("explosion");
				}
				// spiderman´s power vs green goblin´s pumpkins
				for (Pumpkin pk : pks) {
					pkg = pk.r;
					boolean collision_ppk = detectCollisionSpPk(pkg, pow);
					if (collision_ppk) {
						pks.remove(pk);
						explosion.remove(pk);
						score += kill_bomb;
						num_kills++;
						if (num_kills == 3) {
							num_kills = 0;
							powers.remove(p);
						}
						sound.stop("explosion");
						sound.play("explosion");
					}
				}
				glob_b = globin.r2;
				glob_h = globin.r;
				// spiderman´s power vs green goblin
				boolean collision_pg = detectCollisionPg(glob_h, glob_b, pow);
				if (collision_pg) {
					score += hit_globin;
					powers.remove(p);
					removeLifeGG();
					sound.stop("grito");
					sound.play("grito");
				}
			}
		}
	}

	private boolean detectCollisionSpPk(Rectangle2D pkg, Rectangle2D pow) {
		// return true if spiderman´s power intersects a green goblin´s pumpkin
		if (pkg.intersects(pow))
			return true;
		return false;
	}

	private void checkCollision_PkSp() {
		// spider vs green goblin´s pumpkins
		Rectangle2D spider_head, spider_body, pk;
		for (Pumpkin p : pks) {
			spider_head = spider.r;
			spider_body = spider.r2;
			pk = p.r;
			boolean collision = detectCollisionPks(spider_head, spider_body, pk);
			if (collision) {
				pks.remove(p);
				explosion.remove(p);
				removeLife();
				removeLife();
				sound.stop("bomb");
				sound.play("bomb");
			}
		}
	}

	private void checkCollision_PkSb() {
		// goblin´s pumpkins vs spiderman´s bullet
		Rectangle2D bullet, pk_body;
		for (Pumpkin p : pks) {
			pk_body = p.r;
			for (Fire f : bullets) {
				bullet = f.r;
				boolean collision = detectCollisionBpk(pk_body, bullet);
				if (collision) {
					num_hits++;
					if (num_hits == 2) {
						num_hits = 0;
						pks.remove(p);
						explosion.remove(p);
						score += kill_pk;
					}
					bullets.remove(f);
					sound.stop("explosion");
					sound.play("explosion");
				}
			}
		}
	}

	private void checkCollision() {
		// spiderman´s bullet vs green goblin´s bomb
		Rectangle2D bomb_mecha, bomb_body, fire_body;
		Rectangle2D gg_head, gg_body;
		for (Bomb bomb : bombs) {
			bomb_mecha = bomb.r;
			bomb_body = bomb.r2;
			for (Fire f : bullets) {
				fire_body = f.r;
				boolean collision_bb = detectCollisionBb(bomb_mecha, bomb_body,
						fire_body);
				if (collision_bb) {
					bombs.remove(bomb);
					bullets.remove(f);
					score += kill_bomb;
					sound.stop("explosion");
					sound.play("explosion");
				}
				fire_body = f.r;
				gg_head = globin.r;
				gg_body = globin.r2;
				// spiderman´s bullet vs green goblin
				boolean collision_bg = detectCollisionBg(gg_head, gg_body,
						fire_body);
				if (collision_bg) {
					bullets.remove(f);
					score += hit_globin;
					removeLifeGG();
					sound.stop("grito");
					sound.play("grito");
				}
			}
		}
	}

	@Override
	public void run() {
		// main thread of the application
		while (mainWindow.isVisible()) {
			paintWorld();
			if (!pause && !dead && !win && !directions) {
				t++;
				if (cont_delay2 == 5)
					addExplosion();
				if (explosion.isEmpty())
					run2();
				checkCollisionBs();
				checkCollision();
				checkCollision_PkSp();
				checkCollision_PkSb();
				checkCollisionSpb();
				globin.updatePosition(WINDOWS_SIZE.getWidth());
				for (Bomb bomb : bombs) {
					checkBombBounds(bomb);
					bomb.updatePosition();
				}
				for (Fire f : bullets) {
					checkBulletBounds(f);
					f.updatePosition();
				}
				for (Pumpkin pk : pks) {
					checkPksBounds(pk);
					pk.updatePosition();
				}
				for (Power p : powers) {
					checkPowerBounds(p);
					p.updatePosition();
				}
				updateSpiderPos();
				isAlive();
				win();
			}
			delay();
		}
	}

	private void checkPowerBounds(Power p) {
		// remove the spiderman´s power when it´s out of frame´s bounds
		if (p.r.intersects(limit_sup)) {
			powers.remove(p);
		}
	}

	private void removeLife() {
		// remove one spiderman´s life
		if (!lifes.isEmpty()) {
			Lifes last = lifes.get(lifes.size() - 1);
			lifes.remove(last);
		}
	}

	private void removeLifeGG() {
		// remove one green goblin´s life
		if (!lifesGG.isEmpty()) {
			LifesGG last = lifesGG.get(lifesGG.size() - 1);
			lifesGG.remove(last);
		}
	}

	private void isAlive() {
		// check if spiderman has more enough lifes
		dead = lifes.isEmpty();
		if (dead) {
			restarting = false;
			stop1();
			stop2();
			if (playRisa) {
				for (String name : sound.songs)
					sound.sounds.get(name).stop();
				sound.loop("risa");
				sound.play("risa");
				playRisa = false;
			}
		} else
			run1();
	}

	private void win() {
		// check if green goblin has more enough lifes
		win = lifesGG.isEmpty();
		if (win) {
			restarting = false;
			stop1();
			stop2();
			if (playWin) {
				for (String name : sound.songs)
					sound.sounds.get(name).stop();
				sound.loop("winner");
				sound.play("winner");
				playWin = false;
			}
		} else
			run1();
	}

	private void checkBombBounds(Bomb b) {
		// remove the green goblins´s bombs when it´s out of frame´s bounds
		if (b.r2.intersects(limit)) {
			bombs.remove(b);
		}
	}

	private void checkPksBounds(Pumpkin pk) {
		// remove the green goblins´s power when it´s out of frame´s bounds
		if (pk.r.intersects(limit)) {
			pks.remove(pk);
			explosion.remove(pk);
		}
	}

	private void checkBulletBounds(Fire f) {
		// remove the spiderman´s bullet when it´s out of frame´s bounds
		if (f.r.intersects(limit_sup)) {
			bullets.remove(f);
		}
	}

	private boolean detectCollisionBb(Rectangle2D bomb_mecha,
			Rectangle2D bomb_body, Rectangle2D bullet) {
		// return true if spiderman´s bullet intersects a green goblin´s bomb
		if (bomb_mecha.intersects(bullet) || bomb_body.intersects(bullet)) {
			return true;
		}
		return false;
	}

	private boolean detectCollisionPg(Rectangle2D globin_head,
			Rectangle2D globin_body, Rectangle2D power) {
		// return true if spiderman´s power intersects green goblin
		if (globin_head.intersects(power) || globin_body.intersects(power)) {
			return true;
		}
		return false;
	}

	private boolean detectCollisionPb(Rectangle2D bomb_mecha,
			Rectangle2D bomb_body, Rectangle2D power) {
		// return true if spiderman´s power intersects a green goblin´s power
		if (bomb_mecha.intersects(power) || bomb_body.intersects(power)) {
			return true;
		}
		return false;
	}

	private boolean detectCollisionBpk(Rectangle2D pk, Rectangle2D bullet) {
		// return true if spiderman´s bullet intersects a green goblin´s power
		if (pk.intersects(bullet)) {
			return true;
		}
		return false;
	}

	private boolean detectCollisionBg(Rectangle2D gg_head, Rectangle2D gg_body,
			Rectangle2D bullet) {
		// return true if spiderman´s bullet intersects green goblin
		if (gg_head.intersects(bullet) || gg_body.intersects(bullet)) {
			return true;
		}
		return false;
	}

	private boolean detectCollisionPks(Rectangle2D sp_head,
			Rectangle2D sp_body, Rectangle2D pk) {
		// return true if spiderman intersects a green goblin´s power
		if (sp_head.intersects(pk) || sp_body.intersects(pk)) {
			return true;
		}
		return false;
	}

	private boolean detectCollision(Rectangle2D bomb_mecha,
			Rectangle2D bomb_body, Rectangle2D spider_head,
			Rectangle2D spider_body) {
		// return true if spiderman intersects a green goblin´s bomb
		if (bomb_mecha.intersects(spider_head)
				|| bomb_mecha.intersects(spider_body)) {
			return true;
		}
		if (bomb_body.intersects(spider_head)
				|| bomb_body.intersects(spider_body)) {
			return true;
		}
		return false;
	}

	private void delay() {
		// delay the main loop of the application
		int delay = 10;
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
		}
	}

	private void addBomb() {
		// adds new bomb to the array
		bombs.add(new Bomb(WINDOWS_SIZE, globin.r2.getX()));
	}

	private BufferedImage loadImage(String nombre) {
		URL url = null;
		try {
			url = getClass().getClassLoader().getResource(nombre);
			return ImageIO.read(url);
		} catch (Exception e) {
			JOptionPane
					.showMessageDialog(null, "No se pudo cargar la imagen "
							+ nombre + " de " + url, "Error",
							JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, "El error fue : "
					+ e.getClass().getName() + " " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
			return null;
		}
	}

	public BufferedImage getImage(String nombre) {
		BufferedImage img = null;
		img = loadImage("images/" + nombre);
		return img;
	}

	public void paintWorld() {
		// paint all the elements that conforms the game
		Graphics2D g2 = (Graphics2D) strategy.getDrawGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(new TexturePaint(background, new Rectangle(t, t, background
				.getWidth(), background.getHeight())));
		g2.fillRect(0, 0, getWidth(), getHeight());
		if (!dead && !directions)
			spider.draw(g2);
		if (!win && !directions)
			globin.draw(g2);
		if (!dead && !win && !directions)
			for (Bomb bomb : bombs)
				bomb.draw(g2);
		if (!dead && !win && !directions)
			for (Pumpkin pk : pks)
				pk.draw(g2);
		if (!dead && !win && !directions)
			for (Fire fire : bullets)
				fire.draw(g2);
		if (!dead && !win && !directions)
			for (Power p : powers)
				p.draw(g2);
		g2.setColor(Color.lightGray);
		limit = new Rectangle2D.Double(-50, WINDOWS_SIZE.getHeight() - 58,
				WINDOWS_SIZE.getWidth() + 50, 200);
		g2.fill(limit);
		limit_sup = new Rectangle2D.Double(-50, -10,
				WINDOWS_SIZE.getWidth() + 50, 5);
		g2.fill(limit_sup);
		g2.setFont(new Font("Arial", Font.BOLD, 15));
		g2.setColor(Color.red);
		// spiderman´s lifes
		g2.drawString("LIFES: ", 5, (int) WINDOWS_SIZE.getHeight() - 40);
		g2.setColor(new Color(129, 8, 114));
		// green goblin´s lifes
		g2.drawString("LIFES: ", 220, (int) WINDOWS_SIZE.getHeight() - 40);
		g2.setColor(new Color(97, 101, 197));
		g2.drawString("SCORE: " + score, 600,
				(int) WINDOWS_SIZE.getHeight() - 40);
		for (Lifes life : lifes)
			life.draw(g2);
		for (int i = 0; i < 10; i++)
			if (i < lifesGG.size())
				lifesGG.get(i).draw(g2);
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial", Font.BOLD, 25));
		if (pause && !dead && !win && !directions)
			g2.drawString(">>> GAME PAUSED <<<",
					(int) WINDOWS_SIZE.getWidth() / 2 - 150,
					(int) WINDOWS_SIZE.getHeight() / 2);
		drawLevel(g2);
		isDead(g2);
		winner(g2);
		printDirections(g2);
		strategy.show();
	}

	private void drawLevel(Graphics2D g2) {
		// draw the games´s level on the screen
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial", Font.BOLD, 13));
		g2.drawString("MODE: " + level, 770,
				(int) WINDOWS_SIZE.getHeight() - 40);
	}

	private void printDirections(Graphics2D g2) {
		// show the necessary directions to play the game
		if (!ayuda.TranslucencySupported && directions) {
			String msj = "<<< Instrucciones del juego >>>\n\n 1. Usa las teclas de direccion del teclado\n"
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
			int option = JOptionPane
					.showConfirmDialog(null, msj, "Ayuda",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.OK_OPTION) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				pausar();
			}
		}
	}

	private void isDead(Graphics2D g2) {
		// show the respective message that the player is dead
		if (dead) {
			g2.setColor(Color.white);
			g2.setFont(new Font("Arial", Font.BOLD, 17));
			g2.drawString(">>> YOU ARE DEAD :( PRESS (R) TO RESTART",
					(int) WINDOWS_SIZE.getWidth() / 2 - 150,
					(int) WINDOWS_SIZE.getHeight() / 2);
			g2.drawString(" THE GAME OR (E) TO EXIT THE GAME <<<",
					(int) WINDOWS_SIZE.getWidth() / 2 - 150,
					(int) WINDOWS_SIZE.getHeight() / 2 + 20);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
			}
			if (!restarting) {
				globin.position[0] = globin.r.getWidth()
						+ rand.nextInt((int) WINDOWS_SIZE.getWidth() - 200);
				globin.position[1] = rand.nextInt((int) WINDOWS_SIZE
						.getHeight() - 200);
				int side = 1 + rand.nextInt(2);
				if (side == 1)
					globin.loadRightGreengoblin();
				else
					globin.loadLeftGreengoblin();
			}
		}
	}

	private void winner(Graphics2D g2) {
		// show the respective message that the player win the game
		if (win) {
			g2.setColor(Color.white);
			g2.setFont(new Font("Arial", Font.BOLD, 14));
			g2.drawString(
					">>> YOU BEAT THE GREEN GOBLIN, CONGRATULATIONS !! <<<",
					(int) WINDOWS_SIZE.getWidth() / 2 - 220,
					(int) WINDOWS_SIZE.getHeight() / 2);
			g2.drawString("PRESS (R) TO PLAY AGAIN OR (E) TO EXIT THE GAME",
					(int) WINDOWS_SIZE.getWidth() / 2 - 150,
					(int) WINDOWS_SIZE.getHeight() / 2 + 20);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
			}
			if (!restarting) {
				spider.position[0] = globin.r.getWidth()
						+ rand.nextInt((int) WINDOWS_SIZE.getWidth() - 200);
				spider.position[1] = rand.nextInt((int) WINDOWS_SIZE
						.getHeight() - 200);
				int side = 1 + rand.nextInt(2);
				if (side == 1)
					spider.loadRightSpiderman();
				else
					spider.loadLeftSpiderman();
			}
		}
	}

	private void restart() {
		// restart the game to the initials values
		loadSpidermanLifes();
		loadLifesGG();
		bombs.clear();
		pks.clear();
		win = false;
		dead = false;
		pause = false;
		globin.restart();
		restartSpiderPos();
		restarting = true;
		explosion.clear();
		cont_delay2 = 0;
		score = 0;
		bullets.clear();
		powers.clear();
		playRisa = true;
		playWin = true;
		for (String name : sound.songs) {
			sound.sounds.get(name).stop();
		}
		sound.loop("game");
		sound.play("game");
		run1();
		run2();
	}

	public void restartSpiderPos() {
		// restart spiderman´s position to the initials values
		spider.position[0] = WINDOWS_SIZE.getHeight() / 2;
		spider.position[1] = WINDOWS_SIZE.getHeight() - 140;
	}

	public static void main(String[] args) {
		// main method that runs the program

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame.setDefaultLookAndFeelDecorated(true);
				new Game("Spiderman vs Green Goblin");
			}
		});
	}

	private void updateSpiderPos() {
		// update the spiderman´s position
		spider.velocity[0] = 7;
		if (left) {
			spider.updatePosition(2, WINDOWS_SIZE);
			spider.loadLeftSpiderman();
		}
		if (right) {
			spider.updatePosition(1, WINDOWS_SIZE);
			spider.loadRightSpiderman();
		}
	}

	public void pausar() {
		pause = !pause;
		sound.stop("game");
		if (pause) {
			stop1();
			stop2();
		} else {
			play();
		}
	}

	public void play() {
		pause = false;
		directions = false;
		run1();
		run2();
		sound.loop("game");
		sound.play("game");
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// key listener
		if (!pause && !dead) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				left = true;
				break;
			case KeyEvent.VK_RIGHT:
				right = true;
				break;
			case KeyEvent.VK_Z:
				addBullet();
				break;
			case KeyEvent.VK_SPACE:
				firePower();
				break;
			}
		}
		if (KeyEvent.VK_ENTER == e.getKeyCode()) {
			if (!dead) {
				if (!ayuda.isVisible())
					pausar();
			}
		}
		switch (e.getKeyCode()) {
		case KeyEvent.VK_R:
			if (dead || win)
				restart();
			break;
		case KeyEvent.VK_E:
			if (win || dead)
				System.exit(0);
			break;
		case KeyEvent.VK_F1:
			if (!pause)
				pausar();
			sound.loop("intro");
			sound.play("intro");
			ayuda.showHelp(ayuda);
			break;
		case KeyEvent.VK_F2:
			// easy
			if (!pause) {
				num_lifes = 5;
				level = "EASY";
				delay = 2000;
				delay2 = 1500;
				max_lifesGG = 20;
				max_pks = 1;
				angry.cancel();
				angry.purge();
				setAngry();
				timer.cancel();
				timer.purge();
				setTimer();
				restart();
			}
			break;
		case KeyEvent.VK_F3:
			// medium
			if (!pause) {
				num_lifes = 5;
				level = "MEDIUM";
				delay = 1000;
				delay2 = 1000;
				max_lifesGG = 20;
				max_pks = 2;
				angry.cancel();
				angry.purge();
				setAngry();
				timer.cancel();
				timer.purge();
				setTimer();
				restart();
			}
			break;
		case KeyEvent.VK_F4:
			// hard
			if (!pause) {
				num_lifes = 5;
				level = "HARD";
				delay = 400;
				delay2 = 700;
				max_lifesGG = 30;
				max_pks = 3;
				angry.cancel();
				angry.purge();
				setAngry();
				timer.cancel();
				timer.purge();
				setTimer();
				restart();
			}
			break;
		case KeyEvent.VK_F5:
			// very hard
			if (!pause) {
				num_lifes = 5;
				level = "VERY HARD";
				delay = 200;
				delay2 = 500;
				max_lifesGG = 40;
				max_pks = 4;
				angry.cancel();
				angry.purge();
				setAngry();
				timer.cancel();
				timer.purge();
				setTimer();
				restart();
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// key listener
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
