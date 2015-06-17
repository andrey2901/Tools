package ua.com.hedgehogsoft.metronome;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class Pendulum extends JPanel
{
   private static final long serialVersionUID = 1L;
   private double angle = Math.PI / 4;
   private int length;

   public Pendulum(int length)
   {
      this.length = length;
      setDoubleBuffered(true);
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

   public void setAngle(double angle)
   {
      this.angle = angle;
   }
}