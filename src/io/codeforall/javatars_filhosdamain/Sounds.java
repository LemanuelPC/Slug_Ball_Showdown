package io.codeforall.javatars_filhosdamain;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Sounds implements Runnable {

    private Clip backgroundMusic;
    private AudioInputStream audioInputStream;
    private FloatControl gainControl;
    String path;

    public Sounds(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.path = path;
        InputStream resourceStream = getClass().getResourceAsStream(path);
        if (resourceStream == null) {
            throw new IOException("Resource not found: " + path);
        }
        BufferedInputStream bufferedIn = new BufferedInputStream(resourceStream);
        this.audioInputStream = AudioSystem.getAudioInputStream(bufferedIn);

        this.backgroundMusic = AudioSystem.getClip();
        this.backgroundMusic.open(audioInputStream);

        gainControl = (FloatControl) backgroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
        // The music is now ready to be played but not played here directly
    }

    @Override
    public void run() {
        playMusic(); // Call this to start playing music
    }

    public void runOnce(){
        if (backgroundMusic.isRunning()) {
            backgroundMusic.stop(); // Stop the clip if it's currently running
        }
        backgroundMusic.setFramePosition(10); // Rewind to the beginning of the clip
        backgroundMusic.start(); // Start playing the sound effect
    }

    public void playMusic() {
        backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY); // Start playing music
    }

    public void stopMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop(); // Stop the music
        }
    }

    public void resumeMusic() {
        if (backgroundMusic != null && !backgroundMusic.isRunning()) {
            backgroundMusic.start(); // Resume music from where it was stopped
        }
    }

    public void setVolume(int percentage) {
        float min = gainControl.getMinimum();
        float max = gainControl.getMaximum();
        // Correctly calculate the volume level within the gain control's range
        float volume = min + ((max - min) * (percentage / 100.0f));
        // Ensure the percentage is converted to a float for accurate division
        gainControl.setValue(volume);
    }

}