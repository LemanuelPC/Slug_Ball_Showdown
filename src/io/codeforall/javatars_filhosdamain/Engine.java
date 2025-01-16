package io.codeforall.javatars_filhosdamain;

import org.academiadecodigo.simplegraphics.graphics.Canvas;

public class Engine {

    public static void main(String[] args) {

        Canvas.limitCanvasHeight(410);
        Canvas.limitCanvasWidth(570);

        Game game = new Game();

        game.init();

    }
}
