package ua.com.hedgehogsoft.task;

import javax.swing.JComponent;

public class ChangeWordsTaskSettings
{
   private JComponent[] uiControlComponents = null;
   private StopTaskMessage stopMessage = null;

   public ChangeWordsTaskSettings(StopTaskMessage stopMessage, JComponent... uiControlComponents)
   {
      this.uiControlComponents = uiControlComponents;

      this.stopMessage = stopMessage;
   }

   public JComponent[] getUiControlComponents()
   {
      return uiControlComponents;
   }

   public StopTaskMessage getStopMessage()
   {
      return stopMessage;
   }
}
