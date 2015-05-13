package ua.com.hedgehogsoft.task.strategy;

import java.awt.Font;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.task.ChangeWordsTaskSettingsResolver;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;
import ua.com.hedgehogsoft.task.config.TaskConfig;
import ua.com.hedgehogsoft.task.config.enums.PassConfig;
import ua.com.hedgehogsoft.task.config.enums.ShuffleConfig;

public abstract class AbstractTaskStrategy implements ITaskStrategy
{
   protected JLabel wordLabel = null;
   protected JProgressBar prgBar = null;
   protected Map<String, String> dictionary = null;
   protected List<String> keys = null;
   protected String word = null;
   protected int counter = 0;
   protected double progressBarStep = 0.0;
   protected TaskConfig taskConfig = null;

   public AbstractTaskStrategy(JLabel wordLabel,
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

   protected abstract void directTranslationDirectionTask();

   protected abstract void reverseTranslationDirectionTask();

   @Override
   public void execute()
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

   protected Font getFontSize(String word)
   {
      int fontSize = 5;

      if (word.length() > 9)
      {
         fontSize = word.length();
      }

      return new Font("Serif", Font.BOLD, wordLabel.getSize().width / fontSize);
   }

   protected void processNonStopPassTask()
   {
      if (taskConfig.getPassConfig() == PassConfig.NON_STOP)
      {
         counter = 0;

         if (taskConfig.getShuffleConfig() == ShuffleConfig.EACH_PASS)
         {
            Collections.shuffle(keys);
         }
      }
   }

   protected void processSinglePassTask()
   {
      if (taskConfig.getPassConfig() == PassConfig.SINGLE)
      {
         taskConfig.getStopMessage().send();
      }
   }

   protected void checkForFinishElement()
   {
      if (counter == keys.size())
      {
         prgBar.setValue(100);

         processNonStopPassTask();
      }
   }

   @Override
   public ChangeWordsTaskState getState()
   {
      return new ChangeWordsTaskState(keys, word, counter, progressBarStep);
   }

   @Override
   public TaskConfig getTaskConfig()
   {
      return taskConfig;
   }
}
