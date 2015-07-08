package ua.com.hedgehogsoft.task.config;

import ua.com.hedgehogsoft.task.StopTaskMessage;
import ua.com.hedgehogsoft.task.config.enums.ListConfig;
import ua.com.hedgehogsoft.task.config.enums.Mode;
import ua.com.hedgehogsoft.task.config.enums.PassConfig;
import ua.com.hedgehogsoft.task.config.enums.ShuffleConfig;
import ua.com.hedgehogsoft.task.config.enums.TranslationDirection;

public class TaskConfig
{
   private ListConfig listConfig;
   private PassConfig passConfig;
   private ShuffleConfig shuffleConfig;
   private TranslationDirection translationDirection;
   private Mode mode;
   private StopTaskMessage stopMessage = null;
   private int timePeriod = 0;

   public ListConfig getListConfig()
   {
      return listConfig;
   }

   public void setListConfig(ListConfig listConfig)
   {
      this.listConfig = listConfig;
   }

   public PassConfig getPassConfig()
   {
      return passConfig;
   }

   public void setPassConfig(PassConfig passConfig)
   {
      this.passConfig = passConfig;
   }

   public ShuffleConfig getShuffleConfig()
   {
      return shuffleConfig;
   }

   public void setShuffleConfig(ShuffleConfig shuffleConfig)
   {
      this.shuffleConfig = shuffleConfig;
   }

   public TranslationDirection getTranslationDirection()
   {
      return translationDirection;
   }

   public void setTranslationDirection(TranslationDirection translationDirection)
   {
      this.translationDirection = translationDirection;
   }

   public Mode getMode()
   {
      return mode;
   }

   public void setMode(Mode mode)
   {
      this.mode = mode;
   }

   public StopTaskMessage getStopMessage()
   {
      return stopMessage;
   }

   public void setStopMessage(StopTaskMessage stopMessage)
   {
      this.stopMessage = stopMessage;
   }

   public int getTimePeriod()
   {
      return timePeriod;
   }

   public void setTimePeriod(int timePeriod)
   {
      this.timePeriod = timePeriod;
   }
}
