package ua.com.hedgehogsoft.task;

public class ChangeWordsTaskState
{
   private int counter = 0;
   private boolean translated = false;

   public ChangeWordsTaskState(int counter)
   {
      this.counter = counter;
   }

   public ChangeWordsTaskState(int counter, boolean translated)
   {
      this.counter = counter;

      this.translated = translated;
   }

   public int getCounter()
   {
      return counter;
   }

   public void setCounter(int counter)
   {
      this.counter = counter;
   }

   public boolean isTranslated()
   {
      return translated;
   }

   public void setTranslated(boolean translated)
   {
      this.translated = translated;
   }
}