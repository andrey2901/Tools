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
      if (state.getCounter() < dictionary.getWords().size())
      {
         Word word = dictionary.getWords().get(state.getCounter());

         blockLabel.setText(word.getBlock().getName());

         if (state.isTranslated())
         {
            wordLabel.setFont(getFontSize(word.getValue()));

            wordLabel.setText(word.getValue());

            prgBar.setValue((int) (progressBarStep * (state.getCounter() + 0.5)));

            state.setTranslated(false);
         }
         else
         {
            wordLabel.setFont(getFontSize(word.getTranslation()));

            wordLabel.setText(word.getTranslation());

            state.setCounter(state.getCounter() + 1);

            prgBar.setValue((int) (progressBarStep * state.getCounter()));

            checkForFinishElement();

            state.setTranslated(true);
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
      if (state.getCounter() < dictionary.getWords().size())
      {
         Word word = dictionary.getWords().get(state.getCounter());

         blockLabel.setText(word.getBlock().getName());

         if (state.isTranslated())
         {
            wordLabel.setFont(getFontSize(word.getTranslation()));

            wordLabel.setText(word.getTranslation());

            prgBar.setValue((int) (progressBarStep * (state.getCounter() + 0.5)));

            state.setTranslated(false);
         }
         else
         {
            wordLabel.setFont(getFontSize(word.getValue()));

            wordLabel.setText(word.getValue());

            state.setCounter(state.getCounter() + 1);

            prgBar.setValue((int) (progressBarStep * state.getCounter()));

            checkForFinishElement();

            state.setTranslated(true);
         }
      }
      else
      {
         processSinglePassTask();
      }
   }
}
