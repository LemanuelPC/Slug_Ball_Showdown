package io.codeforall.javatars_filhosdamain;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class CharacterChooser implements Interactable{

    Game game;
    boolean isVisible;
    private Picture background;
    private int characterP1Option, characterP2Option;
    private final String[] characterOptions = {"data/sprites/characters/spr_nuderunner.png", "data/sprites/characters/spr_slime_alien.png", "data/sprites/characters/spr_slime_archer.png", "data/sprites/characters/spr_slime_australian.png",
            "data/sprites/characters/spr_slime_basket.png", "data/sprites/characters/spr_slime_bomb.png", "data/sprites/characters/spr_slime_boxer.png", "data/sprites/characters/spr_slime_classic.png", "data/sprites/characters/spr_slime_clock.png",
            "data/sprites/characters/spr_slime_cosmos.png", "data/sprites/characters/spr_slime_cowboy.png", "data/sprites/characters/spr_slime_disco.png", "data/sprites/characters/spr_slime_diver.png", "data/sprites/characters/spr_slime_female.png",
            "data/sprites/characters/spr_slime_fire.png", "data/sprites/characters/spr_slime_fisher.png", "data/sprites/characters/spr_slime_ghost.png", "data/sprites/characters/spr_slime_ice.png", "data/sprites/characters/spr_slime_indian.png",
            "data/sprites/characters/spr_slime_maniac.png", "data/sprites/characters/spr_slime_monk.png", "data/sprites/characters/spr_slime_mud.png", "data/sprites/characters/spr_slime_murder.png", "data/sprites/characters/spr_slime_music.png",
            "data/sprites/characters/spr_slime_nature.png", "data/sprites/characters/spr_slime_robot.png", "data/sprites/characters/spr_slime_runner.png", "data/sprites/characters/spr_slime_scientist.png", "data/sprites/characters/spr_slime_sponge.png",
            "data/sprites/characters/spr_slime_super.png", "data/sprites/characters/spr_slime_traffic.png", "data/sprites/characters/spr_slime_twin.png", "data/sprites/characters/spr_slime_water.png", "data/sprites/characters/spr_twinslime.png"};
    private Picture characterP1ChoicePreview, characterP2ChoicePreview;
    private String[] textOptionsText;
    private Text[] textOptions;
    private Text title;
    String characterP1, characterP2;
    private int currentOption = 0;


    public CharacterChooser(Game game){
        this.game = game;
        this.characterP1Option = 7;
        this.characterP2Option = 13;
        textOptionsText = new String[]{"Player 1 Skin: " + (characterP1Option + 1), "Player 2 Skin: " + (characterP2Option + 1), "Go back"};
        textOptions = new Text[textOptionsText.length];
        this.characterP1 = characterOptions[7];
        this.characterP2 = characterOptions[13];
        this.characterP1ChoicePreview = new Picture(310, 190, characterOptions[characterP1Option]);
        this.characterP2ChoicePreview = new Picture(310, 240, characterOptions[characterP2Option]);

    }

    public void display() {
        game.setKeyboardListenerEntity(this);
        isVisible = true;
        background = new Picture(10, 10, "data/sprites/feild.png");
        background.draw();


        title = new Text(290, 30, "Characters Choose Menu");
        title.setColor(Color.WHITE);
        title.draw();

        for (int i = 0; i < textOptionsText.length; i++) {
            textOptions[i] = new Text(180, 200 + (i * 50), textOptionsText[i]);
            textOptions[i].draw();
        }
        characterP1ChoicePreview.draw();
        characterP2ChoicePreview.draw();

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
        characterP1ChoicePreview.delete();
        characterP2ChoicePreview.delete();
        background.delete();
        title.delete();
        isVisible = false;
        game.setCharacterChooserOpen(false);
    }

    private void executeSelectedOption() {
        switch (currentOption) {
            case 0:
                // Choose Background
                textOptions[0].delete();
                characterP1ChoicePreview.delete();
                characterP1Option++;
                if(characterP1Option == characterOptions.length){
                    characterP1Option = 0;
                }
                textOptionsText[0] = "Player 1 Skin: " + (characterP1Option + 1);
                textOptions[0] = new Text(180, 200, textOptionsText[0]);
                textOptions[0].draw();
                characterP1 = characterOptions[characterP1Option];
                characterP1ChoicePreview = new Picture(310, 190, characterOptions[characterP1Option]);
                characterP1ChoicePreview.draw();
                updateMenuDisplay();
                break;
            case 1:
                // Choose Background
                textOptions[1].delete();
                characterP2ChoicePreview.delete();
                characterP2Option++;
                if(characterP2Option == characterOptions.length){
                    characterP2Option = 0;
                }
                textOptionsText[1] = "Player 2 Skin: " + (characterP2Option + 1);
                textOptions[1] = new Text(180, 250, textOptionsText[1]);
                textOptions[1].draw();
                characterP2 = characterOptions[characterP2Option];
                characterP2ChoicePreview = new Picture(310, 240, characterOptions[characterP2Option]);
                characterP2ChoicePreview.draw();
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
