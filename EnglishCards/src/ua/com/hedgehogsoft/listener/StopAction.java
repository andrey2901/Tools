package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;

public class StopAction extends AbstractListener
{
   private JButton startButton = null;

   public StopAction(JLabel wordLabel, Map<String, String> dictionary, JButton startButton)
   {
      super(wordLabel, dictionary, null);

      this.startButton = startButton;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      System.out.println(stopButtonName);

      AbstractListener start_pauseListener = null;

      ActionListener[] listeners = startButton.getActionListeners();

      for (int i = 0; i < listeners.length; i++)
      {
         if (listeners[i] instanceof AbstractListener)
         {
            start_pauseListener = (AbstractListener) listeners[i];
         }
      }

      timer = start_pauseListener.getTimer();

      if (timer != null)
      {
         timer.cancel();

         timer = null;
      }

      startButton.setText(startButtonName);

      startButton.removeActionListener(start_pauseListener);

      startButton.addActionListener(new StartAction(wordLabel, dictionary, state));

      wordLabel.setText("");
   }
}
