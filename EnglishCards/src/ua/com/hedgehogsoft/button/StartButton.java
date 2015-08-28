package ua.com.hedgehogsoft.button;

import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Timer;

import ua.com.hedgehogsoft.listener.AbstractListener;
import ua.com.hedgehogsoft.listener.StartAction;
import ua.com.hedgehogsoft.props.Properties;
import ua.com.hedgehogsoft.view.MainFrame;

public class StartButton extends ObserverButton
{
   private static final long serialVersionUID = 1L;
   private Timer timer = null;
   private MainFrame mainFrame = null;

   public StartButton(String text, MainFrame mainFrame)
   {
      super(text);

      this.mainFrame = mainFrame;
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

      this.setText(Properties.getProperty("cards.button.start.text"));

      this.addActionListener(new StartAction(mainFrame, null));
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
