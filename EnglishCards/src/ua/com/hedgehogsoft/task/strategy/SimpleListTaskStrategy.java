package ua.com.hedgehogsoft.task.strategy;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public class SimpleListTaskStrategy extends AbstractTaskStrategy
{
   public SimpleListTaskStrategy(JLabel wordLabel,
                                 JProgressBar prgBar,
                                 Map<String, String> dictionary,
                                 ChangeWordsTaskSettings settings,
                                 ChangeWordsTaskState state)
   {
      super(wordLabel, prgBar, dictionary, settings, state);
   }

   @Override
   protected void directTranslationDirectionTask()
   {
      if (counter < keys.size())
      {
         word = keys.get(counter++);

         wordLabel.setFont(getFontSize(word));

         wordLabel.setText(word);

         prgBar.setValue((int) (progressBarStep * counter));

         checkForFinishElement();
      }
      else
      {
         processSinglePassTask();
      }
   }

   @Override
   protected void reverseTranslationDirectionTask()
   {
      if (counter < keys.size())
      {
         word = keys.get(counter++);

         String translation = dictionary.get(word);

         wordLabel.setFont(getFontSize(translation));

         wordLabel.setText(translation);

         prgBar.setValue((int) (progressBarStep * counter));

         checkForFinishElement();
      }
      else
      {
         processSinglePassTask();
      }
   }
}
