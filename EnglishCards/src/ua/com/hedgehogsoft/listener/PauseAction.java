package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;

import ua.com.hedgehogsoft.button.StartButton;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;
import ua.com.hedgehogsoft.view.MainFrame;

public class PauseAction extends AbstractListener
{

   public PauseAction(MainFrame mainFrame, ChangeWordsTaskState state)
   {
      super(mainFrame, state);
   }

   public PauseAction(MainFrame mainFrame, ChangeWordsTaskState state, ChangeWordsTask task)
   {
      super(mainFrame, state, task);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      StartButton startButton = (StartButton) e.getSource();

      startButton.setText(startButtonName);

      startButton.removeActionListener(this);

      startButton.getTimer().cancel();

      startButton.setTimer(null);

      startButton.addActionListener(new StartAction(mainFrame, task.getState()));
   }

}
