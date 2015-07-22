package ua.com.hedgehogsoft.task.strategy;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.model.Dictionary;
import ua.com.hedgehogsoft.model.Word;
import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public class TranslatedListTaskStrategy extends AbstractTaskStrategy
{

   public TranslatedListTaskStrategy(JLabel wordLabel,
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
         Word word = dictionary.getWords().get(counter);

         blockLabel.setText(word.getBlock().getName());

         if (translated)
         {
            wordLabel.setFont(getFontSize(word.getValue()));

            wordLabel.setText(word.getValue());

            prgBar.setValue((int) (progressBarStep * (counter + 0.5)));

            translated = false;
         }
         else
         {
            wordLabel.setFont(getFontSize(word.getTranslation()));

            wordLabel.setText(word.getTranslation());

            counter++;

            prgBar.setValue((int) (progressBarStep * counter));

            checkForFinishElement();

            translated = true;
         }
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
         Word word = dictionary.getWords().get(counter);

         blockLabel.setText(word.getBlock().getName());

         if (translated)
         {
            wordLabel.setFont(getFontSize(word.getTranslation()));

            wordLabel.setText(word.getTranslation());

            prgBar.setValue((int) (progressBarStep * (counter + 0.5)));

            translated = false;
         }
         else
         {
            wordLabel.setFont(getFontSize(word.getValue()));

            wordLabel.setText(word.getValue());

            counter++;

            prgBar.setValue((int) (progressBarStep * counter));

            checkForFinishElement();

            translated = true;
         }
      }
      else
      {
         processSinglePassTask();
      }
   }
}
