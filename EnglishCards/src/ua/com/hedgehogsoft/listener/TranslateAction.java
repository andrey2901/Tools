package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;

import ua.com.hedgehogsoft.button.StartButton;
import ua.com.hedgehogsoft.button.TranslateButton;
import ua.com.hedgehogsoft.props.Properties;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.config.TaskConfig;
import ua.com.hedgehogsoft.task.config.enums.ListConfig;
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

      startButton.setText(Properties.getProperty("cards.button.start.name"));

      if (startButton.getTimer() != null)
      {
         startButton.getTimer().cancel();

         startButton.setTimer(null);
      }

      task = ((AbstractListener) startButton.getActionListeners()[0]).getTask();

      TaskConfig config = task.getTaskConfig();

      if (config.getListConfig() == ListConfig.SIMPLE)
      {
         translate(task.getState().getCounter() - 1);
      }
      else if (config.getListConfig() == ListConfig.WITH_TRANSLATION)
      {
         if (task.getState().isTranslated())
         {
            translate(task.getState().getCounter() - 1);
         }
         else
         {
            translate(task.getState().getCounter());
         }
      }

      startButton.addActionListener(new StartAction(mainFrame, task));
   }

   private void translate(int index)
   {
      if (mainFrame.getWordLabel().getText().equals(mainFrame.getDictionary().getWords().get(index).getTranslation()))
      {
         mainFrame.getWordLabel().setText(mainFrame.getDictionary().getWords().get(index).getValue());
      }
      else
      {
         mainFrame.getWordLabel().setText(mainFrame.getDictionary().getWords().get(index).getTranslation());
      }
   }
}
