package io.codeforall.javatars_filhosdamain;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SoundsControls implements Interactable{

    Game game;
    boolean isVisible;
    private Picture background;
    private int volume;
    private String[] volumeOptionsText;
    private Text[] textOptions;
    private Text title;
    private int currentOption = 0;


    public SoundsControls(Game game){
        this.game = game;
        this.volume = 10;
        volumeOptionsText = new String[]{"Match Music Volume: " + (volume*10) + "%", "Go back"};
        textOptions = new Text[volumeOptionsText.length];
    }

    public void display() {
        game.setKeyboardListenerEntity(this);
        isVisible = true;
        background = new Picture(10, 10, "data/sprites/feild.png");
        background.draw();


        title = new Text(290, 30, "Sounds Controls Menu");
        title.setColor(Color.WHITE);
        title.draw();

        for (int i = 0; i < volumeOptionsText.length; i++) {
            textOptions[i] = new Text(180, 200 + (i * 50), volumeOptionsText[i]);
            textOptions[i].draw();
        }

        updateMenuDisplay();
    }

    public void updateMenuDisplay() {
        for (int i = 0; i < textOptions.length; i++) {
            textOptions[i].setColor(i == currentOption ? Color.RED : Color.BLACK);
        }
    }

    @Override
    public void setKey(int key, boolean state) {
        if (key == KeyboardEvent.KEY_UP){
            currentOption = (currentOption - 1 + textOptions.length) % textOptions.length;
            updateMenuDisplay();
        }
        if (key == KeyboardEvent.KEY_DOWN){
            currentOption = (currentOption + 1) % textOptions.length;
            updateMenuDisplay();
        }
        if (key == KeyboardEvent.KEY_SPACE) {
            executeSelectedOption();
        }
    }

    public void clearDisplay(){
        for (int i = 0; i < textOptions.length; i++) {
            textOptions[i].delete();
        }
        background.delete();
        title.delete();
        isVisible = false;
        game.setSoundsControlsOpen(false);
    }

    private void executeSelectedOption() {
        switch (currentOption) {
            case 0:
                // Choose Volume
                textOptions[0].delete();
                volume++;
                if(volume > 10){
                    volume = 0;
                    volumeOptionsText[0] = "Match Music Volume: Muted";
                    textOptions[0] = new Text(180, 200, volumeOptionsText[0]);
                    textOptions[0].draw();
                    updateMenuDisplay();
                    break;
                }
                volumeOptionsText[0] = "Match Music Volume: " + (volume*10) + "%";
                textOptions[0] = new Text(180, 200, volumeOptionsText[0]);
                textOptions[0].draw();
                updateMenuDisplay();
                break;
            case 1:
                // Go Back
                clearDisplay();
                game.getMatchMusic().setVolume(volume*10);
                game.setConfigOpen(true);
                break;
        }
    }
}
