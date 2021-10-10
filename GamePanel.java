import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GamePanel extends JPanel
{
   private static int[][] colors;
   private static int[] controls;
   private static JButton[][] grid;
   private static Scoreboard scoreboard;
   private static TetrisBlock block;
   private static TetrisBlock heldblock;
   private static TetrisBlock[] upcoming;
   private static Timer t, countdowntimer;
   private static int countdown = 3;
   private static int timedelay = 500;
   private static boolean down = false;
   private static boolean firsthold = true, held = false;
   private static int level = 1, rowscleared = 0;
   //private static boolean firstturn = true;
   public GamePanel(ActionListener lis) {
      controls = new int[7];
      try {
    	  Scanner in = new Scanner(new File("controls.txt"));
         int i = 0;
         while(in.hasNextInt()) {
            controls[i] = in.nextInt();
            i++;
         }
         in.close();
      }
      catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Controls file not found! Using default controls");
         controls[0] = 37;
         controls[1] = 39;
         controls[2] = 40;
         controls[3] = 38;
         controls[4] = 32;
         controls[5] = 67;
         controls[6] = 80;
      }

      countdown = 3;
      timedelay = 500;
      down = false;
      firsthold = true;
      held = false;
      level = 1;
      rowscleared = 0;

      grid = new JButton[20][10];
      colors = new int[24][10];
      upcoming = new TetrisBlock[3];

      setBorder(new EmptyBorder(10, 10, 10, 10));
      setLayout(new BorderLayout(5, 5));

      JPanel gridPanel = new JPanel();
      gridPanel.setLayout(new GridLayout(20, 10));

      for (int i = grid.length - 1; i >= 0; i--) {
         for (int j = 0; j < grid[0].length; j++) {
            grid[i][j] = new JButton();
            grid[i][j].setEnabled(false);
            grid[i][j].setBackground(Color.LIGHT_GRAY);
            gridPanel.add(grid[i][j]);
         }
      }

      add(gridPanel, BorderLayout.WEST);

      scoreboard = new Scoreboard(new ResetListener(), lis, new PauseListener());
      add(scoreboard, BorderLayout.CENTER);

      for (int i = 0; i < colors.length; i++) {
         for (int j = 0; j < colors[0].length; j++) {
            colors[i][j] = 0;
         }
      }

      addKeyListener(new Key());
      setFocusable(true);

      t = new Timer(500, new TimerListener());
      t.setInitialDelay(0);
      countdowntimer = new Timer(1000, new CountdownListener());

      countdowntimer.start();

      for (int i = 0; i < upcoming.length; i++) {
         double random = Math.random();
         if (random < 1.0/7) {
            upcoming[i] = new StraightTetris(3);
         }
         else if (random < 2.0/7) {
            upcoming[i] = new SquareTetris(4);
         }
         else if (random < 3.0/7) {
            upcoming[i] = new JTetris(3);
         }
         else if (random < 4.0/7){
            upcoming[i] = new LTetris(3);
         }
         else if (random < 5.0/7){
            upcoming[i] = new STetris(3);
         }
         else if (random < 6.0/7) {
            upcoming[i] = new ZTetris(3);
         }
         else {
            upcoming[i] = new TTetris(4);
         }
      }

      //newBlock();
   }

   private static class TimerListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         /*if(left) {
            block.moveLeft(colors);
         }
         if(right) {
            block.moveRight(colors);
         }*/

         if(down) {
            scoreboard.add(1);
         }

         if(block.checkStop(colors)) {
            checkRows();
            held = false;
            if(checkLose()) {
               scoreboard.lose();
               t.stop();
               countdown = 3;
            }
            newBlock();
         }

         block.move(colors);
         draw();
      }
   }
   private static class CountdownListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         countdown--;
         if (countdown == 0) {
            t.start();
            countdowntimer.stop();

            /*for (int i = grid.length - 1; i >= 0; i--) {
               for (int j = 0; j < grid[0].length; j++) {
                  gridPanel.add(grid[i][j]);
               }
            }*/

            newBlock();
         }
         scoreboard.countdowntext(countdown);
      }
   }
   private static class PauseListener implements ActionListener
   {
	   public void actionPerformed(ActionEvent e){

		   pause();
		   scoreboard.pause();
	  }
   }
   public static void pause() {
      if(scoreboard.getPause()) {
     	 t.start();
      }
      else {
     	 t.stop();
      }
   }
   private static class ResetListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         scoreboard.reset();
         countdown = 3;
         down = false;
         timedelay = 500;
         level = 1;
         rowscleared = 0;
         t.setDelay(timedelay);

         firsthold = true;
         held = false;
         heldblock = null;

         for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors[0].length; j++) {
               colors[i][j] = 0;
            }
         }

         for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = 0; j < grid[0].length; j++) {
               grid[i][j].setBackground(Color.LIGHT_GRAY);
            }
         }

         countdowntimer.start();

         for (int i = 0; i < upcoming.length; i++) {
        	 double random = Math.random();
            if (random < 1.0/7) {
               upcoming[i] = new StraightTetris(3);
            }
            else if (random < 2.0/7) {
               upcoming[i] = new SquareTetris(4);
            }
            else if (random < 3.0/7) {
               upcoming[i] = new JTetris(3);
            }
            else if (random < 4.0/7){
               upcoming[i] = new LTetris(3);
            }
            else if (random < 5.0/7){
               upcoming[i] = new STetris(3);
            }
            else if (random < 6.0/7) {
               upcoming[i] = new ZTetris(3);
            }
            else {
               upcoming[i] = new TTetris(4);
            }
         }

      }
   }
   private class Key extends KeyAdapter {
      public void keyPressed(KeyEvent e) {
         if (countdown == 0 && scoreboard.getPause() == false) {
            if(e.getKeyCode() == controls[0]) {
               block.moveLeft(colors);
               draw();
            //left = true;
            }
            if(e.getKeyCode() == controls[1]) {
               block.moveRight(colors);
               draw();
            //right = true;
            }
            if(e.getKeyCode() == controls[2]) {
               if (down == false) {
                  t.setDelay(timedelay/5);
                  t.stop();
                  t.start();
                  down = true;
               }
            }
            if(e.getKeyCode() == controls[3]) {
               block.rotate(colors);
               draw();
            }
            if(e.getKeyCode() == controls[4]) {
               int moves = block.hardDown(colors);
               scoreboard.add(moves * 2);
               draw();
               checkRows();
               if(checkLose()) {
                  scoreboard.lose();
                  t.stop();
                  countdown = 3;
               }

               held = false;
               newBlock();
            }
            if(e.getKeyCode() == controls[5]) {
               hold();
            }
         }
         if(e.getKeyCode() == controls[6]) {
             pause();
             scoreboard.pause();
          }
         //System.out.println(e.getKeyCode());
      }
      public void keyReleased(KeyEvent e) {
         if(countdown == 0 && scoreboard.getPause() == false) {
            if(e.getKeyCode() == controls[2]) {
               t.setDelay(timedelay);
               t.stop();
               t.start();
               down = false;
            }
         }
      }

   }


   private static void draw() {
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[0].length; j++) {
            if(colors[i][j] == 0) {
               grid[i][j].setBackground(Color.LIGHT_GRAY);
            }
            else if(colors[i][j] == 1) {
               grid[i][j].setBackground(Color.CYAN);
            }
            else if(colors[i][j] == 2) {
               grid[i][j].setBackground(Color.YELLOW);
            }
            else if(colors[i][j] == 3) {
               grid[i][j].setBackground(Color.BLUE);
            }
            else if(colors[i][j] == 4) {
               grid[i][j].setBackground(Color.ORANGE);
            }
            else if(colors[i][j] == 5) {
               grid[i][j].setBackground(Color.GREEN);
            }
            else if(colors[i][j] == 6) {
               grid[i][j].setBackground(Color.RED);
            }
            else if(colors[i][j] == 7) {
               grid[i][j].setBackground(new Color(192, 0, 255));
            }
         }
      }
   }
   private static void newBlock() {
      block = upcoming[0];
      for (int i = 0; i < upcoming.length - 1; i++) {
         upcoming[i] = upcoming[i+1];
      }
      double random = Math.random();
      if (random < 1.0/7) {
         upcoming[upcoming.length - 1] = new StraightTetris(3);
      }
      else if (random < 2.0/7){
         upcoming[upcoming.length - 1] = new SquareTetris(4);
      }
      else if (random < 3.0/7){
         upcoming[upcoming.length - 1] = new JTetris(3);
      }
      else if (random < 4.0/7){
         upcoming[upcoming.length - 1] = new LTetris(3);
      }
      else if (random < 5.0/7) {
         upcoming[upcoming.length - 1] = new STetris(3);
      }
      else if (random < 6.0/7) {
         upcoming[upcoming.length - 1] = new ZTetris(3);
      }
      else {
         upcoming[upcoming.length - 1] = new TTetris(4);
      }

      scoreboard.displayBlocks(upcoming);
   }
   private static void checkRows() {
      int mult = 0;
      for(int i = grid.length - 1; i >= 0; i--) {
         for(int j = 0; j < grid[0].length; j++) {
            if (colors[i][j] == 0) {
               break;
            }
            if (j == grid[0].length-1) {
               clearRow(i);
               mult++;
               rowscleared++;
            }
         }
      }
      if (mult != 0) {
         scoreboard.add((int)(100 * Math.pow(2, mult - 1)));
      }
      if(rowscleared > 5 * level) {
         level++;
         rowscleared = 0;
         if(timedelay - 50 >= 50) {
            timedelay -= 50;
            t.setDelay(timedelay);
         }
         scoreboard.levelUp();
      }
      draw();
      //System.out.println(rowscleared);
   }
   private static void hold() {
      if(firsthold) {
         firsthold = false;
         if (block instanceof StraightTetris) {
            heldblock = new StraightTetris(3);
         }
         else if (block instanceof SquareTetris) {
            heldblock = new SquareTetris(4);
         }
         else if (block instanceof JTetris) {
            heldblock = new JTetris(3);
         }
         else if (block instanceof LTetris) {
            heldblock = new LTetris(3);
         }
         else if (block instanceof STetris) {
            heldblock = new STetris(3);
         }
         else if (block instanceof ZTetris) {
            heldblock = new ZTetris(3);
         }
         else if (block instanceof TTetris) {
            heldblock = new TTetris(4);
         }
         block.disappear(colors);
         newBlock();
      }
      else {
         if(held == false) {
            held = true;
            TetrisBlock temp = heldblock;
            if (block instanceof StraightTetris) {
               heldblock = new StraightTetris(3);
            }
            else if (block instanceof SquareTetris) {
               heldblock = new SquareTetris(4);
            }
            else if (block instanceof JTetris) {
               heldblock = new JTetris(3);
            }
            else if (block instanceof LTetris) {
               heldblock = new LTetris(3);
            }
            else if (block instanceof STetris) {
               heldblock = new STetris(3);
            }
            else if (block instanceof ZTetris) {
               heldblock = new ZTetris(3);
            }
            else if (block instanceof TTetris) {
               heldblock = new TTetris(4);
            }
            block.disappear(colors);
            block = temp;
         }
      }
      scoreboard.hold(heldblock);
      draw();
   }
   private static void clearRow(int row) {
      for(int i = row; i < grid.length - 1; i++) {
         for(int j = 0; j < grid[0].length; j++) {
            colors[i][j] = colors[i+1][j];
         }
      }
      for(int j = 0; j < grid[0].length; j++) {
         colors[grid.length - 1][j] = 0;
      }
   }
   private static boolean checkLose() {
      for(int i = grid.length; i < colors.length; i++) {
         for(int j = 0; j < colors[0].length; j++) {
            if(colors[i][j] != 0) {
               return true;
            }
         }
      }
      return false;
   }
}
