
public class Enviroment {
private final Position topLeft;
private final Position bottomRight;

public Enviroment (Position topLeft, Position bottomRight) {
	this.topLeft = topLeft;
	this.bottomRight = bottomRight;
}
public boolean isOutOfBounds (Position head) {
	return head.getX() < topLeft.getX() || head.getX() > bottomRight.getX() || 
			head.getY()< topLeft.getY() || head.getY() > bottomRight.getY();
	}
}
