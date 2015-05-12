package ua.com.hedgehogsoft.task.strategy;

import java.util.Collections;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;
import ua.com.hedgehogsoft.task.config.enums.PassConfig;
import ua.com.hedgehogsoft.task.config.enums.ShuffleConfig;

public class DoubleListTaskStrategy extends AbstractTaskStrategy
{

   public DoubleListTaskStrategy(JLabel wordLabel,
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
      if (counter < keys.size())
      {
         word = keys.get(counter++);

         String translation = dictionary.get(word);

         wordLabel.setFont(getFontSize(word + "/n" + translation));

         wordLabel.setText("<html><p style=\"line-height: 400%;text-align: center;font-size:100\">" + word + "<br>"
               + translation + "</p></html>");

         prgBar.setValue((int) (progressBarStep * counter));
      }
      else
      {
         postprocess();
      }
   }

   private void reverseTranslationDirectionTask()
   {
      if (counter < keys.size())
      {
         word = keys.get(counter++);

         String translation = dictionary.get(word);

         wordLabel.setFont(getFontSize(translation + "/n" + word));

         wordLabel.setText("<html><p style=\"line-height: 400%;text-align: center;font-size:100\">" + translation
               + "<br>" + word + "</p></html>");

         prgBar.setValue((int) (progressBarStep * counter));
      }
      else
      {
         postprocess();
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
