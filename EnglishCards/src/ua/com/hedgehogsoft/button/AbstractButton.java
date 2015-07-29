package ua.com.hedgehogsoft.button;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.apache.log4j.Logger;

import ua.com.hedgehogsoft.Labels;

public abstract class AbstractButton extends JButton implements Labels
{
   private static final long serialVersionUID = 1L;
   private static final Logger logger = Logger.getLogger(AbstractButton.class);

   protected AbstractButton(String text)
   {
      super(text);
   }

   /**
    * <p>
    * Adds an ActionListener to the button.
    * </p>
    * <p>
    * Overridden version of this method adds only one ActionListener to the
    * button. If the button already has an ActionListener this listener will be
    * replaced by new one.
    * </p>
    */
   @Override
   public void addActionListener(ActionListener l)
   {
      if (l != null)
      {
         if (getActionListeners().length == 0)
         {
            super.addActionListener(l);

            logger.trace("New ActionListener [" + l.getClass().getName() + "] was added to the button.");
         }
         else if (getActionListeners().length == 1)
         {
            logger.trace("ActionListener [" + getActionListeners()[0].getClass().getName() + "] will be replaced by ["
                  + l.getClass().getName() + "].");

            removeActionListener(getActionListeners()[0]);

            super.addActionListener(l);

            logger.trace("Old ActionListener was replaced by [" + l.getClass().getName() + "].");
         }
      }
   }
}
