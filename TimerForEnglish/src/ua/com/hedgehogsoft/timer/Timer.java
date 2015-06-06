package ua.com.hedgehogsoft.timer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class Timer
{
   private JFrame f = null;
   private java.util.Timer timer = null;
   private JLabel timeLabel = null;
   private JButton startButton = null;
   private JButton stopButton = null;
   private JButton exitButton = null;
   private List<String> circleTimes = null;
   private ActionListener startAction = new StartAction();
   private ActionListener stopAction = new StopAction();
   private ActionListener nextCircleAction = new NextCircleAction();
   private String initialTime = "00:00:00";
   private String startButtonName = "Start";
   private String stopButtonName = "Stop";
   private String nextCircleButtonName = "Next circle";
   private String exitButtonName = "Exit";

   public void init()
   {
      f = new JFrame("Timer");
      f.setUndecorated(false);
      f.setSize(new Dimension(740, 440));
      f.setResizable(false);
      timeLabel = new JLabel(initialTime, JLabel.CENTER);
      startButton = new JButton(startButtonName);
      startButton.addActionListener(startAction);
      stopButton = new JButton(stopButtonName);
      stopButton.addActionListener(stopAction);
      exitButton = new JButton(exitButtonName);
      exitButton.addActionListener(new ExitAction());
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.add(timeLabel, BorderLayout.CENTER);
      Panel buttonPanel = new Panel();
      Panel functionButtonPanel = new Panel();
      functionButtonPanel.add(startButton, BorderLayout.WEST);
      functionButtonPanel.add(stopButton, BorderLayout.EAST);
      buttonPanel.add(functionButtonPanel, BorderLayout.NORTH);
      Panel exitButtonPanel = new Panel();
      exitButtonPanel.add(exitButton);
      buttonPanel.add(exitButtonPanel, BorderLayout.SOUTH);
      f.add(buttonPanel, BorderLayout.SOUTH);
      f.setLocationRelativeTo(null);
      f.setVisible(true);
      Font myFont = new Font("Serif", Font.BOLD, f.getSize().width / 4);
      timeLabel.setFont(myFont);
   }

   private class StartAction implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         System.out.println(startButtonName);
         startButton.setText(nextCircleButtonName);
         startButton.removeActionListener(startAction);
         startButton.addActionListener(nextCircleAction);
         circleTimes = new ArrayList<String>();
         if (timer == null)
         {
            timer = new java.util.Timer();
         }
         String[] values = timeLabel.getText().split(":");
         int minutes = Integer.valueOf(values[0]);
         int seconds = Integer.valueOf(values[1]);
         int centiseconds = Integer.valueOf(values[2]);
         timer.schedule(new UpdateUITask(minutes, seconds, centiseconds), 0, 10);
      }
   }

   private class StopAction implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         System.out.println(stopButtonName);
         if (timer != null)
         {
            timer.cancel();
            timer = null;
         }
         if (startButton.getText().equals(nextCircleButtonName))
         {
            startButton.setText(startButtonName);
            startButton.removeActionListener(nextCircleAction);
            startButton.addActionListener(startAction);
         }
         timeLabel.setText(initialTime);

         String[] columnNames = { "Circle", "Circle time", "Common time" };

         Object[][] data = new String[circleTimes.size()][3];

         for (int i = 0; i < circleTimes.size(); i++)
         {
            data[i][0] = Integer.toString(i + 1) + ".";

            if (i == 0)
            {
               data[i][1] = circleTimes.get(i);
            }
            else
            {
               data[i][1] = getCircleTime(circleTimes.get(i), circleTimes.get(i - 1));

            }
            data[i][2] = circleTimes.get(i);
         }

         JTable table = new JTable(data, columnNames);

         TableColumn column = null;

         for (int i = 0; i < columnNames.length; i++)
         {
            column = table.getColumnModel().getColumn(i);

            if (i == 0)
            {
               column.setPreferredWidth(30);
            }
            else
            {
               column.setPreferredWidth(150);
            }
         }

         JOptionPane.showMessageDialog(f, new JScrollPane(table), "Results", JOptionPane.PLAIN_MESSAGE);
      }
   }

   private class NextCircleAction implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         circleTimes.add(timeLabel.getText());
      }
   }

   private class ExitAction implements ActionListener
   {
      @Override
      public void actionPerformed(ActionEvent e)
      {
         System.out.println(exitButtonName);
         System.exit(0);
      }
   }

   private class UpdateUITask extends TimerTask
   {
      int minutes;
      int seconds;
      int centiseconds;

      UpdateUITask(int minutes, int seconds, int centiseconds)
      {
         this.minutes = minutes;
         this.seconds = seconds;
         this.centiseconds = centiseconds;
      }

      @Override
      public void run()
      {
         EventQueue.invokeLater(new Runnable()
         {
            @Override
            public void run()
            {
               String time = getTime(minutes, seconds, centiseconds++);
               timeLabel.setText(time);
               if (centiseconds > 99)
               {
                  seconds++;
                  if (seconds > 59)
                  {
                     minutes++;
                     seconds = 0;
                  }
                  centiseconds = 0;
               }
            }
         });
      }
   }

   private String getTime(int minutes, int seconds, int centiseconds)
   {
      StringBuilder result = new StringBuilder();

      result = addZero(result, minutes);

      result.append(":");

      result = addZero(result, seconds);

      result.append(":");

      result = addZero(result, centiseconds);

      return result.toString();
   }

   private StringBuilder addZero(StringBuilder result, int time)
   {
      if (time < 10)
      {
         result.append("0" + String.valueOf(time));
      }
      else
      {
         result.append(String.valueOf(time));
      }
      return result;
   }

   private String getCircleTime(String currentTime, String previousTime)
   {
      String[] values = currentTime.split(":");

      int cur = Integer.valueOf(values[0]) * 60 * 100 + Integer.valueOf(values[1]) * 100 + Integer.valueOf(values[2]);

      values = previousTime.split(":");

      int prev = Integer.valueOf(values[0]) * 60 * 100 + Integer.valueOf(values[1]) * 100 + Integer.valueOf(values[2]);

      int circleTime = cur - prev;

      int minutes = circleTime / 6000;

      int seconds = circleTime % 6000 / 100;

      int centiseconds = circleTime % 600 % 100;

      return getTime(minutes, seconds, centiseconds);
   }

   public static void main(String args[])
   {
      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            Timer clock = new Timer();
            clock.init();
         }
      });
   }
}
