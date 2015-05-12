package ua.com.hedgehogsoft.task;

import java.awt.Font;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.Labels;
import ua.com.hedgehogsoft.task.config.TaskConfig;
import ua.com.hedgehogsoft.task.config.enums.PassConfig;
import ua.com.hedgehogsoft.task.config.enums.ShuffleConfig;

public class ChangeWordsTask extends TimerTask implements Labels
{
   private JLabel wordLabel = null;
   private JProgressBar prgBar = null;
   private Map<String, String> dictionary = null;
   private List<String> keys = null;
   private String word = null;
   private int counter = 0;
   private double progressBarStep = 0.0;
   private TaskConfig taskConfig = null;

   public ChangeWordsTask(JLabel wordLabel,
                          JProgressBar prgBar,
                          Map<String, String> dictionary,
                          ChangeWordsTaskSettings settings,
                          ChangeWordsTaskState state)
   {
      this.wordLabel = wordLabel;

      this.prgBar = prgBar;

      this.dictionary = dictionary;

      if (state == null)
      {
         keys = new ArrayList<String>(this.dictionary.keySet());

         Collections.shuffle(keys);

         progressBarStep = new BigDecimal(100 / keys.size()).setScale(2, RoundingMode.HALF_UP).doubleValue();
      }
      else
      {
         keys = state.getKeys();

         word = state.getWord();

         counter = state.getCounter();

         progressBarStep = state.getProgressBarStep();
      }
      this.taskConfig = new ChangeWordsTaskSettingsResolver(settings).getTaskConfig();
   }

   @Override
   public void run()
   {
      simpleListTask();
   }

   public ChangeWordsTaskState getState()
   {
      return new ChangeWordsTaskState(keys, word, counter, progressBarStep);
   }

   public TaskConfig getTaskConfig()
   {
      return taskConfig;
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

   private void simpleListTask()
   {
      switch (taskConfig.getTranslationDirection())
      {
         case DIRECT:
         {
            directTranslationDirectionTask();
            break;
         }

         case REVERSE:
         {
            reverseTranslationDirectionTask();
            break;
         }
      }
   }

   private void directTranslationDirectionTask()
   {
      if (counter < keys.size())
      {
         word = keys.get(counter++);

         wordLabel.setFont(getFontSize(word));

         wordLabel.setText(word);

         prgBar.setValue((int) (progressBarStep * counter));
      }
      else
      {
         if (taskConfig.getPassConfig() == PassConfig.NON_STOP)
         {
            counter = 0;

            if (taskConfig.getShuffleConfig() == ShuffleConfig.EACH_PASS)
            {
               Collections.shuffle(keys);
            }
         }
         if (taskConfig.getPassConfig() == PassConfig.SINGLE)
         {
            taskConfig.getStopMessage().send();
         }
      }
   }

   private void reverseTranslationDirectionTask()
   {
      if (counter < keys.size())
      {
         word = keys.get(counter++);

         String translation = dictionary.get(word);

         wordLabel.setFont(getFontSize(translation));

         wordLabel.setText(translation);

         prgBar.setValue((int) (progressBarStep * counter));
      }
      else
      {
         counter = 0;

         if (taskConfig.getShuffleConfig() == ShuffleConfig.EACH_PASS)
         {
            Collections.shuffle(keys);
         }
      }
   }
}