package io.codeforall.javatars_filhosdamain;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class BackgroundChooser implements Interactable {

    Game game;
    boolean isVisible;
    private Picture background;
    private int bgOption;
    private final String[] bgOptions = {"data/backgrounds/bg_castle.png", "data/backgrounds/bg_desert.png", "data/backgrounds/bg_disco.png", "data/backgrounds/bg_forest.png", "data/backgrounds/bg_lab.png", "data/backgrounds/bg_moon.png",
            "data/backgrounds/bg_runnertrack.png", "data/backgrounds/bg_soccerfield.png", "data/backgrounds/bg_underwater.png", "data/backgrounds/bg_urban.png", "data/backgrounds/bg_winter.png", "data/backgrounds/estadio.png",
            "data/backgrounds/estadio2.png", "data/backgrounds/estadio6.png"};
    private final String[] bgOptionsPreview = {"data/backgrounds/bg_castle_preview.png", "data/backgrounds/bg_desert_preview.png", "data/backgrounds/bg_disco_preview.png", "data/backgrounds/bg_forest_preview.png", "data/backgrounds/bg_lab_preview.png",
            "data/backgrounds/bg_moon_preview.png", "data/backgrounds/bg_runnertrack_preview.png", "data/backgrounds/bg_soccerfield_preview.png", "data/backgrounds/bg_underwater_preview.png", "data/backgrounds/bg_urban_preview.png",
            "data/backgrounds/bg_winter_preview.png", "data/backgrounds/estadio_preview.png", "data/backgrounds/estadio2_preview.png", "data/backgrounds/estadio6_preview.png"};
    private Picture backgroundChoicePreview;
    private String[] textOptionsText;
    private Text[] textOptions;
    private Text title;
    String backgroundChoice;
    private int currentOption = 0;


    public BackgroundChooser(Game game){
        this.game = game;
        this.bgOption = 13;
        textOptionsText = new String[]{"Background: " + (bgOption + 1), "Go back"};
        textOptions = new Text[textOptionsText.length];
        this.backgroundChoice = bgOptions[13];
        this.backgroundChoicePreview = new Picture(415, 200, bgOptionsPreview[bgOption]);
    }

    public void display() {
        game.setKeyboardListenerEntity(this);
        isVisible = true;
        background = new Picture(10, 10, "data/sprites/feild.png");
        background.draw();


        title = new Text(290, 30, "Background Choose Menu");
        title.setColor(Color.WHITE);
        title.draw();

        for (int i = 0; i < textOptionsText.length; i++) {
            textOptions[i] = new Text(145, 200 + (i * 50), textOptionsText[i]);
            textOptions[i].draw();
        }
        backgroundChoicePreview.draw();

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
        backgroundChoicePreview.delete();
        background.delete();
        title.delete();
        isVisible = false;
        game.setBackgroundChooserOpen(false);
    }

    private void executeSelectedOption() {
        switch (currentOption) {
            case 0:
                // Choose Background
                textOptions[0].delete();
                backgroundChoicePreview.delete();
                bgOption++;
                if(bgOption == bgOptions.length){
                    bgOption = 0;
                }
                textOptionsText[0] = "Background: " + (bgOption+1);
                textOptions[0] = new Text(145, 200, textOptionsText[0]);
                textOptions[0].draw();
                backgroundChoice = bgOptions[bgOption];
                backgroundChoicePreview = new Picture(415, 200, bgOptionsPreview[bgOption]);
                backgroundChoicePreview.draw();
                updateMenuDisplay();
                break;
            case 1:
                // Go Back
                clearDisplay();
                game.reloadOptions();
                game.setConfigOpen(true);
                break;
        }
    }
}
