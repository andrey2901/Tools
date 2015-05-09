package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Timer;
import javax.swing.JButton;
import javax.swing.JLabel;

import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public class PauseAction extends AbstractListener
{

   public PauseAction(JLabel wordLabel, Map<String, String> dictionary, ChangeWordsTaskState state)
   {
      super(wordLabel, dictionary, state);
   }

   public PauseAction(JLabel wordLabel,
                      Map<String, String> dictionary,
                      ChangeWordsTaskState state,
                      Timer timer,
                      ChangeWordsTask task)
   {
      super(wordLabel, dictionary, state, timer, task);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      JButton pauseButton = (JButton) e.getSource();

      System.out.println(pauseButtonName);

      pauseButton.setText(startButtonName);

      pauseButton.removeActionListener(this);

      timer.cancel();

      pauseButton.addActionListener(new StartAction(wordLabel, dictionary, task.getState()));
   }

}
