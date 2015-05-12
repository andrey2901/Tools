package ua.com.hedgehogsoft.task.strategy;

import java.util.Collections;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;
import ua.com.hedgehogsoft.task.config.enums.PassConfig;
import ua.com.hedgehogsoft.task.config.enums.ShuffleConfig;

public class TranslatedListTaskStrategy extends AbstractTaskStrategy
{

   public TranslatedListTaskStrategy(JLabel wordLabel,
                                     JProgressBar prgBar,
                                     Map<String, String> dictionary,
                                     ChangeWordsTaskSettings settings,
                                     ChangeWordsTaskState state)
   {
      super(wordLabel, prgBar, dictionary, settings, state);
   }

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

   private void directTranslationDirectionTask()
   {
      if (word == null)
      {
         if (counter < keys.size())
         {
            word = keys.get(counter++);

            wordLabel.setFont(getFontSize(word));

            wordLabel.setText(word);

            prgBar.setValue((int) (progressBarStep * (counter - 0.5)));
         }
         else
         {
            postprocess();
         }
      }
      else
      {
         String translation = dictionary.get(word);

         wordLabel.setFont(getFontSize(translation));

         wordLabel.setText(translation);

         word = null;

         prgBar.setValue((int) (progressBarStep * counter));
      }
   }

   private void reverseTranslationDirectionTask()
   {
      if (word == null)
      {
         if (counter < keys.size())
         {
            word = keys.get(counter++);

            String translation = dictionary.get(word);

            wordLabel.setFont(getFontSize(translation));

            wordLabel.setText(translation);

            prgBar.setValue((int) (progressBarStep * (counter - 0.5)));
         }
         else
         {
            postprocess();
         }
      }
      else
      {
         wordLabel.setFont(getFontSize(word));

         wordLabel.setText(word);

         word = null;

         prgBar.setValue((int) (progressBarStep * counter));
      }
   }

   private void postprocess()
   {
      prgBar.setValue(100);

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
