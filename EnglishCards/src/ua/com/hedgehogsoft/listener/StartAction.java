package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.util.Timer;

import ua.com.hedgehogsoft.button.StartButton;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;
import ua.com.hedgehogsoft.view.MainFrame;

public class StartAction extends AbstractListener
{
   public StartAction(MainFrame mainFrame, ChangeWordsTaskState state)
   {
      super(mainFrame, state);

      if (state == null)
      {
         mainFrame.getWordLabel().setText("");

         mainFrame.getPrgBar().setValue(0);
      }
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      StartButton startButton = (StartButton) e.getSource();

      System.out.println(startButtonName);

      startButton.removeActionListener(this);

      startButton.setText(pauseButtonName);

      startButton.setTimer(new Timer());

      Timer timer = startButton.getTimer();

      ChangeWordsTaskSettings settings = new ChangeWordsTaskSettings(mainFrame.getStopMessage(),
            mainFrame.getSettingComponents());

      ChangeWordsTask task = new ChangeWordsTask(mainFrame.getWordLabel(), mainFrame.getPrgBar(), mainFrame.getWords(),
            settings, state);

      startButton.addActionListener(new PauseAction(mainFrame, state, task));

      timer.schedule(task, 0, task.getTaskConfig().getTimePeriod() * 1000);
   }
}
