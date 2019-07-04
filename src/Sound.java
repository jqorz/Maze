import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 声音控制
 */
class Sound {
    private AudioClip audioClip;
    private boolean isPlaying = false;

    void makeSound(String soundname) {
        try {
            URL cb;
            File f = new File(soundname);
            cb = f.toURI().toURL();
            audioClip = Applet.newAudioClip(cb);
            audioClip.play();
            isPlaying = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    void stop() {
        audioClip.stop();
        isPlaying = false;
    }

    boolean isPlaying() {
        return isPlaying;
    }

}
