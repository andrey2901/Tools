package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionListener;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.view.MainFrame;

/**
 * The class(interface) <code>AbstractListener</code>.
 * 
 * @author Andrey
 * @version 1.00 10 мая 2015 г.
 */
public abstract class AbstractListener implements ActionListener
{
   protected MainFrame mainFrame = null;
   protected ChangeWordsTask task = null;

   public AbstractListener(MainFrame mainFrame)
   {
      this(mainFrame, null);
   }

   public AbstractListener(MainFrame mainFrame, ChangeWordsTask task)
   {
      this.mainFrame = mainFrame;

      this.task = task;
   }

   public ChangeWordsTask getTask()
   {
      return task;
   }
}
