package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import ua.com.hedgehogsoft.button.StartButton;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.view.MainFrame;

public class PauseAction extends AbstractListener
{

   public PauseAction(MainFrame mainFrame)
   {
      super(mainFrame);
   }

   public PauseAction(MainFrame mainFrame, ChangeWordsTask task)
   {
      super(mainFrame, task);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      StartButton startButton = (StartButton) e.getSource();

      startButton.setText(startButtonName);

      startButton.getTimer().cancel();

      startButton.setTimer(null);

      startButton.removeActionListener(this);

      startButton.addActionListener(new StartAction(mainFrame, task));
   }

}
