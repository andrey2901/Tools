package ua.com.hedgehogsoft.button;

import java.util.Observable;

import ua.com.hedgehogsoft.task.StartTaskMessage;
import ua.com.hedgehogsoft.task.StopTaskMessage;

public class TranslateButton extends ObserverButton
{
   private static final long serialVersionUID = 1L;

   private StartButton startButton = null;

   public TranslateButton(String text, StartButton startButton)
   {
      super(text);

      this.startButton = startButton;
   }

   public StartButton getStartButton()
   {
      return startButton;
   }

   @Override
   public void update(Observable o, Object arg)
   {
      if (o instanceof StartTaskMessage)
      {
         this.setEnabled(true);
      }
      if (o instanceof StopTaskMessage)
      {
         this.setEnabled(false);
      }
   }
}
