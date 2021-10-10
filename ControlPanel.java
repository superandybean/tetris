import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class ControlPanel extends JPanel
{
   private static JTextField[] buttons;
   private static JButton[] editbuttons;
   private static int[] controlkeys;
   private static JLabel saved;
   private static JPanel buttonpanel;
   private int currentedit = 0;
   private boolean editing = false;
   public ControlPanel(ActionListener lis)
   {      
      controlkeys = new int[7];
      try {
         Scanner in = new Scanner(new File("controls.txt"));
         int i = 0;
         while(in.hasNextInt()) {
            controlkeys[i] = in.nextInt();
            i++;
         }
         in.close();
      }
      catch (Exception e) {
         JOptionPane.showMessageDialog(null, "Controls file not found! Using default controls");
         controlkeys[0] = 37;
         controlkeys[1] = 39;
         controlkeys[2] = 40;
         controlkeys[3] = 38;
         controlkeys[4] = 32;
         controlkeys[5] = 67;
         controlkeys[6] = 80;
      }
   
      setLayout(new BorderLayout());
      setBorder(new EmptyBorder(10, 10, 10, 10));
           
      JPanel labels = new JPanel();
      labels.setLayout(new BorderLayout());
      JLabel label = new JLabel("Press the edit button and press a key to edit controls.");
      JLabel label2 = new JLabel("Remember to save!");
      label.setFont(new Font("Sans-Serif", Font.BOLD, 20));
      label2.setFont(new Font("Sans-Serif", Font.BOLD, 30));
      label.setHorizontalAlignment(SwingConstants.CENTER);
      label2.setHorizontalAlignment(SwingConstants.CENTER);
      labels.add(label, BorderLayout.NORTH);
      labels.add(label2, BorderLayout.CENTER);
      add(labels, BorderLayout.NORTH);
   
      JPanel centerpanel = new JPanel();
      centerpanel.setLayout(new BorderLayout(10, 10));
      
      JPanel buttonlabels = new JPanel();
      buttonlabels.setLayout(new GridLayout(7, 1, 10, 10));
      JLabel[] buttonslabel = new JLabel[7];
      for (int i = 0; i < buttonslabel.length; i++) {
         buttonslabel[i] = new JLabel();
         buttonslabel[i].setHorizontalAlignment(SwingConstants.CENTER);
         buttonlabels.add(buttonslabel[i]);
      }
      buttonslabel[0].setText("LEFT");
      buttonslabel[1].setText("RIGHT");
      buttonslabel[2].setText("SOFT DROP");
      buttonslabel[3].setText("ROTATE");
      buttonslabel[4].setText("HARD DROP");
      buttonslabel[5].setText("HOLD");
      buttonslabel[6].setText("PAUSE");
      
      JPanel keys = new JPanel();
      keys.setLayout(new GridLayout(7, 1, 10, 10));
      
      buttons = new JTextField[7];
      for (int i = 0; i < buttons.length; i++) {
         buttons[i] = new JTextField();
         buttons[i].setEnabled(false);
         buttons[i].setHorizontalAlignment(SwingConstants.CENTER);
         buttons[i].setFont(new Font("Sans-Serif", Font.BOLD, 50));
         buttons[i].setDisabledTextColor(Color.BLACK);
         keys.add(buttons[i]);
      }
      
      updateText();
      checkRepeat();
      
      addKeyListener(new Key());
      setFocusable(true);
      
      JPanel edit = new JPanel();
      edit.setLayout(new GridLayout(7, 1, 10, 10));
      
      editbuttons = new JButton[7];
      for (int i = 0; i < editbuttons.length; i++) {
         editbuttons[i] = new JButton("EDIT");
         editbuttons[i].addActionListener(new EditListener(i));
         editbuttons[i].setFocusable(false);
         edit.add(editbuttons[i]);
      }
      
      centerpanel.add(buttonlabels, BorderLayout.WEST);
      centerpanel.add(keys, BorderLayout.CENTER);
      centerpanel.add(edit, BorderLayout.EAST);
      add(centerpanel, BorderLayout.CENTER);
      
      buttonpanel = new JPanel(); 
      add(buttonpanel, BorderLayout.SOUTH);
      buttonpanel.setLayout(new FlowLayout());
      buttonpanel.setOpaque(false);
      
      saved = new JLabel("Saved!");
        
      JButton start = new JButton("Save");
      start.addActionListener(new SaveListener());
      buttonpanel.add(start);
      
      JButton high = new JButton("Back");
      high.addActionListener(lis);
      buttonpanel.add(high);
   }

   private class EditListener implements ActionListener
   {  
      int myX;
      public EditListener(int i) {
         myX = i;
      }
      public void actionPerformed(ActionEvent e)
      {
         currentedit = myX;
         editing = true;
         
         for (int i = 0; i < buttons.length; i++) {
            buttons[i].setBackground(Color.WHITE);
         }
         
         buttons[currentedit].setBackground(Color.GRAY);
         buttonpanel.remove(saved);
         revalidate();
         repaint();
      }
   }

   private class SaveListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         PrintStream outfile = null;
         try {
            outfile = new PrintStream("controls.txt");
            for (int i = 0; i < controlkeys.length; i++) {
               outfile.println(controlkeys[i]);
            }
         }
         catch (Exception a) {
            JOptionPane.showMessageDialog(null, "File could not be created!");
         }
         buttonpanel.add(saved);
         revalidate();
         repaint();
      }
   }
   private static void updateText() {
      for (int i = 0; i < buttons.length; i++) { 
         if (controlkeys[i] == 20) {
            buttons[i].setText("CAPS LOCK");
         }
         else if (controlkeys[i] == 16) {
            buttons[i].setText("SHIFT");
         }
         else if (controlkeys[i] == 16) {
            buttons[i].setText("ENTER");
         }
         else if (controlkeys[i] == 37) {
            buttons[i].setText("LEFT");
         }
         else if (controlkeys[i] == 38) {
            buttons[i].setText("UP");
         }
         else if (controlkeys[i] == 39) {
            buttons[i].setText("RIGHT");
         }
         else if (controlkeys[i] == 40) {
            buttons[i].setText("DOWN");
         }
         else if (controlkeys[i] == 32) {
            buttons[i].setText("SPACE");
         }
         else if (controlkeys[i] >= 65 && controlkeys[i] <= 90) {
            buttons[i].setText("" + (char)(controlkeys[i]));
         }
      }
   }
   private static void checkRepeat() {
      for (int i = 0; i < controlkeys.length; i++) {
         buttons[i].setDisabledTextColor(Color.BLACK);
      }
      for (int i = 0; i < controlkeys.length; i++) {
         for (int j = i + 1; j < controlkeys.length; j++) {
            if (controlkeys[i] == controlkeys[j]) {
               buttons[i].setDisabledTextColor(Color.RED);
               buttons[j].setDisabledTextColor(Color.RED);
            }
         }
      }
   }
   private class Key extends KeyAdapter {
      public void keyPressed(KeyEvent e) {
         if (editing) {
            editing = false;
            
            for (int i = 0; i < buttons.length; i++) {
               buttons[currentedit].setBackground(Color.WHITE);
            }
            
            controlkeys[currentedit] = e.getKeyCode();
            updateText();
            checkRepeat();
         }
      }
   }
}








