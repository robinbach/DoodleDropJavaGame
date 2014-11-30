package doodledrop;

import java.awt.FlowLayout;


import javax.swing.*;

public class DebugWindow extends JFrame
{
  /**
   * for safety
   */
  private static final long serialVersionUID = 1L;

  static JLabel position, bar1, health, score;

 


  public DebugWindow()
  {
    super("Doodle _(:3Z)_ Drop");

    //setMinimumSize(new Dimension(400, 120));
    setBounds(600, 0, 400, 120);

    setLayout(new FlowLayout());
    
    health = new JLabel();
    add(health);
    
    score = new JLabel();
    add(score);
    
    JLabel positionLabel = new JLabel("player's location: ");
    add(positionLabel);
    position = new JLabel();
    add(position);

    JLabel barLabel = new JLabel("Top bar's location: ");
    add(barLabel);

    bar1 = new JLabel();
    add(bar1);
    
    JTextArea instructLabel =
    		new JTextArea("Keyboard listener is in both window"
    		+ "\n control: left,right,up."
    		+ "\n kill the player: press k"
    		+ "\n close selected window _(:3Z)_: press q");
    instructLabel.setEditable(false);
    instructLabel.addKeyListener(new GameKbdListener());
    add(instructLabel);

    setVisible(true);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

//    addKeyListener(new GameKbdListener());

//    setDefaultCloseOperation(EXIT_ON_CLOSE);

  }
}
