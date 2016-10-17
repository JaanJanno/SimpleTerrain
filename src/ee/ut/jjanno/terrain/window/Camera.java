package ee.ut.jjanno.terrain.window;

public class Camera {

	float x;
	float y;
	float width;
	float height;
	
	public Camera(float x, float y, float width, float height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public int getX() {
		return (int)x;
	}

	public int getY() {
		return (int)y;
	}
	
	
	
}
