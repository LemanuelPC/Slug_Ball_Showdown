package io.codeforall.javatars_filhosdamain;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class MatchOptions implements Interactable{
    int maxGoals;
    int timeLimit;
    Game game;
    boolean isVisible;
    private Picture background;
    private final String[] moOptions = {"Goal limit: " + maxGoals, "Time Limit: " + timeLimit + " min", "Go Back"};
    private final Text[] moTexts = new Text[moOptions.length];
    private int currentOption = 0;


    public MatchOptions(Game game){
        this.game = game;
        this.maxGoals = 10;
        this.timeLimit = 0;
        moOptions[0] = "Goal limit: " + maxGoals;
        moOptions[1] = "Time limit: No limit";
    }

    public void display() {
        //System.out.println("Match Options opened");
        game.setKeyboardListenerEntity(this);
        isVisible = true;
        background = new Picture(10, 10, "data/sprites/feild.png");
        background.draw();


        for (int i = 0; i < moOptions.length; i++) {
            moTexts[i] = new Text(290, 120 + (i * 20), moOptions[i]);
            //configTexts[i].grow(40,20);
            moTexts[i].draw();
        }

        updateMenuDisplay();
    }

    public void updateMenuDisplay() {
        for (int i = 0; i < moTexts.length; i++) {
            moTexts[i].setColor(i == currentOption ? Color.RED : Color.BLACK);
        }
    }

    @Override
    public void setKey(int key, boolean state) {
        if (key == KeyboardEvent.KEY_UP){
            currentOption = (currentOption - 1 + moOptions.length) % moOptions.length;
            updateMenuDisplay();
        }
        if (key == KeyboardEvent.KEY_DOWN){
            currentOption = (currentOption + 1) % moOptions.length;
            updateMenuDisplay();
        }
        if (key == KeyboardEvent.KEY_SPACE) {
            executeSelectedOption();
        }
    }

    public void clearDisplay(){
        for (int i = 0; i < moOptions.length; i++) {
            moTexts[i].delete();
        }
        background.delete();
        isVisible = false;
        game.setMatchOptionsOpen(false);
    }

    private void executeSelectedOption() {
        switch (currentOption) {
            case 0:
                // Goal Limit
                moTexts[0].delete();
                maxGoals++;
                if (maxGoals > 10){
                    maxGoals = 0;
                    moOptions[0] = "Goal limit: No limit";
                    moTexts[0] = new Text(290, 120, moOptions[0]);
                    moTexts[0].draw();
                    updateMenuDisplay();
                    break;
                }
                moOptions[0] = "Goal limit: " + maxGoals;
                moTexts[0] = new Text(290, 120, moOptions[0]);
                moTexts[0].draw();
                updateMenuDisplay();
                break;
            case 1:
                // Time Limit
                moTexts[1].delete();
                timeLimit++;
                if (timeLimit > 5){
                    timeLimit = 0;
                    moOptions[1] = "Time limit: No limit";
                    moTexts[1] = new Text(290, 120 + 20, moOptions[1]);
                    moTexts[1].draw();
                    updateMenuDisplay();
                    break;
                }
                moOptions[1] = "Time limit: " + timeLimit + " min";
                moTexts[1] = new Text(290, 120 + 20, moOptions[1]);
                moTexts[1].draw();
                updateMenuDisplay();
                break;
            case 2:
                // Go Back
                clearDisplay();
                game.reloadOptions();
                game.setConfigOpen(true);
                break;
        }
    }
}
