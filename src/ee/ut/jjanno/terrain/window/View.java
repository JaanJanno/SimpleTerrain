package ee.ut.jjanno.terrain.window;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JPanel;

import ee.ut.jjanno.terrain.generator.Generator;

@SuppressWarnings("serial")
public class View extends JPanel implements KeyListener {

	boolean runs = true;
	
	int mapWidth = Generator.mapWidth;
	int mapHeight = Generator.mapHeight;
	
	boolean pan_l = false;
	boolean pan_r = false;
	boolean pan_u = false;
	boolean pan_d = false;
	
	Tile[][] tiles = new Tile[mapWidth][mapHeight];
	
	Camera camera = new Camera(0, 0, getWidth(), getHeight());

	public View() {
		super();
		
		Generator g = new Generator();
		for(int x = 0; x < mapWidth; x++) {
			for(int y = 0; y < mapHeight; y++) {
				tiles[x][y] = new Tile(x, y, g.generate(x, y));
				
			}
		}


		MouseListener l = new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 2) {

				}
				if (e.getButton() == 1) {
	
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseClicked(MouseEvent e) {

			}
		};
		addMouseListener(l);

		addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {

			}
		});

		addKeyListener(this);
		setFocusable(true);
	}


	

	public void mainLoop() {
		
		

		while (true) {

			long start = System.currentTimeMillis();

			repaint();
			try {
				Thread.sleep(Math.max(0, 16 - (System.currentTimeMillis() - start)));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
	

	private void moveCamera(double d) {
		if(pan_u) {
			camera.y += 5000 * d;
		}
		if(pan_d) {
			camera.y -= 5000 * d;
		}
		if(pan_l) {
			camera.x += 5000 * d;
		}
		if(pan_r) {
			camera.x -= 5000 * d;
		}
		camera.width = getWidth();
		camera.height = getHeight();
	}




	@Override
	protected void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D)gg;
		
		
		
		double time = (double)System.nanoTime()/5000000000.0;
		for(int x = 0; x < mapWidth; x++) {
			for(int y = 0; y < mapHeight; y++) {
				tiles[x][y].draw(g, camera, time);
			}
		}
		moveCamera((double)System.nanoTime()/5000000000.0 - time);

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {	
			pan_u = true;		
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			pan_d = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			pan_l = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pan_r = true;
		}
		
		
		if (e.getKeyChar() == 'p') {
		}

		if (e.getKeyChar() == 'k') {
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {	
			pan_u = false;		
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			pan_d = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			pan_l = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			pan_r = false;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
