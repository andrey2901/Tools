package ua.com.hedgehogsoft.metronome;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class IntervalsControlPanel extends JPanel implements Observer
{
   private static final long serialVersionUID = 1L;
   private JSlider intervals;
   private BeatsInterval bInterval;

   public IntervalsControlPanel(final BeatsInterval beatsInterval)
   {
      this.bInterval = beatsInterval;
      intervals = new JSlider(JSlider.HORIZONTAL, 5, 100, 50);
      Hashtable<Integer, JLabel> intervalsLabelTable = new Hashtable<Integer, JLabel>();
      for (int i = 5; i <= 100; i += 5)
      {
         intervalsLabelTable.put(i, new JLabel(Double.toString(i / 10.0)));
      }
      intervals.setLabelTable(intervalsLabelTable);
      intervals.setMajorTickSpacing(5);
      intervals.setMinorTickSpacing(5);
      intervals.setPaintTicks(true);
      intervals.setPaintLabels(true);
      intervals.setSnapToTicks(true);
      setLayout(new BorderLayout());
      add(intervals, BorderLayout.CENTER);
      setBorder(BorderFactory.createTitledBorder("Intervals in seconds"));
      bInterval.setValue(intervals.getValue() / 10.0);
      intervals.addMouseListener(new AbstractMouseListener()
      {
         @Override
         public void mouseReleased(MouseEvent e)
         {
            
            int x = intervals.getValue();
            System.out.println(x);
            
            bInterval.setValue(intervals.getValue() / 10.0);
         }
      });
   }

   @Override
   public void update(Observable o, Object arg)
   {
      if (bInterval == o)
      {
         intervals.setValue((int) (bInterval.getValue() * 10.0));
      }
   }
}
