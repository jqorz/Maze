import java.applet.*;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
public class Sound {
	AudioClip zzz;
	String soundname;
	boolean a = false;
	public void makeSound(String soundname) {
		try {
			URL cb;
			File f = new File(soundname);
			cb = f.toURI().toURL();
			zzz = Applet.newAudioClip(cb);
			zzz.play();
			a=true;
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	public void stop() {
		zzz.stop();
		a=false;
	}
	public boolean isPlaying() {
		return a;
	}

}
