package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

import ua.com.hedgehogsoft.Labels;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public abstract class AbstractListener implements ActionListener, Labels
{
   protected JLabel wordLabel = null;
   protected Map<String, String> dictionary = null;
   protected ChangeWordsTaskState state = null;
   protected Timer timer = null;
   protected ChangeWordsTask task = null;

   public AbstractListener(JLabel wordLabel, Map<String, String> dictionary, ChangeWordsTaskState state)
   {
      this.wordLabel = wordLabel;
      this.dictionary = dictionary;
      this.state = state;
   }

   public AbstractListener(JLabel wordLabel,
                           Map<String, String> dictionary,
                           ChangeWordsTaskState state,
                           Timer timer,
                           ChangeWordsTask task)
   {
      this.wordLabel = wordLabel;
      this.dictionary = dictionary;
      this.state = state;
      this.timer = timer;
      this.task = task;
   }

   public Timer getTimer()
   {
      return timer;
   }

   public TimerTask getTask()
   {
      return task;
   }
}
