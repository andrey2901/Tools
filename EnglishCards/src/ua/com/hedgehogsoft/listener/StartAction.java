package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.util.Timer;

import ua.com.hedgehogsoft.button.StartButton;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.view.MainFrame;

public class StartAction extends AbstractListener
{
   public StartAction(MainFrame mainFrame)
   {
      this(mainFrame, null);
   }

   public StartAction(MainFrame mainFrame, ChangeWordsTask task)
   {
      super(mainFrame, task);

      if (task == null)
      {
         mainFrame.getBlockLabel().setText(defualtBlockLabelText);

         mainFrame.getWordLabel().setText("");

         mainFrame.getPrgBar().setValue(0);
      }
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      StartButton startButton = (StartButton) e.getSource();

      ChangeWordsTaskSettings settings = new ChangeWordsTaskSettings(mainFrame.getStopMessage(),
            mainFrame.getSettingComponents());

      if (task == null)
      {
         task = new ChangeWordsTask(mainFrame.getWordLabel(), mainFrame.getBlockLabel(), mainFrame.getPrgBar(),
               mainFrame.getDictionary(), settings, null);
      }
      else
      {
         task = new ChangeWordsTask(mainFrame.getWordLabel(), mainFrame.getBlockLabel(), mainFrame.getPrgBar(),
               mainFrame.getDictionary(), settings, task.getState());
      }

      startButton.setText(pauseButtonName);

      startButton.setTimer(new Timer());

      Timer timer = startButton.getTimer();

      startButton.removeActionListener(this);

      startButton.addActionListener(new PauseAction(mainFrame, task));

      mainFrame.getStartMessage().send();

      timer.schedule(task, 0, task.getTaskConfig().getTimePeriod() * 1000);
   }
}
