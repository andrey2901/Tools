package ua.com.hedgehogsoft.task.strategy;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.model.Dictionary;
import ua.com.hedgehogsoft.model.Word;
import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public class SimpleListTaskStrategy extends AbstractTaskStrategy
{
   public SimpleListTaskStrategy(JLabel wordLabel,
                                 JLabel blockLabel,
                                 JProgressBar prgBar,
                                 Dictionary dictionary,
                                 ChangeWordsTaskSettings settings,
                                 ChangeWordsTaskState state)
   {
      super(wordLabel, blockLabel, prgBar, dictionary, settings, state);
   }

   @Override
   protected void directTranslationDirectionTask()
   {
      if (counter < dictionary.getWords().size())
      {
         Word word = dictionary.getWords().get(counter++);

         wordLabel.setFont(getFontSize(word.getValue()));

         wordLabel.setText(word.getValue());

         blockLabel.setText(word.getBlock().getName());

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
      if (counter < dictionary.getWords().size())
      {
         Word word = dictionary.getWords().get(counter++);

         wordLabel.setFont(getFontSize(word.getTranslation()));

         wordLabel.setText(word.getTranslation());

         blockLabel.setText(word.getBlock().getName());

         prgBar.setValue((int) (progressBarStep * counter));

         checkForFinishElement();
      }
      else
      {
         processSinglePassTask();
      }
   }
}
