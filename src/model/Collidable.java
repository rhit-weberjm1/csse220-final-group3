package model;

import java.awt.Rectangle;

public interface Collidable {
	
	/* sprites extend this class so that they don't phase through walls
	 * or themselves. 
	 * 
	 * Player and zombies share these.
	 */
	Rectangle getBounds();
	void onCollisionWithWall(Maze wall);

}
