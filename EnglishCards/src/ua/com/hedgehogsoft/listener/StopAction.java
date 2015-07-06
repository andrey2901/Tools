package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;

import ua.com.hedgehogsoft.view.MainFrame;

public class StopAction extends AbstractListener
{
   public StopAction(MainFrame mainFrame)
   {
      super(mainFrame, null);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      System.out.println(stopButtonName);

      mainFrame.getStopMessage().send();
   }
}
