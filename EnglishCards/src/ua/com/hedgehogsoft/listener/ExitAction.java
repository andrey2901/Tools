package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;

public class ExitAction extends AbstractListener
{
   public ExitAction()
   {
      super(null, null);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      System.exit(0);
   }
}
