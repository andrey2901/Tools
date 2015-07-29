package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import ua.com.hedgehogsoft.button.StartButton;
import ua.com.hedgehogsoft.button.TranslateButton;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.view.MainFrame;

public class TranslateAction extends AbstractListener
{

   public TranslateAction(MainFrame mainFrame)
   {
      this(mainFrame, null);
   }

   public TranslateAction(MainFrame mainFrame, ChangeWordsTask task)
   {
      super(mainFrame, task);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      StartButton startButton = ((TranslateButton) e.getSource()).getStartButton();

      startButton.setText(startButtonName);

      if (startButton.getTimer() != null)
      {
         startButton.getTimer().cancel();

         startButton.setTimer(null);
      }

      task = ((AbstractListener) startButton.getActionListeners()[0]).getTask();

      if (mainFrame.getWordLabel().getText()
            .equals(mainFrame.getDictionary().getWords().get(task.getState().getCounter() - 1).getTranslation()))
      {
         mainFrame.getWordLabel().setText(
               mainFrame.getDictionary().getWords().get(task.getState().getCounter() - 1).getValue());
      }
      else
      {
         mainFrame.getWordLabel().setText(
               mainFrame.getDictionary().getWords().get(task.getState().getCounter() - 1).getTranslation());
      }

      startButton.addActionListener(new StartAction(mainFrame, task));
   }

}
