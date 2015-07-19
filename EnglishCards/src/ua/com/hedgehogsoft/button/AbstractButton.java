package ua.com.hedgehogsoft.button;

import javax.swing.JButton;

import ua.com.hedgehogsoft.Labels;

public abstract class AbstractButton extends JButton implements Labels
{
   private static final long serialVersionUID = 1L;

   protected AbstractButton(String text)
   {
      super(text);
   }
}
