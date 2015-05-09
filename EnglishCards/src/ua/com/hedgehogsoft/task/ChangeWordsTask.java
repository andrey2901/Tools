package ua.com.hedgehogsoft.task;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.swing.JLabel;

import ua.com.hedgehogsoft.Labels;

public class ChangeWordsTask extends TimerTask implements Labels
{
   private JLabel wordLabel = null;
   private Map<String, String> dictionary = null;
   private List<String> keys = null;
   private String word = null;
   private int counter = 0;

   public ChangeWordsTask(JLabel wordLabel, Map<String, String> dictionary, ChangeWordsTaskState state)
   {
      this.wordLabel = wordLabel;

      this.dictionary = dictionary;

      if (state == null)
      {
         keys = new ArrayList<String>(this.dictionary.keySet());

         Collections.shuffle(keys);
      }
      else
      {
         keys = state.getKeys();

         word = state.getWord();

         counter = state.getCounter();
      }

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
            counter = 0;
            /*
             * if (timer != null) { timer.cancel(); timer = null; } if
             * (startButton.getText().equals(pauseButtonName)) {
             * startButton.setText(startButtonName);
             * 
             * startButton.removeActionListener(pauseAction);
             * 
             * startButton.addActionListener(startAction); }
             * wordLabel.setText("");
             */
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

   public ChangeWordsTaskState getState()
   {
      return new ChangeWordsTaskState(keys, word, counter);
   }

   private Font getFontSize(String word)
   {
      int fontSize = 5;

      if (word.length() > 9)
      {
         fontSize = word.length();
      }

      return new Font("Serif", Font.BOLD, wordLabel.getSize().width / fontSize);
   }
}