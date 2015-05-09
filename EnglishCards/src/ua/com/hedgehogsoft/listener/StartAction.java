package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;

import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public class StartAction extends AbstractListener
{
   public StartAction(JLabel wordLabel, Map<String, String> dictionary, ChangeWordsTaskState state)
   {
      super(wordLabel, dictionary, state);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      JButton startButton = (JButton) e.getSource();

      System.out.println(startButtonName);

      startButton.removeActionListener(this);

      startButton.setText(pauseButtonName);

      Timer timer = new Timer();

      ChangeWordsTask task = new ChangeWordsTask(wordLabel, dictionary, state);

      startButton.addActionListener(new PauseAction(wordLabel, dictionary, state, timer, task));

      timer.schedule(task, 0, 1000);
   }
}
