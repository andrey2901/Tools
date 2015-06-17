package ua.com.hedgehogsoft.timer;

public class StopWatch
{
   private long startTime;

   public void start()
   {
      startTime = System.nanoTime();
   }

   public long getTime()
   {
      return (System.nanoTime() - startTime) / 1000000L;
   }
}
