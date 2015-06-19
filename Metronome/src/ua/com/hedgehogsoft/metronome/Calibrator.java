package ua.com.hedgehogsoft.metronome;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calibrator
{
   private double factor = 0;

   public double calibrate()
   {
      double angleAccel, angleVelocity = 0, dt = 1.0;
      int prevSign = 0;
      double angle = Math.PI / 4;
      int length = 300;
      int i = 0;
      while (true)
      {
         angleAccel = -9.81 / length * Math.sin(angle);
         angleVelocity += angleAccel * dt;
         int sign = (int) Math.signum(angleVelocity);
         if (prevSign != sign)
         {
            prevSign = sign;

            if (i++ > 0)
            {
               factor += 0.01;
               factor = new BigDecimal(factor).setScale(2, RoundingMode.HALF_UP).doubleValue() / 10;
               System.out.println("Factor: " + factor);
               break;
            }
            factor = 0;
         }
         angle += angleVelocity * dt;
         try
         {
            Thread.sleep(10);
         }
         catch (InterruptedException ex)
         {
         }
         factor += 0.1;
      }
      return factor;
   }
}
