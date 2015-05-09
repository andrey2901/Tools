package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;

public class ExitAction extends AbstractListener
{

   public ExitAction()
   {
      super(null, null, null);
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      System.out.println(exitButtonName);

      System.exit(0);
   }

}
