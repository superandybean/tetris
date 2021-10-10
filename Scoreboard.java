import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.net.URL;

public class Scoreboard extends JPanel {
   JLabel currentscore, highscore, currentlevel;
   JLabel[] nextpiece;
   JLabel countdown;
   JPanel centerpanel, buttons;
   JLabel hold, heldblock;
   JButton reset, highscorebutton;
   static JButton pause;
   JPanel pausepanel;
   int[] scores;
   String[] names;
   boolean displayOnce = false;
   static boolean gamepause = false;
   private static int score = 0, high = 0, level = 1;
   public Scoreboard(ActionListener lis, ActionListener lis2, ActionListener lis3) {
   
	   score = 0; 
	   high = 0; 
	   level = 1;
	   
      scores = new int[11];
      names = new String[11];
      
      for (int i = 0; i < 11; i++) {
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
   
      setLayout(new BorderLayout());
      
      JPanel scorespanel = new JPanel();
      scorespanel.setLayout(new GridLayout(2, 1, 10, 10));
      
      JPanel current = new JPanel();
      current.setLayout(new GridLayout(2, 2, 5, 5));
      
      currentscore = new JLabel("0");
      currentscore.setFont(new Font("Sans-Serif", Font.BOLD, 40));
      currentscore.setHorizontalAlignment(SwingConstants.CENTER);
      
      JLabel currentlabel = new JLabel("Score:");
      currentlabel.setFont(new Font("Sans-Serif", Font.BOLD, 30));
      currentlabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      JLabel levellabel = new JLabel("Level:");
      levellabel.setFont(new Font("Sans-Serif", Font.BOLD, 30));
      levellabel.setHorizontalAlignment(SwingConstants.CENTER);
      
      currentlevel = new JLabel("1");
      currentlevel.setFont(new Font("Sans-Serif", Font.BOLD, 40));
      currentlevel.setHorizontalAlignment(SwingConstants.CENTER);
      
      current.add(currentlabel);
      current.add(currentscore);
      current.add(levellabel);
      current.add(currentlevel);
      
      high = scores[0];
      
      JPanel highpanel = new JPanel();
      highpanel.setLayout(new BorderLayout());
      highscore = new JLabel("" + high);
      highscore.setFont(new Font("Sans-Serif", Font.BOLD, 40));
      highscore.setHorizontalAlignment(SwingConstants.CENTER);
      JLabel highlabel = new JLabel("High Score:");
      highlabel.setFont(new Font("Sans-Serif", Font.BOLD, 30));
      highlabel.setHorizontalAlignment(SwingConstants.CENTER);
      highpanel.add(highlabel, BorderLayout.NORTH);
      highpanel.add(highscore, BorderLayout.CENTER);
      
      scorespanel.add(current);
      scorespanel.add(highpanel);
      
      add(scorespanel, BorderLayout.NORTH);
      
      centerpanel = new JPanel();
      centerpanel.setLayout(new BorderLayout());
      countdown = new JLabel("3");
      countdown.setFont(new Font("Sans-Serif", Font.BOLD, 100));
      countdown.setHorizontalAlignment(SwingConstants.CENTER);
      centerpanel.add(countdown, BorderLayout.CENTER);
      
      hold = new JLabel("HOLD");
      hold.setFont(new Font("Sans-Serif", Font.BOLD, 30));
      hold.setHorizontalAlignment(SwingConstants.CENTER);
      heldblock = new JLabel();
      heldblock.setHorizontalAlignment(SwingConstants.CENTER);
      
      pausepanel = new JPanel();
      pausepanel.setLayout(new FlowLayout());
      
      pause = new JButton("PAUSE");
      pause.setFont(new Font("Sans-Serif", Font.BOLD, 15));
      pause.setHorizontalAlignment(SwingConstants.CENTER);
      pause.addActionListener(lis3);
      pause.setFocusable(false);
      pausepanel.add(pause);
      
      buttons = new JPanel();
      buttons.setLayout(new FlowLayout());
      
      reset = new JButton("Reset");
      reset.setFont(new Font("Sans-Serif", Font.BOLD, 15));
      reset.addActionListener(lis);
      reset.setHorizontalAlignment(SwingConstants.CENTER);
      
      highscorebutton = new JButton("High Scores");
      highscorebutton.setFont(new Font("Sans-Serif", Font.BOLD, 15));
      highscorebutton.addActionListener(lis2);
      highscorebutton.setHorizontalAlignment(SwingConstants.CENTER);
      
      buttons.add(reset);
      buttons.add(highscorebutton);
      
      add(centerpanel, BorderLayout.CENTER);
      
      JPanel nextpieces = new JPanel();
      nextpieces.setLayout(new BorderLayout());
      
      JPanel pieces = new JPanel();
      pieces.setLayout(new GridLayout(4, 1, 10, 10));
      JLabel nextlabel = new JLabel("NEXT");
      nextlabel.setHorizontalAlignment(SwingConstants.CENTER);
      nextlabel.setFont(new Font("Sans-Serif", Font.BOLD, 50));
      pieces.add(nextlabel);
      
      nextpiece = new JLabel[3];
      for (int i = 0; i < nextpiece.length; i++) {
         nextpiece[i] = new JLabel();
         nextpiece[i].setHorizontalAlignment(SwingConstants.CENTER);
         pieces.add(nextpiece[i]);
      }
      
      nextpieces.add(pieces, BorderLayout.SOUTH);
      
      add(nextpieces, BorderLayout.SOUTH);
   }
   
   public void displayBlocks(TetrisBlock[] arr) {
      for (int i = 0; i < arr.length; i++) {
         if (arr[i] instanceof StraightTetris) {
        	 URL pic = this.getClass().getResource("images/straight.png");
            nextpiece[i].setIcon(new ImageIcon(pic));
         }
         else if (arr[i] instanceof SquareTetris) {
        	 URL pic = this.getClass().getResource("images/square.png");
        	 nextpiece[i].setIcon(new ImageIcon(pic));
         }
         else if (arr[i] instanceof JTetris) {
        	 URL pic = this.getClass().getResource("images/J.png");
        	 nextpiece[i].setIcon(new ImageIcon(pic));
         }
         else if (arr[i] instanceof LTetris) {
        	 URL pic = this.getClass().getResource("images/L.png");
        	 nextpiece[i].setIcon(new ImageIcon(pic));
         }
         else if (arr[i] instanceof STetris) {
        	 URL pic = this.getClass().getResource("images/S.png");
        	 nextpiece[i].setIcon(new ImageIcon(pic));
         }
         else if (arr[i] instanceof ZTetris) {
        	 URL pic = this.getClass().getResource("images/Z.png");
        	 nextpiece[i].setIcon(new ImageIcon(pic));
         }
         else if (arr[i] instanceof TTetris) {
        	 URL pic = this.getClass().getResource("images/T.png");
        	 nextpiece[i].setIcon(new ImageIcon(pic));
         }
      }
   } 
   
   public void add(int n) {
      score += n;
      currentscore.setText("" + score);
      if (score > high) {
         high = score;
         highscore.setText("" + score);
      }
   } 
   
   public void hold(TetrisBlock block) {
      if (block instanceof StraightTetris) {
         URL pic = this.getClass().getResource("images/straight.png");
         heldblock.setIcon(new ImageIcon(pic));
      }
      else if (block instanceof SquareTetris) {
    	  URL pic = this.getClass().getResource("images/square.png");
    	  heldblock.setIcon(new ImageIcon(pic));
      }
      else if (block instanceof JTetris) {
    	  URL pic = this.getClass().getResource("images/J.png");
    	  heldblock.setIcon(new ImageIcon(pic));
      }
      else if (block instanceof LTetris) {
    	  URL pic = this.getClass().getResource("images/L.png");
    	  heldblock.setIcon(new ImageIcon(pic));
      }
      else if (block instanceof STetris) {
    	  URL pic = this.getClass().getResource("images/S.png");
    	  heldblock.setIcon(new ImageIcon(pic));
      }
      else if (block instanceof ZTetris) {
    	  URL pic = this.getClass().getResource("images/Z.png");
    	  heldblock.setIcon(new ImageIcon(pic));
      }
      else if (block instanceof TTetris) {
    	  URL pic = this.getClass().getResource("images/T.png");
    	  heldblock.setIcon(new ImageIcon(pic));
      }
   }
   
   public void lose() {
      countdown.setFont(new Font("Sans-Serif", Font.BOLD, 30));
      countdown.setText("GAME OVER!");
      centerpanel.remove(hold);
      centerpanel.remove(heldblock);
      centerpanel.remove(pausepanel);
      
      if (score > high) {
         high = score;
         highscore.setText("" + score);
      }

      
      scores[10] = score;
      int place = findPlace(scores);
      
      if (place != 11 && displayOnce == false) {
    	  displayOnce = true;
    	  String inputname = JOptionPane.showInputDialog("Congratulations! Your score was #" + (place+1) + "! \nPlease enter your name:");
    	  names[10] = inputname;
     	 sort(scores, names);
      }	 
    	 
    	 try {
    		 PrintStream outfile = new PrintStream("highscores.txt");
             for(int i = 0; i < 10; i++) {
                 outfile.println(names[i] + " " + scores[i]);
              }
             outfile.close();
          }
          catch(Exception e) {
             JOptionPane.showMessageDialog(null,"The file could not be created.");
          }
      
      
      centerpanel.add(countdown, BorderLayout.CENTER);
      centerpanel.add(buttons, BorderLayout.SOUTH);
      revalidate();
      repaint();
   } 
   
   public int findPlace(int[] array) { 
      for (int i = 0; i < 10; i++) {
         if (array[10] > array[i]) {
            return i;
         }
      }
      return 11;
   }
   
   public void countdowntext(int n) {
      countdown.setFont(new Font("Sans-Serif", Font.BOLD, 100));
      countdown.setText("" + n);
      if (n == 0) {
         centerpanel.remove(countdown);
         centerpanel.add(hold, BorderLayout.NORTH);
         centerpanel.add(heldblock, BorderLayout.CENTER);
         centerpanel.add(pausepanel, BorderLayout.SOUTH);
         revalidate();
         repaint();
      }
   } 
   
   public void sort(int[] arr, String[] arr2){ //insertion sort,
	      for (int i = 1; i < arr.length; ++i) { 
	         int key = arr[i]; 
	         String key2 = arr2[i];
	         int j = i - 1; 
	         while (j >= 0 && arr[j] < key) { 
	            arr[j + 1] = arr[j]; 
	            arr2[j + 1] = arr2[j]; 
	            j = j - 1; 
	         } 
	         arr[j + 1] = key; 
	         arr2[j + 1] = key2; 
	      } 
	   }
   
   public void reset() {
      score = 0;
      level = 1;
      
      displayOnce = false;
      
      for (int i = 0; i < nextpiece.length; i++) {
         nextpiece[i].setIcon(null);
      }
      heldblock.setIcon(null);
      
      currentlevel.setText("" + level);
      currentscore.setText("" + score);
      countdown.setFont(new Font("Sans-Serif", Font.BOLD, 100));
      countdown.setText("3");
      centerpanel.remove(buttons);
      revalidate();
      repaint();
   } 
   
   public void levelUp() {
      level++;
      currentlevel.setText("" + level);
   }
   
   public boolean getPause() {
	   return gamepause;
   }
   
      public void pause()
      {
         if(gamepause) {
        	 pause.setText("PAUSE");
        	 gamepause = false;
         }
         else {
        	 pause.setText("UNPAUSE");
        	 gamepause = true;
         }
   }
}