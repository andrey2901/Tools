package ua.com.hedgehogsoft.task.strategy;

import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

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

         // wordLabel.setFont(getFontSize(word));

         String translation = dictionary.get(word);

          wordLabel.setFont(getFontSize(translation));

         // wordLabel.setText(word + "/n" + translation);

         wordLabel.setText("<html><center>" + word + "</center><br><center>" + translation + "</center></html>");

         prgBar.setValue((int) (progressBarStep * counter));
      }
   }

   private void reverseTranslationDirectionTask()
   {
      // TODO Auto-generated method stub
   }
}
