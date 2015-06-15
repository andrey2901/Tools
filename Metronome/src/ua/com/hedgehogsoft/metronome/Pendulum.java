package ua.com.hedgehogsoft.metronome;

import java.awt.Color;
import java.awt.Graphics;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;

public class Pendulum extends JPanel implements Runnable
{
   private static final long serialVersionUID = 1L;
   private double angle = Math.PI / 4;
   private int length;
   private BeatsInterval beatsInterval;
   private MidiChannel[] channels;

   public Pendulum(int length, BeatsInterval beatsInterval)
   {
      this.length = length;
      this.beatsInterval = beatsInterval;
      setDoubleBuffered(true);
      try
      {
         Synthesizer synth = MidiSystem.getSynthesizer();
         synth.open();
         synth.loadAllInstruments(synth.getDefaultSoundbank());
         channels = synth.getChannels();
         channels[0].programChange(0, 115);
      }
      catch (MidiUnavailableException e)
      {
         e.printStackTrace();
      }
   }

   public void run()
   {
      double angleAccel, angleVelocity = 0, dt;
      double prevTime = 0;
      int prevSign = 0;
      while (true)
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
            channels[0].noteOn(75, 75);
            prevSign = sign;
            double currentTime = System.nanoTime();
            System.out.println((currentTime - prevTime) / 1000000000);
            prevTime = currentTime;
            System.out.println("Boom!!!");
            channels[0].noteOff(75, 75);
         }
         angle += angleVelocity * dt;
         repaint();
         try
         {
            Thread.sleep(10);
         }
         catch (InterruptedException ex)
         {
         }
      }
   }

   @Override
   public void paint(Graphics g)
   {
      g.setColor(Color.WHITE);
      g.fillRect(0, 0, getWidth(), getHeight());
      g.setColor(Color.BLACK);
      int anchorX = getWidth() / 2;
      int anchorY = getHeight() - 20;
      int ballX = anchorX - (int) (Math.sin(angle) * length);
      int ballY = anchorY - (int) (Math.cos(angle) * length);
      g.drawLine(anchorX, anchorY, ballX, ballY);
      g.fillOval(anchorX - 3, anchorY - 4, 7, 7);
      g.fillOval(ballX - 7, ballY - 7, 14, 14);
   }
}