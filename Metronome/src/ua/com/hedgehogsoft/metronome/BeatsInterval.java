package ua.com.hedgehogsoft.metronome;

import java.util.Observable;


/**
 * The class(interface) <code>BeatsInterval</code> saves intervals in seconds between beats.
 * 
 * @author Andrey
 * @version 1.00
 * 15 θώνÿ 2015 γ. 
 */
public class BeatsInterval extends Observable
{
   private double n = 0;

   public void setValue(double value)
   {
      n = value;
      System.out.println(n);
      setChanged();
      notifyObservers();
   }

   public double getValue()
   {
      return n;
   }
}
