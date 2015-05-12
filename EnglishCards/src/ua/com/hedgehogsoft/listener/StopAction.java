package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import ua.com.hedgehogsoft.Cards;

public class StopAction extends AbstractListener
{
   public StopAction(Cards cards)
   {
      super(cards, null);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      System.out.println(stopButtonName);

      cards.getStopMessage().send();
   }
}
