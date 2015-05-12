package ua.com.hedgehogsoft;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import javax.swing.JButton;

import ua.com.hedgehogsoft.listener.AbstractListener;
import ua.com.hedgehogsoft.listener.StartAction;

public class StartButton extends JButton implements Observer, Labels
{
   private static final long serialVersionUID = 2553030150325461637L;
   private Timer timer = null;
   private Cards cards = null;

   public StartButton(String text, Cards cards)
   {
      super(text);

      this.cards = cards;
   }

   @Override
   public void update(Observable o, Object arg)
   {
      ActionListener[] listeners = this.getActionListeners();

      for (int i = 0; i < listeners.length; i++)
      {
         if (listeners[i] instanceof AbstractListener)
         {
            this.removeActionListener(listeners[i]);
         }
      }

      if (timer != null)
      {
         timer.cancel();

         timer = null;
      }

      this.setText(startButtonName);

      this.addActionListener(new StartAction(cards, null));
   }

   public Timer getTimer()
   {
      return timer;
   }

   public void setTimer(Timer timer)
   {
      this.timer = timer;
   }
}
