package ee.ut.jjanno.terrain.window;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ee.ut.jjanno.noise.Noise;

public class Tile {
	
	private static float waterLevels = 255;
	private static float sandLevels = 55;
	private static float grassLevels = 55;
	private static float treeLevels = 15;

	private int x;
	private int y;
	private TerrainType type;

	private static BufferedImage waters[] = new BufferedImage[(int)waterLevels];
	private static BufferedImage sands[] = new BufferedImage[(int)sandLevels];
	private static BufferedImage grasses[] = new BufferedImage[(int)grassLevels];
	private static BufferedImage trees[] = new BufferedImage[(int)treeLevels];

	private Noise n = new Noise();
	
	private double mySandVal;
	private double myGrassVal;
	private double myTreeVal;
	private double myTreeDispl;

	public Tile(int x, int y, TerrainType type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
		
		mySandVal = n.compositeNoise((float)x / 10, (float)y / 10, 0, 5, 0.5);
		myGrassVal = n.compositeNoise((float)x / 10, (float)y / 10, 0, 5, 0.5);
		myTreeVal = n.compositeNoise((float)x / 10, (float)y / 10, 0, 5, 0.5);
		myTreeDispl = (int)(8.0*n.noise(x, y));

	}
	
	

	static {
		
		for (int i = 0; i < waterLevels; i++) {
			BufferedImage water = new BufferedImage(32, 12, BufferedImage.TYPE_INT_ARGB);
			Color c = new Color(0, 0, (int) (255f * (i / waterLevels)), 255);
			Graphics2D g = (Graphics2D) water.getGraphics();
			g.setColor(c);
			g.fillRect(0, 4, 32, 4);
			g.fillRect(8, 0, 16, 12);
			waters[i] = water;
		}
		
		for (int i = 0; i < sandLevels; i++) {
			BufferedImage water = new BufferedImage(32, 12, BufferedImage.TYPE_INT_ARGB);
			Color c = new Color((int) (180f + 55f * (i / sandLevels)), (int) (180f + 55f * (i / sandLevels)), 0, 255);
			Graphics2D g = (Graphics2D) water.getGraphics();
			g.setColor(c);
			g.fillRect(0, 4, 32, 4);
			g.fillRect(8, 0, 16, 12);
			sands[i] = water;
		}
		
		for (int i = 0; i < sandLevels; i++) {
			BufferedImage water = new BufferedImage(32, 12, BufferedImage.TYPE_INT_ARGB);
			Color c = new Color((int) (180f + 55f * (i / sandLevels)), (int) (180f + 55f * (i / sandLevels)), 0, 255);
			Graphics2D g = (Graphics2D) water.getGraphics();
			g.setColor(c);
			g.fillRect(0, 4, 32, 4);
			g.fillRect(8, 0, 16, 12);
			sands[i] = water;
		}
		
		for (int i = 0; i < grassLevels; i++) {
			BufferedImage water = new BufferedImage(32, 12, BufferedImage.TYPE_INT_ARGB);
			Color c = new Color((int) (50f + 55f * (i / grassLevels)), (int) (100f + 55f * (i / grassLevels)), 0, 255);
			Graphics2D g = (Graphics2D) water.getGraphics();
			g.setColor(c);
			g.fillRect(0, 4, 32, 4);
			g.fillRect(8, 0, 16, 12);
			grasses[i] = water;
		}
		
		for (int i = 0; i < treeLevels; i++) {
			BufferedImage water = new BufferedImage(32, 36, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) water.getGraphics();
			Color c = new Color(0, 0, 0, 255);
			g.setColor(c);
			g.fillRect(14, 0, 4, 36);
			c = new Color(25, (int) (85f * (i / treeLevels)), 0, 255);
			g.setColor(c);
			g.fillRect(9, 0, 13, 24);
			trees[i] = water;
		}
		
	}
	
	public void drawTree(Graphics2D g, Camera c, double time) {

		int sx = -x * 16 + y * 16 + (int)myTreeDispl;
		int sy = x * 8 + y * 8 - 24 - 4;

		if(sx + c.getX() < -32) {
			return;
		}
		if(sx + c.getX() > c.width) {
			return;
		}
		if(sy + c.getY() < -12) {
			return;
		}
		if(sy + c.getY() > c.height) {
			return;
		}

		g.drawImage(trees[(int)(0.5*treeLevels*(1.0+myTreeVal))], null, sx + c.getX(), sy + c.getY());
	}

	public void draw(Graphics2D g, Camera c, double time) {

		int sx = -x * 16 + y * 16;
		int sy = x * 8 + y * 8;

		if(sx + c.getX() < -32) {
			return;
		}
		if(sx + c.getX() > c.width) {
			return;
		}
		if(sy + c.getY() < -12) {
			return;
		}
		if(sy + c.getY() > c.height) {
			return;
		}

		switch (type) {
		case WATER:
			g.drawImage(waters[(int)(0.5*waterLevels*(1.0+n.compositeNoise((float)x / 10, (float)y / 10, time, 2, 0.7)))], null, sx + c.getX(), sy + c.getY());
			break;
		case SAND:
			g.drawImage(sands[(int)(0.5*sandLevels*(1.0+mySandVal))], null, sx + c.getX(), sy + c.getY());
			break;
		case TREE:
			g.drawImage(grasses[(int)(0.5*grassLevels*(1.0+myGrassVal))], null, sx + c.getX(), sy + c.getY());	
			drawTree(g, c, time);
			break;
		case GRASS:
			g.drawImage(grasses[(int)(0.5*grassLevels*(1.0+myGrassVal))], null, sx + c.getX(), sy + c.getY());			
			break;

		}
	}

}
