import javax.swing.JFrame;
import java.awt.event.*;

public class TetrisDriver
{
   private static JFrame main, game, controls, high;
   public static void main(String[] args)
   {
      main = new JFrame("Tetris");
      main.setLocation(100, 100);
      main.setSize(600, 800);
      main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      main.setContentPane(new PanelMainMenu(new Listener(), new Listener2(), new Listener3()));
      main.setVisible(true);
      
      high = new JFrame("Tetris - High Scores");
      high.setLocation(100, 100);
      high.setSize(600, 800);
      high.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      controls = new JFrame("Tetris - High Scores");
      controls.setLocation(100, 100);
      controls.setSize(600, 800);
      controls.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   private static class Listener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         main.setVisible(false);
         game = new JFrame("Tetris");
         game.setLocation(100, 100);
         game.setSize(600, 800);
         game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         game.setContentPane(new GamePanel(new Listener6()));
         game.setVisible(true);
      }
   }
   private static class Listener2 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         main.setVisible(false);
         high.setContentPane(new HighScorePanel(new Listener5()));
         high.setVisible(true);
      }
   }
   private static class Listener3 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         main.setVisible(false);
         controls.setContentPane(new ControlPanel(new Listener4()));
         controls.setVisible(true);
      }
   }
   private static class Listener4 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         main.setVisible(true);
         controls.setVisible(false);
      }
   }
   private static class Listener5 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         main.setVisible(true);
         high.setVisible(false);
      }
   }
   private static class Listener6 implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         game.setVisible(false);
         high.setContentPane(new HighScorePanel(new Listener5()));
         high.setVisible(true);
      }
   }
}