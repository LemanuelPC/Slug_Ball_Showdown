package io.codeforall.javatars_filhosdamain;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Config implements Interactable {

    Game game;
    boolean isVisible;
    private Picture background;
    private final String[] configOptions = {"Match Options", "Background Chooser", "Character Chooser", "Sounds Controls", "Go Back"};
    private final Text[] configTexts = new Text[configOptions.length];
    private int currentOption = 0;


    public Config(Game game){
        this.game = game;
    }

    public void display() {
      //  System.out.println("Config opened");
        game.setKeyboardListenerEntity(this);
        isVisible = true;
        background = new Picture(10, 10, "data/sprites/feild.png");
        background.draw();


        for (int i = 0; i < configOptions.length; i++) {
            configTexts[i] = new Text(290, 120 + (i * 20), configOptions[i]);
            //configTexts[i].grow(40,20);
            configTexts[i].draw();
        }

        updateMenuDisplay();
    }

    public void updateMenuDisplay() {
        for (int i = 0; i < configTexts.length; i++) {
            configTexts[i].setColor(i == currentOption ? Color.RED : Color.BLACK);
        }
    }

    @Override
    public void setKey(int key, boolean state) {
        if (key == KeyboardEvent.KEY_UP){
            currentOption = (currentOption - 1 + configOptions.length) % configOptions.length;
            updateMenuDisplay();
        }
        if (key == KeyboardEvent.KEY_DOWN){
            currentOption = (currentOption + 1) % configOptions.length;
            updateMenuDisplay();
        }
        if (key == KeyboardEvent.KEY_SPACE) {
            executeSelectedOption();
        }
    }

    public void clearDisplay(){
        for (int i = 0; i < configOptions.length; i++) {
            configTexts[i].delete();
        }
        background.delete();
        isVisible = false;
        game.setConfigOpen(false);
    }

    private void executeSelectedOption() {
        switch (currentOption) {
            case 0:
                // Match Options
                clearDisplay();
                game.setMatchOptionsOpen(true);
                break;
            case 1:
                clearDisplay();
                game.setBackgroundChooserOpen(true);
                break;
            case 2:
                // Character Chooser
                clearDisplay();
                game.setCharacterChooserOpen(true);
                break;
            case 3:
                // Sounds Controls
                clearDisplay();
                game.setSoundsControlsOpen(true);
                break;
            case 4:
                // Go Back
                clearDisplay();
                game.setMenuOpen(true);
                break;
        }
    }
}
