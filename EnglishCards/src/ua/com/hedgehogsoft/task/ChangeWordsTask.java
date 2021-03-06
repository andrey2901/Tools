package ua.com.hedgehogsoft.task;

import java.util.Map;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.Labels;
import ua.com.hedgehogsoft.task.config.TaskConfig;
import ua.com.hedgehogsoft.task.strategy.DoubleListTaskStrategy;
import ua.com.hedgehogsoft.task.strategy.SimpleListTaskStrategy;
import ua.com.hedgehogsoft.task.strategy.ITaskStrategy;
import ua.com.hedgehogsoft.task.strategy.TranslatedListTaskStrategy;

public class ChangeWordsTask extends TimerTask implements Labels
{
   private ITaskStrategy taskStrategy = null;

   public ChangeWordsTask(JLabel wordLabel,
                          JProgressBar prgBar,
                          Map<String, String> dictionary,
                          ChangeWordsTaskSettings settings,
                          ChangeWordsTaskState state)
   {
      TaskConfig taskConfig = new ChangeWordsTaskSettingsResolver(settings).getTaskConfig();
      switch (taskConfig.getListConfig())
      {
         case SIMPLE:
            taskStrategy = new SimpleListTaskStrategy(wordLabel, prgBar, dictionary, settings, state);
            break;

         case WITH_TRANSLATION:
            taskStrategy = new TranslatedListTaskStrategy(wordLabel, prgBar, dictionary, settings, state);
            break;

         case DOUBLE_VIEW:
            taskStrategy = new DoubleListTaskStrategy(wordLabel, prgBar, dictionary, settings, state);
            break;
      }

   }

   @Override
   public void run()
   {
      taskStrategy.execute();
   }

   public ChangeWordsTaskState getState()
   {
      return taskStrategy.getState();
   }

   public TaskConfig getTaskConfig()
   {
      return taskStrategy.getTaskConfig();
   }
}