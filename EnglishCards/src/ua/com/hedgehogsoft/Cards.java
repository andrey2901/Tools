package ua.com.hedgehogsoft;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Cards
{
   private Timer timer = null;
   private String startButtonName = "Start";
   private String stopButtonName = "Stop";
   private String pauseButtonName = "Pause";
   private String exitButtonName = "Exit";
   private JLabel wordLabel = null;
   private JButton startButton = null;
   private JButton stopButton = null;
   private JButton exitButton = null;
   private ActionListener startAction = new StartAction();
   private ActionListener stopAction = new StopAction();
   private ActionListener pauseAction = new PauseAction();
   private JFrame f = null;

   public Cards()
   {
      f = new JFrame("Cards");

      wordLabel = new JLabel("", JLabel.CENTER);

      startButton = new JButton(startButtonName);

      startButton.addActionListener(startAction);

      stopButton = new JButton(stopButtonName);

      stopButton.addActionListener(stopAction);

      exitButton = new JButton(exitButtonName);

      exitButton.addActionListener(new ExitAction());

      f.add(wordLabel, BorderLayout.CENTER);

      Panel buttonPanel = new Panel();

      Panel functionButtonPanel = new Panel();

      functionButtonPanel.add(startButton, BorderLayout.WEST);

      functionButtonPanel.add(stopButton, BorderLayout.EAST);

      buttonPanel.add(functionButtonPanel, BorderLayout.NORTH);

      Panel exitButtonPanel = new Panel();

      exitButtonPanel.add(exitButton);

      buttonPanel.add(exitButtonPanel, BorderLayout.SOUTH);

      f.add(buttonPanel, BorderLayout.SOUTH);

      f.pack();

      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      f.setSize(900, 500);

      f.setVisible(true);

      f.setLocationRelativeTo(null);
   }

   private class StartAction implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         System.out.println(startButtonName);

         startButton.setText(pauseButtonName);

         startButton.removeActionListener(startAction);

         startButton.addActionListener(pauseAction);

         timer = new Timer();

         timer.schedule(new ChangeWordsTask(), 0, 1000);
      }
   }

   private class StopAction implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         System.out.println(stopButtonName);

         if (timer != null)
         {
            timer.cancel();

            timer = null;
         }

         if (startButton.getText().equals(pauseButtonName))
         {
            startButton.setText(startButtonName);

            startButton.removeActionListener(pauseAction);

            startButton.addActionListener(startAction);
         }

         wordLabel.setText("");
      }
   }

   private class PauseAction implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         System.out.println(pauseButtonName);

         startButton.setText(startButtonName);

         startButton.removeActionListener(pauseAction);

         startButton.addActionListener(startAction);

         timer.cancel();

         timer = null;
      }
   }

   private class ExitAction implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         System.out.println(exitButtonName);

         timer.cancel();

         System.exit(0);
      }
   }

   private class ChangeWordsTask extends TimerTask
   {
      Map<String, String> dictionary = null;
      List<String> keys = null;
      String word = null;
      int counter = 0;

      ChangeWordsTask()
      {
         dictionary = new ProviderManager().getProvider().getWords();

         keys = new ArrayList<String>(dictionary.keySet());

         Collections.shuffle(keys);
      }

      @Override
      public void run()
      {
         if (word == null)
         {
            if (counter < keys.size())
            {
               word = keys.get(counter);

               wordLabel.setFont(getFontSize(word));

               wordLabel.setText(word);

               word = keys.get(counter++);
            }
            else
            {
               // counter = 0;
               if (timer != null)
               {
                  timer.cancel();
                  timer = null;
               }
               if (startButton.getText().equals(pauseButtonName))
               {
                  startButton.setText(startButtonName);

                  startButton.removeActionListener(pauseAction);

                  startButton.addActionListener(startAction);
               }
               wordLabel.setText("");
            }
         }
         else
         {
            String translation = dictionary.get(word);

            wordLabel.setFont(getFontSize(translation));

            wordLabel.setText(translation);

            word = null;
         }
      }
   }

   private Font getFontSize(String word)
   {
      int fontSize = 5;

      if (word.length() > 9)
      {
         fontSize = word.length();
      }

      return new Font("Serif", Font.BOLD, f.getSize().width / fontSize);
   }

   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            @SuppressWarnings("unused")
            final Cards clock = new Cards();
         }
      });
   }
}