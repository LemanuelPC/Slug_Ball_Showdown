package io.codeforall.javatars_filhosdamain;

import io.codeforall.javatars_filhosdamain.players.Slime;
import org.academiadecodigo.simplegraphics.graphics.Canvas;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Menu implements KeyboardHandler {

    private Canvas canvas;
    Slime slime2;
    private Game game;
    private boolean escPressed = false;
    private boolean pPressed = false;
    public Menu(Canvas canvas){
        this.canvas = canvas;
        registerKeyboardEvents();
    }
    private int currentOption = 0;
    private final String[] menuOptions = {"Start Game", "Config", "Exit Game", "Start New Game 2"};
    private final Text[] menuTexts = new Text[menuOptions.length];

    public void initMenu() {
        for (int i = 0; i < menuOptions.length; i++) {
            menuTexts[i] = new Text(350, 250 + (i * 50), menuOptions[i]);
            canvas.show(menuTexts[i]);
        }
        updateMenuDisplay();
    }

    public void hideMenu(){
        for (int i = 0; i < menuOptions.length; i++) {
            canvas.hide(menuTexts[i]);
        }
        System.out.println("Hiding menu");
    }

    public void updateMenuDisplay() {
        for (int i = 0; i < menuTexts.length; i++) {
            menuTexts[i].setColor(i == currentOption ? Color.RED : Color.BLACK);
        }
    }

    private void registerKeyboardEvents() {
        Keyboard keyboard = new Keyboard(this);
        int[] keys = {KeyboardEvent.KEY_UP, KeyboardEvent.KEY_DOWN, KeyboardEvent.KEY_ENTER, KeyboardEvent.KEY_ESC, KeyboardEvent.KEY_P};
        for (int key : keys) {
            keyboard.addEventListener(createKeyboardEvent(key, KeyboardEventType.KEY_PRESSED));
            keyboard.addEventListener(createKeyboardEvent(key, KeyboardEventType.KEY_RELEASED));
        }
    }

    private KeyboardEvent createKeyboardEvent(int key, KeyboardEventType type) {
        KeyboardEvent event = new KeyboardEvent();
        event.setKey(key);
        event.setKeyboardEventType(type);
        return event;
    }

    @Override
    public void keyPressed(KeyboardEvent e) {
        switch (e.getKey()) {
            case KeyboardEvent.KEY_UP:
                currentOption = (currentOption - 1 + menuOptions.length) % menuOptions.length;
                updateMenuDisplay();
                break;
            case KeyboardEvent.KEY_DOWN:
                currentOption = (currentOption + 1) % menuOptions.length;
                updateMenuDisplay();
                break;
            case KeyboardEvent.KEY_ENTER:
                executeSelectedOption();
                break;
            case KeyboardEvent.KEY_ESC:
                escPressed = true;
                hideConfig();
                break;
            case KeyboardEvent.KEY_P:
                pPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent e) {
        // Optional: Implement if needed for finer control over key events
    }

    private void executeSelectedOption() {
        switch (currentOption) {
            case 0:
                // Start the game
                startNewGame();
                break;
            case 1:
                // Open configuration settings
                openConfig();
                break;
            case 2:
                // Exit the game
                System.exit(0);
                break;
            case 3:
                startNewGame();
                break;
        }
    }


    public void openConfig(){
        slime2 = new Slime();
        hideMenu();
        canvas.show(slime2.getSlime());
    }

    public void hideConfig(){
        canvas.hide(slime2.getSlime());
        initMenu();
    }

    public void startNewGame(){
        hideMenu();
        game = new Game(canvas);
    }

}