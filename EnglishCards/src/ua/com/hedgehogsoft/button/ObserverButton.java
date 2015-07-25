package ua.com.hedgehogsoft.button;

import java.util.Observer;

public abstract class ObserverButton extends AbstractButton implements Observer
{
   private static final long serialVersionUID = 1L;

   protected ObserverButton(String text)
   {
      super(text);
   }
}
