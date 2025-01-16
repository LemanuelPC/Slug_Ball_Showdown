package io.codeforall.javatars_filhosdamain;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Game {
    private Match match;
    private static final int DELAY = 10;
    private Menu menu;
    private Config config;
    private MatchOptions matchOptions;
    private BackgroundChooser backgroundChooser;
    private CharacterChooser characterChooser;
    private SoundsControls soundsControls;
    private KeyboardListener keyboardListener;
    private Sounds matchMusic, hittingGoalSound, goalSound;
    private boolean matchOpen;
    private boolean matchPause;
    private boolean menuOpen;
    private boolean configOpen;
    private boolean matchOptionsOpen;
    private boolean backgroundChooserOpen;
    private boolean characterChooserOpen;
    private boolean soundsControlsOpen;

    public void init() {
        this.keyboardListener = new KeyboardListener();
        this.menu = new Menu(this);
        this.config = new Config(this);
        this.matchOptions = new MatchOptions(this);
        this.backgroundChooser = new BackgroundChooser(this);
        this.characterChooser = new CharacterChooser(this);
        try {
            this.matchMusic = new Sounds("/data/sound/futebol.wav");
            this.hittingGoalSound = new Sounds("/data/sound/hittingPost.wav");
            this.goalSound = new Sounds("/data/sound/goal.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        this.soundsControls = new SoundsControls(this);
        this.match = new Match(this, matchOptions.timeLimit, matchOptions.maxGoals, backgroundChooser.backgroundChoice, characterChooser.characterP1, characterChooser.characterP2, matchMusic, hittingGoalSound, goalSound);
        //keyboardListener.setEntity(menu);
        setMenuOpen(true);


        while (true) {
            /*System.out.println("Menu Open? - " + menuOpen);
            System.out.println("Menu Visible? - " + menu.isVisible);
            System.out.println("Config Open? - " + configOpen);
            System.out.println("Config Visible? - " + config.isVisible);
            System.out.println("Match Options Open? - " + matchOptionsOpen);
            System.out.println("Match Options Visible? - " + matchOptions.isVisible);*/
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (matchOpen && !match.initiated) {
                reloadOptions();
                match.init();
            }

            if (configOpen && !config.isVisible){
                config.display();
            }

            if (menuOpen && !menu.isVisible){
                menu.display();
            }
            if (matchOptionsOpen && !matchOptions.isVisible){
                matchOptions.display();
            }
            if (backgroundChooserOpen && !backgroundChooser.isVisible){
                backgroundChooser.display();
            }
            if (characterChooserOpen && !characterChooser.isVisible){
                characterChooser.display();
            }
            if (soundsControlsOpen && !soundsControls.isVisible){
                soundsControls.display();
            }
        }
    }

    public void setKeyboardListenerEntity(Interactable event){
        keyboardListener.setEntity(event);
    }

    public void openMenu(){
        if(!menu.isVisible) {
            menu.display();
        }
    }

    public void reloadOptions(){
        this.match = new Match(this, matchOptions.timeLimit, matchOptions.maxGoals, backgroundChooser.backgroundChoice, characterChooser.characterP1, characterChooser.characterP2, matchMusic, hittingGoalSound, goalSound);
    }

    public void resumeGame(){
        match.showGame();
    }

    public void newMatch() {
        match.initiated = false;
        match.isPaused = false;
        this.match = new Match(this, matchOptions.timeLimit, matchOptions.maxGoals, backgroundChooser.backgroundChoice, characterChooser.characterP1, characterChooser.characterP2, matchMusic, hittingGoalSound, goalSound);
    }

    public void setMatchOpen(boolean state) {
        this.matchOpen = state;
    }

    public boolean isMatchPause() {
        return matchPause;
    }

    public void setMatchPause(boolean state) {
        this.matchPause = state;
    }

    public boolean isMenuOpen() {
        return menuOpen;
    }

    public void setMenuOpen(boolean menuOpen) {
        this.menuOpen = menuOpen;
    }

    public void setConfigOpen(boolean configOpen) {
        this.configOpen = configOpen;
    }

    public void setMatchOptionsOpen(boolean matchOptionsOpen) {
        this.matchOptionsOpen = matchOptionsOpen;
    }

    public void setBackgroundChooserOpen(boolean backgroundChooserOpen) {
        this.backgroundChooserOpen = backgroundChooserOpen;
    }

    public void setCharacterChooserOpen(boolean characterChooserOpen) {
        this.characterChooserOpen = characterChooserOpen;
    }

    public void setSoundsControlsOpen(boolean soundsControlsOpen) {
        this.soundsControlsOpen = soundsControlsOpen;
    }

    public Sounds getMatchMusic() {
        return matchMusic;
    }
}
