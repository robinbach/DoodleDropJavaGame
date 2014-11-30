package doodledrop;

import java.applet.Applet;
import java.applet.AudioClip;


public class MainMusic
{
  private static AudioClip normal;
  private static AudioClip spring;
  private static AudioClip killing;
  private static AudioClip disappear;
  
  public MainMusic()
  {
    normal = Applet.newAudioClip(MainMusic.class.getClassLoader().getResource("image/sound/normal.wav"));
    spring = Applet.newAudioClip(MainMusic.class.getClassLoader().getResource("image/sound/spring.wav"));
    killing = Applet.newAudioClip(MainMusic.class.getClassLoader().getResource("image/sound/killing.wav"));
    disappear = Applet.newAudioClip(MainMusic.class.getClassLoader().getResource("image/sound/disappear.wav"));
    System.out.println("end");
  }
  
  public void playNormal()
  {
    normal.play();
  }
  
  public void playSprint()
  {
    spring.play();
  }
  public void playKilling()
  {
    killing.play();
  }
  public void playDisappear()
  {
    disappear.play();
  }
}

