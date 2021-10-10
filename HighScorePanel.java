import java.io.*; // OLD
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class HighScorePanel extends JPanel
{
   
   public HighScorePanel(ActionListener lis)
   {      
      setLayout(new BorderLayout());
      setBorder(new EmptyBorder(10, 10, 10, 10));
      
      JLabel label = new JLabel("High Scores");
      label.setFont(new Font("Sans-Serif", Font.BOLD, 50));
      label.setHorizontalAlignment(SwingConstants.CENTER);
      add(label, BorderLayout.NORTH);
      
      JPanel highscores = new JPanel();
      highscores.setLayout(new BorderLayout());
      
      JPanel rankpanel = new JPanel();
      rankpanel.setLayout(new GridLayout(10, 1, 5, 5));
      
      JPanel actualscorepanel = new JPanel();
      actualscorepanel.setLayout(new GridLayout(10, 2, 5, 5));
      
      int[] scores = new int[10];
      String[] names = new String[10];
      
      for (int i = 0; i < 10; i++) {
         names[i] = "NONE";
         scores[i] = 0;
      }
      
      try {
    	  Scanner in = new Scanner(new File("highscores.txt"));
      
         for (int i = 0; i < 10; i++) {
            String line = in.nextLine();
            names[i] = line.substring(0, line.lastIndexOf(" "));
            scores[i] = Integer.parseInt(line.substring(line.lastIndexOf(" ") + 1));
         }
         in.close();

      }
      catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Error loading high scores.");
      }
      
      JLabel[] rank = new JLabel[10];
      JLabel[] name = new JLabel[10];
      JLabel[] score = new JLabel[10];
      
      for (int i = 0; i < 10; i++) {
         rank[i] = new JLabel("#" + (i+1));
         rank[i].setHorizontalAlignment(SwingConstants.CENTER);
         rank[i].setFont(new Font("Sans-Serif", Font.BOLD, 20));
         rankpanel.add(rank[i]);
         name[i] = new JLabel(names[i]);
         name[i].setHorizontalAlignment(SwingConstants.CENTER);
         name[i].setFont(new Font("Sans-Serif", Font.BOLD, 20));
         score[i] = new JLabel("" + scores[i]);
         score[i].setHorizontalAlignment(SwingConstants.CENTER);
         score[i].setFont(new Font("Sans-Serif", Font.BOLD, 20));
         actualscorepanel.add(name[i]);
         actualscorepanel.add(score[i]);
      }
      
      highscores.add(rankpanel, BorderLayout.WEST);
      highscores.add(actualscorepanel, BorderLayout.CENTER);
      
      add(highscores, BorderLayout.CENTER);
      
      JPanel buttons = new JPanel();
      buttons.setLayout(new FlowLayout());
      
      JButton reset = new JButton("Back");
      reset.addActionListener(lis);
      
      buttons.add(reset);
      
      add(buttons, BorderLayout.SOUTH);
   }
}