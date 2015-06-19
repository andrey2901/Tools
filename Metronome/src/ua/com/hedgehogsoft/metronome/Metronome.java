package ua.com.hedgehogsoft.metronome;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Metronome
{
   private Timer timer = null;

   public void init()
   {
      JFrame mainFrame = new JFrame("Metronome");

      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      mainFrame.setUndecorated(false);

      mainFrame.setSize(new Dimension(740, 550));

      mainFrame.setResizable(true);

      final BeatsInterval beatsInterval = new BeatsInterval();

      final Pendulum p = new Pendulum(300);

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

      final MidiPlayer synthesizer = new MidiPlayer();

      final JButton startButton = new JButton("Start");

      startButton.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            if (e.getActionCommand() == "Start")
            {
               if (timer == null)
               {
                  timer = new java.util.Timer();
               }
               timer.schedule(new UpdateUITask(300, beatsInterval, synthesizer, p, new Calibrator().calibrate()), 0, 10);
               startButton.setText("Stop");
            }
            if (e.getActionCommand() == "Stop")
            {
               if (timer != null)
               {
                  timer.cancel();
                  timer = null;
               }
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
