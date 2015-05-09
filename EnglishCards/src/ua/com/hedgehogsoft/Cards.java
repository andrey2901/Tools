package ua.com.hedgehogsoft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Cards
{
   private Timer timer = null;
   private String startButtonName = "Start";
   private String stopButtonName = "Stop";
   private String pauseButtonName = "Pause";
   private String exitButtonName = "Exit";
   private String singlePassRadioButtonName = "Single";
   private String nonStopPassRadioButtonName = "Non-stop";
   private String passControlTitledBorderName = "Pass";
   private String directTranslationRadioButtonName = "Direct";
   private String reverseTranslationRadioButtonName = "Reverse";
   private String translationDirectionControlTitledBorderName = "Translation";
   private String simpleListConfigurationRadioButtonName = "Simple";
   private String translatedListConfigurationRadioButtonName = "With translation";
   private String doubleListConfigurationRadioButtonName = "Double view";
   private String listConfigurationControlTitledBorderName = "List Configuration";
   private JLabel wordLabel = null;
   private JButton startButton = null;
   private JButton stopButton = null;
   private JButton exitButton = null;
   private ActionListener startAction = new StartAction();
   private ActionListener stopAction = new StopAction();
   private ActionListener pauseAction = new PauseAction();
   private JFrame f = null;
   private ChangeWordsTask task = null;
   private ChangeWordsTaskState state = null;

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

      /*-------------------------------Pass Control Panel-------------------------------*/
      JPanel passControlPanel = new JPanel();

      passControlPanel.setLayout(new BoxLayout(passControlPanel, BoxLayout.Y_AXIS));

      JRadioButton singlePassRadioButton = new JRadioButton(singlePassRadioButtonName);

      singlePassRadioButton.setSelected(true);

      JRadioButton nonStopPassRadioButton = new JRadioButton(nonStopPassRadioButtonName);

      ButtonGroup passControlGroup = new ButtonGroup();

      passControlGroup.add(singlePassRadioButton);

      passControlGroup.add(nonStopPassRadioButton);

      passControlPanel.add(singlePassRadioButton);

      passControlPanel.add(nonStopPassRadioButton);

      passControlPanel.setBorder(BorderFactory.createTitledBorder(passControlTitledBorderName));
      /*----------------------------End Of Pass Control Panel----------------------------*/

      /*------------------------Translation Direction Control Panel------------------------*/
      JPanel translationDirectionControlPanel = new JPanel();

      translationDirectionControlPanel.setLayout(new BoxLayout(translationDirectionControlPanel, BoxLayout.Y_AXIS));

      JRadioButton directTranslationRadioButton = new JRadioButton(directTranslationRadioButtonName);

      directTranslationRadioButton.setSelected(true);

      JRadioButton reverseTranslationRadioButton = new JRadioButton(reverseTranslationRadioButtonName);

      ButtonGroup translationDirectionControlGroup = new ButtonGroup();

      translationDirectionControlGroup.add(directTranslationRadioButton);

      translationDirectionControlGroup.add(reverseTranslationRadioButton);

      translationDirectionControlPanel.add(directTranslationRadioButton);

      translationDirectionControlPanel.add(reverseTranslationRadioButton);

      translationDirectionControlPanel.setBorder(BorderFactory.createTitledBorder(translationDirectionControlTitledBorderName));
      /*---------------------End Of Translation Direction Control Panel---------------------*/

      /*------------------------List Configuration Control Panel------------------------*/

      JPanel listConfigurationControlPanel = new JPanel();

      listConfigurationControlPanel.setLayout(new BoxLayout(listConfigurationControlPanel, BoxLayout.Y_AXIS));

      JRadioButton simpleListConfigurationRadioButton = new JRadioButton(simpleListConfigurationRadioButtonName);

      simpleListConfigurationRadioButton.setSelected(true);

      JRadioButton translatedListConfigurationRadioButton = new JRadioButton(translatedListConfigurationRadioButtonName);

      JRadioButton doubleListConfigurationRadioButton = new JRadioButton(doubleListConfigurationRadioButtonName);

      ButtonGroup listConfigurationControlGroup = new ButtonGroup();

      listConfigurationControlGroup.add(simpleListConfigurationRadioButton);

      listConfigurationControlGroup.add(translatedListConfigurationRadioButton);

      listConfigurationControlGroup.add(doubleListConfigurationRadioButton);

      listConfigurationControlPanel.add(simpleListConfigurationRadioButton);

      listConfigurationControlPanel.add(translatedListConfigurationRadioButton);

      listConfigurationControlPanel.add(doubleListConfigurationRadioButton);

      listConfigurationControlPanel.setBorder(BorderFactory.createTitledBorder(listConfigurationControlTitledBorderName));

      /*---------------------End Of List Configuration Control Panel--------------------*/

      /*-------------------------------Settings Control Panel-------------------------------*/
      JPanel settingsPanel = new JPanel();

      settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

      settingsPanel.add(passControlPanel);

      settingsPanel.add(translationDirectionControlPanel);

      settingsPanel.add(listConfigurationControlPanel);

      settingsPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.black));
      /*----------------------------End Of Settings Control Panel----------------------------*/

      /*-------------------------------Button Control Panel-------------------------------*/
      JPanel buttonPanel = new JPanel();

      buttonPanel.setLayout(new BorderLayout());

      buttonPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.black));

      JPanel functionButtonPanel = new JPanel();

      functionButtonPanel.add(startButton, BorderLayout.WEST);

      functionButtonPanel.add(stopButton, BorderLayout.EAST);

      buttonPanel.add(functionButtonPanel, BorderLayout.NORTH);

      JPanel exitButtonPanel = new JPanel();

      exitButtonPanel.add(exitButton);

      buttonPanel.add(exitButtonPanel, BorderLayout.SOUTH);
      /*----------------------------End Of Button Control Panel----------------------------*/

      f.setLayout(new BorderLayout());

      f.add(wordLabel, BorderLayout.CENTER);

      f.add(settingsPanel, BorderLayout.EAST);

      f.add(buttonPanel, BorderLayout.SOUTH);

      f.pack();

      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      f.setSize(900, 500);

      f.setLocationRelativeTo(null);

      f.setVisible(true);
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

         if (state == null)
         {
            task = new ChangeWordsTask();
         }
         else
         {
            task = new ChangeWordsTask(state);
         }

         timer.schedule(task, 0, 1000);
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

         if (state != null)
         {
            state = null;
         }

         if (task != null)
         {
            task = null;
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

         state = task.getState();

         task = null;
      }
   }

   private class ExitAction implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         System.out.println(exitButtonName);

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

      ChangeWordsTask(ChangeWordsTaskState state)
      {
         dictionary = state.getDictionary();

         keys = state.getKeys();

         word = state.getWord();

         counter = state.getCounter();
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

      private ChangeWordsTaskState getState()
      {
         return new ChangeWordsTaskState(dictionary, keys, word, counter);
      }
   }

   private class ChangeWordsTaskState
   {
      Map<String, String> dictionary = null;
      List<String> keys = null;
      String word = null;
      int counter = 0;

      ChangeWordsTaskState(Map<String, String> dictionary, List<String> keys, String word, int counter)
      {
         this.dictionary = dictionary;
         this.keys = keys;
         this.word = word;
         this.counter = counter;
      }

      public Map<String, String> getDictionary()
      {
         return dictionary;
      }

      public List<String> getKeys()
      {
         return keys;
      }

      public String getWord()
      {
         return word;
      }

      public int getCounter()
      {
         return counter;
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