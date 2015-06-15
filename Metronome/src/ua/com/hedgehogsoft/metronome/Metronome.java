package ua.com.hedgehogsoft.metronome;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Metronome
{
   public void init()
   {
      JFrame mainFrame = new JFrame("Metronome");

      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      mainFrame.setUndecorated(false);

      mainFrame.setSize(new Dimension(740, 550));

      mainFrame.setResizable(true);

      BeatsInterval beatsInterval = new BeatsInterval();

      final Pendulum p = new Pendulum(300, beatsInterval);

      mainFrame.add(p, BorderLayout.CENTER);

      JPanel controlPanel = new JPanel();

      controlPanel.setLayout(new BorderLayout());

      IntervalsControlPanel intervalsControlPanel = new IntervalsControlPanel(beatsInterval);

      BeatsControlPanel beatsControlPanel = new BeatsControlPanel(beatsInterval);

      beatsInterval.addObserver(intervalsControlPanel);

      beatsInterval.addObserver(beatsControlPanel);

      controlPanel.add(intervalsControlPanel, BorderLayout.NORTH);

      controlPanel.add(beatsControlPanel, BorderLayout.SOUTH);

      mainFrame.add(controlPanel, BorderLayout.NORTH);

      final JButton startButton = new JButton("Start");

      startButton.addActionListener(new ActionListener()
      {
         Thread metronom = null;

         /** TODO Uses deprecated methods */
         @SuppressWarnings("deprecation")
         @Override
         public void actionPerformed(ActionEvent e)
         {
            if (e.getActionCommand() == "Start")
            {
               if (metronom == null)
               {
                  metronom = new Thread(p);

                  metronom.start();
               }
               else
               {
                  metronom.resume();
               }
               startButton.setText("Pause");
            }
            if (e.getActionCommand() == "Pause")
            {
               metronom.suspend();

               startButton.setText("Start");
            }
         }
      });

      mainFrame.add(startButton, BorderLayout.SOUTH);

      mainFrame.setLocationRelativeTo(null);

      mainFrame.setVisible(true);

   }

   public static void main(String args[])
   {
      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            Metronome metronome = new Metronome();
            metronome.init();
         }
      });
   }
}
