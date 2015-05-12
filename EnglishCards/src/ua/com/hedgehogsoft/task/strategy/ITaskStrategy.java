package ua.com.hedgehogsoft.task.strategy;

import ua.com.hedgehogsoft.task.ChangeWordsTaskState;
import ua.com.hedgehogsoft.task.config.TaskConfig;

public interface ITaskStrategy
{
   void execute();

   TaskConfig getTaskConfig();

   ChangeWordsTaskState getState();
}
