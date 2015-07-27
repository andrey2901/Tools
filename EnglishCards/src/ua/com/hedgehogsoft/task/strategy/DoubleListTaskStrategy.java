package ua.com.hedgehogsoft.task.strategy;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.model.Dictionary;
import ua.com.hedgehogsoft.model.Word;
import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public class DoubleListTaskStrategy extends AbstractTaskStrategy
{

   public DoubleListTaskStrategy(JLabel wordLabel,
                                 JLabel blockLabel,
                                 JProgressBar prgBar,
                                 Dictionary dictionary,
                                 ChangeWordsTaskSettings settings,
                                 ChangeWordsTaskState state)
   {
      super(wordLabel, blockLabel, prgBar, dictionary, settings, state);
   }

   protected void directTranslationDirectionTask()
   {
      if (state.getCounter() < dictionary.getWords().size())
      {
         Word word = dictionary.getWords().get(state.getCounter());

         state.setCounter(state.getCounter() + 1);

         wordLabel.setFont(getFontSize(word.getValue() + "/n" + word.getTranslation()));

         wordLabel.setText("<html><p style=\"line-height: 400%;text-align: center;font-size:100\">" + word.getValue()
               + "<br>" + word.getTranslation() + "</p></html>");

         blockLabel.setText(word.getBlock().getName());

         prgBar.setValue((int) (progressBarStep * state.getCounter()));

         checkForFinishElement();
      }
      else
      {
         processSinglePassTask();
      }
   }

   protected void reverseTranslationDirectionTask()
   {
      if (state.getCounter() < dictionary.getWords().size())
      {
         Word word = dictionary.getWords().get(state.getCounter());

         state.setCounter(state.getCounter() + 1);

         wordLabel.setFont(getFontSize(word.getTranslation() + "/n" + word.getValue()));

         wordLabel.setText("<html><p style=\"line-height: 400%;text-align: center;font-size:100\">"
               + word.getTranslation() + "<br>" + word.getValue() + "</p></html>");

         blockLabel.setText(word.getBlock().getName());

         prgBar.setValue((int) (progressBarStep * state.getCounter()));

         checkForFinishElement();
      }
      else
      {
         processSinglePassTask();
      }
   }
}
