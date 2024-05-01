package music;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    List<URL> soundList = new ArrayList<>();

    public Sound() {
        soundList.add(getClass().getResource("/res/sounds/BlueBoyAdventure.wav"));
        soundList.add(getClass().getResource("/res/sounds/coin.wav"));
        soundList.add(getClass().getResource("/res/sounds/powerup.wav"));
        soundList.add(getClass().getResource("/res/sounds/unlock.wav"));
        soundList.add(getClass().getResource("/res/sounds/fanfare.wav"));
    }

    public void setSound(int index) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundList.get(index));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playSound() {
        clip.start();
    }

    public void stopSound() {
        clip.stop();
    }

    public void loopSound() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stopLoop() {
        clip.loop(0);
    }

    public void playSound(int index) {
        setSound(index);
        playSound();
    }
}
