package ua.com.hedgehogsoft;

import java.awt.EventQueue;

import ua.com.hedgehogsoft.props.Properties;
import ua.com.hedgehogsoft.view.MainFrame;

public class Cards
{
   public Cards()
   {
      new MainFrame(Properties.getProperty("cards.window.main.title"));
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