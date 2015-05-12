package ua.com.hedgehogsoft.task;

import java.util.Observable;

public class StopTaskMessage extends Observable
{
   public void send()
   {
      setChanged();
      notifyObservers();
   }
}
