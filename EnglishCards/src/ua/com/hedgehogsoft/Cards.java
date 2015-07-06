package ua.com.hedgehogsoft;

import java.awt.EventQueue;
import ua.com.hedgehogsoft.view.MainFrame;

public class Cards implements Labels
{
   public Cards()
   {
      new MainFrame(mainFrameTitle);
   }

   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            new Cards();
         }
      });
   }
}