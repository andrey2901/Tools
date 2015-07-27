package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionListener;
import ua.com.hedgehogsoft.Labels;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.view.MainFrame;

/**
 * The class(interface) <code>AbstractListener</code> TODO: reorganize the
 * structure of application: the input argument of the constructor should be a
 * JFrame (in our case this is main frame of application).
 * 
 * @author Andrey
 * @version 1.00 10 ��� 2015 �.
 */
public abstract class AbstractListener implements ActionListener, Labels
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
