package io.codeforall.javatars_filhosdamain;

public class Movement {

    double direction;
    Vector velocity;

    public Movement(){
        direction = 0.0;
        velocity = new Vector(0, 0);
    }

    @Override
    public String toString() {
        return "Movement{" +
                "direction=" + direction +
                ", velocity=" + velocity +
                '}';
    }
}
