package ua.com.hedgehogsoft.task;

import java.util.Observable;

public class StartTaskMessage extends Observable
{
   public void send()
   {
      setChanged();
      notifyObservers();
   }
}