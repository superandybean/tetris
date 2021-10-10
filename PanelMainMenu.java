import java.awt.*;
import java.awt.event.*;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.border.*;

public class PanelMainMenu extends JPanel
{
	private static AudioInputStream inputStream;
	   private static Clip clip;
	   InputStream is;
   public PanelMainMenu(ActionListener lis, ActionListener lis2, ActionListener lis3)
   {
      setLayout(new BorderLayout());
      setBorder(new EmptyBorder(30, 30, 30, 30));

      JLabel label = new JLabel("TETRIS");
      label.setFont(new Font("Sans-Serif", Font.BOLD, 100));
      label.setHorizontalAlignment(SwingConstants.CENTER);
      add(label, BorderLayout.NORTH);

      JPanel buttons = new JPanel();
      add(buttons, BorderLayout.CENTER);
      buttons.setLayout(new GridLayout(4, 1, 50, 50));
      buttons.setOpaque(false);


      JButton start = new JButton("Play");
      start.addActionListener(lis);
      start.setFont(new Font("Sans-Serif", Font.BOLD, 50));
      buttons.add(start);

      JButton high = new JButton("High Scores");
      high.addActionListener(lis2);
      high.setFont(new Font("Sans-Serif", Font.BOLD, 50));
      buttons.add(high);

      JButton controls = new JButton("Controls");
      controls.addActionListener(lis3);
      controls.setFont(new Font("Sans-Serif", Font.BOLD, 50));
      buttons.add(controls);

      JButton quit = new JButton("Quit");
      quit.addActionListener(new Listener2());
      quit.setFont(new Font("Sans-Serif", Font.BOLD, 50));
      buttons.add(quit);

      // playClip("src/tetris.wav");
   }

   private class Listener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.exit(0);
      }
   }
   public void playClip(String soundclip)
   {
      try
      {
    	  is = this.getClass().getResourceAsStream("tetris.wav");
    	  inputStream = AudioSystem.getAudioInputStream(is);
         DataLine.Info lineInfo = new DataLine.Info(Clip.class, inputStream.getFormat());
         clip = (Clip)AudioSystem.getLine(lineInfo);
         clip.open(inputStream);
         clip.loop(Clip.LOOP_CONTINUOUSLY);
         FloatControl gainControl =
        		    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        		gainControl.setValue(-15.0f);
         clip.start();
         //inputStream.close();
         //clip.close();
      }
      catch(Exception e)
      {
         System.out.println(e.getMessage());
         System.out.println(e.getClass());
      }
   }

}
