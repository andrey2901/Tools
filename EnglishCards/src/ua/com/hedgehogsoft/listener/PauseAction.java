package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public class PauseAction extends AbstractListener
{

   public PauseAction(JLabel wordLabel, JProgressBar prgBar, Map<String, String> dictionary, ChangeWordsTaskState state)
   {
      super(wordLabel, prgBar, dictionary, state);
   }

   public PauseAction(JLabel wordLabel,
                      JProgressBar prgBar,
                      Map<String, String> dictionary,
                      ChangeWordsTaskState state,
                      Timer timer,
                      ChangeWordsTask task)
   {
      super(wordLabel, prgBar, dictionary, state, timer, task);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      JButton pauseButton = (JButton) e.getSource();

      System.out.println(pauseButtonName);

      pauseButton.setText(startButtonName);

      pauseButton.removeActionListener(this);

      timer.cancel();

      pauseButton.addActionListener(new StartAction(wordLabel, prgBar, dictionary, task.getState()));
   }

}
