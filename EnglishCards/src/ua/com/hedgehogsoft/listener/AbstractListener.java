package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionListener;
import java.util.TimerTask;

import ua.com.hedgehogsoft.Cards;
import ua.com.hedgehogsoft.Labels;
import ua.com.hedgehogsoft.task.ChangeWordsTask;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

/**
 * The class(interface) <code>AbstractListener</code> TODO: reorganize the
 * structure of application: the input argument of the constructor should be a
 * JFrame (in our case this is main frame of application).
 * 
 * @author Andrey
 * @version 1.00 10 мая 2015 г.
 */
public abstract class AbstractListener implements ActionListener, Labels
{
   protected Cards cards = null;
   protected ChangeWordsTaskState state = null;
   protected ChangeWordsTask task = null;

   public AbstractListener(Cards cards, ChangeWordsTaskState state)
   {
      this.cards = cards;
      this.state = state;
   }

   public AbstractListener(Cards cards, ChangeWordsTaskState state, ChangeWordsTask task)
   {
      this.cards = cards;
      this.state = state;
      this.task = task;
   }

   public TimerTask getTask()
   {
      return task;
   }
}
