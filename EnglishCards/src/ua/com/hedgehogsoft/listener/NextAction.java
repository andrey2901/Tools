package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import ua.com.hedgehogsoft.button.NextButton;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.view.MainFrame;

public class NextAction extends AbstractListener
{
   public NextAction(MainFrame mainFrame)
   {
      this(mainFrame, null);
   }

   public NextAction(MainFrame mainFrame, ChangeWordsTask task)
   {
      super(mainFrame, task);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      NextButton nextButton = (NextButton) e.getSource();

      ActionListener[] listeners = nextButton.getStartButton().getActionListeners();

      AbstractListener listener = null;

      for (int i = 0; i < listeners.length; i++)
      {
         if (listeners[i] instanceof AbstractListener)
         {
            listener = (AbstractListener) listeners[i];

            break;
         }
      }

      task = listener.getTask();

      if (listener instanceof StartAction)
      {
         task.run();
      }

      if (listener instanceof PauseAction)
      {
         nextButton.getStartButton().getTimer().cancel();

         nextButton.getStartButton().setTimer(null);

         nextButton.getStartButton().setTimer(new Timer());

         ChangeWordsTaskSettings settings = new ChangeWordsTaskSettings(mainFrame.getStopMessage(),
               mainFrame.getSettingComponents());

         task = new ChangeWordsTask(mainFrame.getWordLabel(), mainFrame.getBlockLabel(), mainFrame.getPrgBar(),
               mainFrame.getDictionary(), settings, task.getState());

         nextButton.getStartButton().getTimer().schedule(task, 0, task.getTaskConfig().getTimePeriod() * 1000);
      }
   }
}
