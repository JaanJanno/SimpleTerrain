package ee.ut.jjanno.terrain.generator;

import ee.ut.jjanno.noise.Noise;
import ee.ut.jjanno.terrain.window.TerrainType;

public class Generator {
	
	public static int mapWidth = 64;
	public static int mapHeight = 64;
	
	Noise noise = new Noise();
	
	public TerrainType generate(int x, int y) {
		
	
		if(x < 22) {
			return TerrainType.TREE;
		}
		
		if(x < 26) {
			return TerrainType.GRASS;
		}
		
		if(x < 32) {
			return TerrainType.SAND;
		}
		
		return TerrainType.WATER;
		
	}

}
