package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.Labels;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public abstract class AbstractListener implements ActionListener, Labels
{
   protected JLabel wordLabel = null;
   protected JProgressBar prgBar = null;
   protected Map<String, String> dictionary = null;
   protected ChangeWordsTaskState state = null;
   protected Timer timer = null;
   protected ChangeWordsTask task = null;

   public AbstractListener(JLabel wordLabel,
                           JProgressBar prgBar,
                           Map<String, String> dictionary,
                           ChangeWordsTaskState state)
   {
      this.wordLabel = wordLabel;
      this.prgBar = prgBar;
      this.dictionary = dictionary;
      this.state = state;
   }

   public AbstractListener(JLabel wordLabel,
                           JProgressBar prgBar,
                           Map<String, String> dictionary,
                           ChangeWordsTaskState state,
                           Timer timer,
                           ChangeWordsTask task)
   {
      this.wordLabel = wordLabel;
      this.prgBar = prgBar;
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
