package ua.com.hedgehogsoft.button;

import ua.com.hedgehogsoft.button.AbstractButton;

public class NextButton extends AbstractButton
{
   private static final long serialVersionUID = 1L;

   private StartButton startButton;

   public NextButton(String text, StartButton startButton)
   {
      super(text);

      this.startButton = startButton;
   }

   public StartButton getStartButton()
   {
      return startButton;
   }
}
