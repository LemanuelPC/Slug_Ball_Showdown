package io.codeforall.javatars_filhosdamain;

import org.academiadecodigo.simplegraphics.graphics.Ellipse;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

public class Ball{
    Ellipse ellipse;
    Vector position;
    Vector velocity;

    public Ball(double xTopLeftCorner, double yTopLeftCorner, double diameter) {
        this.ellipse = new Ellipse(xTopLeftCorner, yTopLeftCorner, diameter, diameter);
        this.position = new Vector(xTopLeftCorner + diameter/2, yTopLeftCorner);
        this.velocity = new Vector(0, 0); // Start with no initial velocity
    }

    // Method to update the ball's position based on its velocity
    public void update() {
        // Predict the next position
        double nextX = position.x + velocity.x;
        double nextY = position.y + velocity.y;

        // Correct the position if it's going to be out of bounds
        if (nextX < 10 || nextX > Match.FIELD_WIDTH) { // Assuming the ball diameter is 20
            velocity.x *= -0.9; // Reverse and reduce speed slightly for bounce effect
            nextX = position.x; // Revert the horizontal movement
        }
        if (nextY < 10 || nextY > Match.FIELD_HEIGHT) {
            velocity.y *= -0.4; // Reverse and reduce speed slightly for bounce effect
            nextY = position.y; // Revert the vertical movement
        }

        // Update the position with corrected values
        position.x = nextX;
        position.y = nextY;

        // Apply the movement
        ellipse.translate(velocity.x, velocity.y);
    }

    public boolean intersects(Rectangle rect) {
        double ellipseCenterX = position.x;
        double ellipseCenterY = position.y;
        double ellipseRadius = (double) ellipse.getWidth() / 2;

        // Find the closest point on the Rectangle to the center of the Ellipse
        double closestX = clamp(ellipseCenterX, rect.getX(), rect.getX() + rect.getWidth());
        double closestY = clamp(ellipseCenterY, rect.getY(), rect.getY() + rect.getHeight());

        // Calculate the distance between the Ellipse's center and the closest point
        double distanceX = ellipseCenterX - closestX;
        double distanceY = ellipseCenterY - closestY;

        // If the distance is less than the radius, they intersect
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY) <= ellipseRadius;
    }

    // Helper method to clamp values
    public double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public boolean isOnGround(int CANVAS_HEIGHT) {
        return this.position.y + (double) this.ellipse.getHeight() / 2 >= CANVAS_HEIGHT+10;
    }

    public boolean isMoving() {
        return this.velocity.x != 0;
    }

}


