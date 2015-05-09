package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class StopAction extends AbstractListener
{
   private JButton startButton = null;

   public StopAction(JLabel wordLabel, JProgressBar prgBar, Map<String, String> dictionary, JButton startButton)
   {
      super(wordLabel, prgBar, dictionary, null);

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

      startButton.addActionListener(new StartAction(wordLabel, prgBar, dictionary, state));

      wordLabel.setText("");

      prgBar.setValue(0);
   }
}
