package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public class StartAction extends AbstractListener
{
   public StartAction(JLabel wordLabel, JProgressBar prgBar, Map<String, String> dictionary, ChangeWordsTaskState state)
   {
      super(wordLabel, prgBar, dictionary, state);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      JButton startButton = (JButton) e.getSource();

      System.out.println(startButtonName);

      startButton.removeActionListener(this);

      startButton.setText(pauseButtonName);

      Timer timer = new Timer();

      ChangeWordsTask task = new ChangeWordsTask(wordLabel, prgBar, dictionary, state);

      startButton.addActionListener(new PauseAction(wordLabel, prgBar, dictionary, state, timer, task));

      timer.schedule(task, 0, 1000);
   }
}
