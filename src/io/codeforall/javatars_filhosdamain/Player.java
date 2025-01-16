package io.codeforall.javatars_filhosdamain;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Player {
    Rectangle rectangle;
    Vector position;
    Vector velocity;

    public Player(double x, double y, double width, double height) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.position = new Vector(x + width / 2, y + height / 2); // Assuming center position
        this.velocity = new Vector(0, 0); // Start with no initial velocity
    }

    // Method to update the player's position based on its velocity
    public void update(int CANVAS_WIDTH, int CANVAS_HEIGHT) {
        // Apply velocity to position
        double nextX = position.x + velocity.x;
        double nextY = position.y + velocity.y;

        // Check for left boundary
        if (nextX < 10 + (double) rectangle.getWidth() / 2) {
            velocity.x = 0; // Stop horizontal movement
            nextX = 10 + (double) rectangle.getWidth() / 2; // Adjust position to boundary
        }
        // Check for right boundary
        if (nextX > CANVAS_WIDTH - (double) rectangle.getWidth() / 2) {
            velocity.x = 0; // Stop horizontal movement
            nextX = CANVAS_WIDTH - (double) rectangle.getWidth() / 2; // Adjust position
        }
        // Check for bottom boundary
        if (nextY > CANVAS_HEIGHT - (double) rectangle.getHeight() / 2) { // Consider padding
            velocity.y = 0; // Stop vertical movement
            nextY = CANVAS_HEIGHT - (double) rectangle.getHeight() / 2; // Adjust position
        }

        // Calculate the translation needed
        double translateX = nextX - position.x;
        double translateY = nextY - position.y;

        // Translate the rectangle
        rectangle.translate(translateX, translateY);

        // Update the position
        position.x = nextX;
        position.y = nextY;
    }

    public boolean isOnGround(int CANVAS_HEIGHT) {
        return this.position.y + (double) this.rectangle.getHeight() / 2 >= CANVAS_HEIGHT;
    }

    public boolean isMoving() {
        return this.velocity.x != 0;
    }

    public void jump(int CANVAS_HEIGHT) {
        // Check if the player is on the ground before allowing a jump
        if (isOnGround(CANVAS_HEIGHT)) {
            velocity.y = -10; // Adjust this value to control the jump strength
        }
    }

    public void move(int direction) {
        velocity.x = 5 * direction; // Multiply by the direction (-1 for left, 1 for right)
    }

    public Vector getCenter() {
        return position;
    }
}
