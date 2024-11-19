import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

class MiBoton extends JButton{

  public MiBoton (String text){
    super(text);
    setPreferredSize(new Dimension((int) getPreferredSize().getWidth()+1, 27));
    setBackground(new Color(200,200,200));
    setForeground(new Color(0,0,0));
  }

}