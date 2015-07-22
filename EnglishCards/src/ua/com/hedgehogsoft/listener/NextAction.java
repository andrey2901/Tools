package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.util.Timer;

import ua.com.hedgehogsoft.button.NextButton;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;
import ua.com.hedgehogsoft.view.MainFrame;

public class NextAction extends AbstractListener
{
   public NextAction(MainFrame mainFrame, ChangeWordsTaskState state)
   {
      super(mainFrame, state);
   }

   public NextAction(MainFrame mainFrame, ChangeWordsTaskState state, ChangeWordsTask task)
   {
      super(mainFrame, state, task);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      NextButton nextButton = (NextButton) e.getSource();

      System.out.println("Next");

      nextButton.getStartButton().getTimer().cancel();

      nextButton.getStartButton().setTimer(new Timer());

      ChangeWordsTaskSettings settings = new ChangeWordsTaskSettings(mainFrame.getStopMessage(),
            mainFrame.getSettingComponents());

      ChangeWordsTask task = new ChangeWordsTask(mainFrame.getWordLabel(),
                                                 mainFrame.getBlockLabel(),
                                                 mainFrame.getPrgBar(),
                                                 mainFrame.getDictionary(),
                                                 settings,
                                                 state);

      nextButton.getStartButton().getTimer().schedule(task, 0, task.getTaskConfig().getTimePeriod() * 1000);
   }
}
