package ua.com.hedgehogsoft.timer;

public class StopWatch
{
   private long startTime;
   private long prevTime;

   public void start()
   {
      startTime = System.currentTimeMillis();
      prevTime = startTime;
   }

   public long getElapsedTime()
   {
      return (System.currentTimeMillis() - startTime);
   }

   public long getCicleTime()
   {
      long curTime = System.currentTimeMillis();
      long cicleTime = curTime - prevTime;
      prevTime = curTime;
      return cicleTime;
   }
}
