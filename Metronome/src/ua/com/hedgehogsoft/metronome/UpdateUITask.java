package ua.com.hedgehogsoft.metronome;

import java.util.TimerTask;

public class UpdateUITask extends TimerTask
{
   private BeatsInterval beatsInterval;
   private int length;
   private MidiPlayer player;
   private Pendulum panel;
   private double angle = Math.PI / 4;
   double angleAccel, angleVelocity = 0, dt;
   double prevTime = 0;
   int prevSign = 0;

   public UpdateUITask(int length, BeatsInterval beatsInterval, MidiPlayer player, Pendulum panel)
   {
      this.length = length;
      this.beatsInterval = beatsInterval;
      this.player = player;
      this.panel = panel;
   }

   @Override
   public void run()
   {

      dt = 0.181 / beatsInterval.getValue();
      // Get angle acceleration from tangential acceleration
      // Tangential acceleration: ma = mg*sin A => a = g * sin A
      // Angle acceleration = a / R => g * sin A / R
      angleAccel = -9.81 / length * Math.sin(angle);
      angleVelocity += angleAccel * dt;
      int sign = (int) Math.signum(angleVelocity);
      if (prevSign != sign)
      {
         player.noteOn();
         prevSign = sign;
         double currentTime = System.nanoTime();
         System.out.println((currentTime - prevTime) / 1000000000);
         prevTime = currentTime;
         System.out.println("Boom!!!");
         player.noteOff();
      }
      angle += angleVelocity * dt;
      panel.setAngle(angle);
      panel.repaint();

   }
}
