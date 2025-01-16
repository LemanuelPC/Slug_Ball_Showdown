package io.codeforall.javatars_filhosdamain;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Player2 {
    //Rectangle rectangle;
    Picture rectangle;
    Position logicalPosition;
    Position graphicalPosition;
    Movement movement;
    double width;
    double height;

    public Player2(double x, double y, String path) {
        //this.rectangle = new Rectangle(x, y, width, height);
        this.rectangle = new Picture(x, y, path);
        this.width = rectangle.getWidth();
        this.height = rectangle.getHeight();
        this.logicalPosition = new Position(x + height, y+height); // Assuming center bottom position
        this.graphicalPosition = new Position(x + height, y+height);
        this.movement = new Movement(); // Start with no initial movement
    }

    public boolean isCollidingWithFloor(Field field) {
        return logicalPosition.y >= field.field.getHeight()+10;
    }

    public boolean isCollidingWithLeftWall(Field field) {
        return logicalPosition.x - height <= field.field.getX();
    }

    public boolean isCollidingWithRightWall(Field field) {
        return logicalPosition.x + height >= field.field.getWidth()+10;
    }


    public void updateLogicalPosition(Field field) {

        this.logicalPosition.x = this.logicalPosition.x + Math.cos(this.movement.direction) * this.movement.velocity.magnitude;
        this.logicalPosition.y = this.logicalPosition.y + Math.sin(this.movement.direction) * this.movement.velocity.magnitude;

    }

    public void updateGraphicalPosition() {
        //System.out.println("Player Logic Position: " + this.logicalPosition);
        //System.out.println("Player Graphical Position before update: " + this.graphicalPosition);
        rectangle.translate(this.logicalPosition.x - this.graphicalPosition.x, this.logicalPosition.y - this.graphicalPosition.y);
        this.graphicalPosition.x = this.logicalPosition.x;
        this.graphicalPosition.y = this.logicalPosition.y;
        //System.out.println("Player Graphical Position after update: " + this.graphicalPosition);
        //System.out.println("******");
    }

    public boolean isMoving() {
        return this.movement.velocity.x != 0;
    }

    public void jump(Field field) {
        // Check if the player is on the ground before allowing a jump
        if (isCollidingWithFloor(field)) {
            movement.velocity.y = -5; // Adjust this value to control the jump strength
            movement.velocity.updateMagnitude();
            movement.direction = Math.atan2(movement.velocity.y, movement.velocity.x);
        }
    }

    void checkCollisions(Field field) {
        if(isCollidingWithFloor(field)){
            if (logicalPosition.y > field.field.getHeight()+10){
                this.logicalPosition.y = field.field.getHeight()+10;
            }

            movement.velocity.y = 0;
            movement.velocity.updateMagnitude();
            movement.direction = Math.atan2(movement.velocity.y, movement.velocity.x);

        }

        if(isCollidingWithLeftWall(field)){
            if (logicalPosition.x - height < field.field.getX()){
                this.logicalPosition.x += field.field.getX() - (logicalPosition.x - height);
            }

            movement.velocity.x = 0;
            movement.velocity.updateMagnitude();
            movement.direction = Math.atan2(movement.velocity.y, movement.velocity.x);
        }

        if(isCollidingWithRightWall(field)){
            if (logicalPosition.x > field.field.getHeight()+10 - height) {
                this.logicalPosition.x -= (logicalPosition.x + height) - field.field.getWidth()-10;
            }

            movement.velocity.x = 0;
            movement.velocity.updateMagnitude();
            movement.direction = Math.atan2(movement.velocity.y, movement.velocity.x);
        }
    }

    public void move(int direction) {
        movement.velocity.x = 3 * direction; // Multiply by the direction (-1 for left, 1 for right)
        movement.velocity.updateMagnitude();
        movement.direction = Math.atan2(movement.velocity.y, movement.velocity.x);

        if(rectangle.getWidth() > 0 && direction == -1){
            rectangle.grow(-width, 0);
        }
        if(rectangle.getWidth() < 0 && direction == 1){
            rectangle.grow(width, 0);
        }

    }

    public Position getCenter() {
        return logicalPosition;
    }

}
