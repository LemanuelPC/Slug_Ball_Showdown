package io.codeforall.javatars_filhosdamain;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Field {
    Rectangle field;
    Vector position;
    double width;
    double height;
    Picture picture;

    public Field(int padding, int width, int height, String path){
        this.field = new Rectangle(padding, padding, width, height);
        this.position = new Vector((double) width / 2, (double) height / 2);
        this.width = width;
        this.height = height;
        this.picture = new Picture(padding, padding, path);
        picture.grow(10, 10);
    }

    public Vector getObjectPosition(Position absolutePosition) {
        // Subtract the padding to translate the absolute position into field-relative position
        double relativeX = absolutePosition.x - field.getX();
        double relativeY = absolutePosition.y - field.getY();
        return new Vector(relativeX, relativeY);
    }

    @Override
    public String toString() {
        return "Field{" +
                "field=" + field +
                ", position=" + position +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
