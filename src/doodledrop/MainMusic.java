package doodledrop;

import java.applet.Applet;
import java.applet.AudioClip;

import doodledrop.control.MainControl;


public class MainMusic
{
  private static AudioClip normal;
  private static AudioClip spring;
  private static AudioClip killing;
  private static AudioClip disappear;
  private static AudioClip win;
  private static AudioClip lose;
  
  public MainMusic()
  {
    normal = Applet.newAudioClip(MainMusic.class.getClassLoader().getResource("image/sound/normal.wav"));
    spring = Applet.newAudioClip(MainMusic.class.getClassLoader().getResource("image/sound/spring.wav"));
    killing = Applet.newAudioClip(MainMusic.class.getClassLoader().getResource("image/sound/killing.wav"));
    disappear = Applet.newAudioClip(MainMusic.class.getClassLoader().getResource("image/sound/disappear.wav"));
    win = Applet.newAudioClip(MainMusic.class.getClassLoader().getResource("image/sound/win.wav"));
    lose = Applet.newAudioClip(MainMusic.class.getClassLoader().getResource("image/sound/lose.wav"));
  }
  
  public void playNormal()
  {
    if (MainControl.BGM.isEnabled())
      normal.play();
  }
  
  public void playSprint()
  {
    if (MainControl.BGM.isEnabled())
      spring.play();
  }
  public void playKilling()
  {
    if (MainControl.BGM.isEnabled())
      killing.play();
  }
  public void playDisappear()
  {
    if (MainControl.BGM.isEnabled())
      disappear.play();
  }
  public void playWin()
  {
    if (MainControl.BGM.isEnabled())
      win.play();
  }
  public void playLose()
  {
    if (MainControl.BGM.isEnabled())
      lose.play();
  }
}

