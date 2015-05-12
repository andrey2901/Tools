package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.util.Timer;

import ua.com.hedgehogsoft.Cards;
import ua.com.hedgehogsoft.StartButton;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskSettings;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public class StartAction extends AbstractListener
{
   public StartAction(Cards cards, ChangeWordsTaskState state)
   {
      super(cards, state);

      cards.getWordLabel().setText("");

      cards.getPrgBar().setValue(0);
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

      ChangeWordsTaskSettings settings = new ChangeWordsTaskSettings(cards.getStopMessage(),
                                                                     cards.getSettingComponents());

      ChangeWordsTask task = new ChangeWordsTask(cards.getWordLabel(),
                                                 cards.getPrgBar(),
                                                 cards.getDictionary(),
                                                 settings,
                                                 state);

      startButton.addActionListener(new PauseAction(cards, state, task));

      timer.schedule(task, 0, task.getTaskConfig().getTimePeriod() * 1000);
   }
}
