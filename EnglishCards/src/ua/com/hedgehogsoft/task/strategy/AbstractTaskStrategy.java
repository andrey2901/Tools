package ua.com.hedgehogsoft.task.strategy;

import java.awt.Font;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.model.Dictionary;
import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.task.ChangeWordsTaskSettingsResolver;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;
import ua.com.hedgehogsoft.task.config.TaskConfig;
import ua.com.hedgehogsoft.task.config.enums.PassConfig;
import ua.com.hedgehogsoft.task.config.enums.ShuffleConfig;

public abstract class AbstractTaskStrategy implements ITaskStrategy
{
   protected JLabel wordLabel = null;
   protected JLabel blockLabel = null;
   protected JProgressBar prgBar = null;
   protected Dictionary dictionary = null;
   protected ChangeWordsTaskState state = null;
   protected double progressBarStep = 0.0;
   protected TaskConfig taskConfig = null;

   public AbstractTaskStrategy(JLabel wordLabel,
                               JLabel blockLabel,
                               JProgressBar prgBar,
                               Dictionary dictionary,
                               ChangeWordsTaskSettings settings,
                               ChangeWordsTaskState state)
   {
      this.wordLabel = wordLabel;

      this.blockLabel = blockLabel;

      this.prgBar = prgBar;

      this.dictionary = dictionary;

      this.taskConfig = new ChangeWordsTaskSettingsResolver(settings).getTaskConfig();

      this.state = state;

      if (state == null)
      {
         this.state = new ChangeWordsTaskState(0, true);

         if (taskConfig.getShuffleConfig() == ShuffleConfig.ONCE
               || taskConfig.getShuffleConfig() == ShuffleConfig.EACH_PASS)
         {
            dictionary.shuffle();
         }
      }

      progressBarStep = new BigDecimal(100 / dictionary.getWords().size()).setScale(2, RoundingMode.HALF_UP)
            .doubleValue();
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
         state.setCounter(0);

         if (taskConfig.getShuffleConfig() == ShuffleConfig.EACH_PASS)
         {
            dictionary.shuffle();
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
      if (state.getCounter() == dictionary.getWords().size())
      {
         prgBar.setValue(100);

         processNonStopPassTask();
      }
   }

   @Override
   public ChangeWordsTaskState getState()
   {
      return state;
   }

   @Override
   public TaskConfig getTaskConfig()
   {
      return taskConfig;
   }
}
