package ua.com.hedgehogsoft.button;

import java.util.Observable;

import ua.com.hedgehogsoft.task.StartTaskMessage;
import ua.com.hedgehogsoft.task.StopTaskMessage;

public class ChooseDictionaryButton extends ObserverButton
{
   private static final long serialVersionUID = 1L;

   public ChooseDictionaryButton(String text)
   {
      super(text);
   }

   @Override
   public void update(Observable o, Object arg)
   {
      if (o instanceof StartTaskMessage)
      {
         this.setEnabled(false);
      }
      if (o instanceof StopTaskMessage)
      {
         this.setEnabled(true);
      }
   }
}
