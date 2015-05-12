package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import ua.com.hedgehogsoft.Cards;
import ua.com.hedgehogsoft.StartButton;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public class PauseAction extends AbstractListener
{

   public PauseAction(Cards cards, ChangeWordsTaskState state)
   {
      super(cards, state);
   }

   public PauseAction(Cards cards, ChangeWordsTaskState state, ChangeWordsTask task)
   {
      super(cards, state, task);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      StartButton startButton = (StartButton) e.getSource();

      System.out.println(pauseButtonName);

      startButton.setText(startButtonName);

      startButton.removeActionListener(this);

      startButton.getTimer().cancel();

      startButton.setTimer(null);

      startButton.addActionListener(new StartAction(cards, task.getState()));
   }

}
