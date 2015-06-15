package ua.com.hedgehogsoft.metronome;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class BeatsControlPanel extends JPanel implements Observer
{
   private static final long serialVersionUID = 1L;
   JSlider beatsSlider;
   BeatsInterval beatsInterval;

   public BeatsControlPanel(final BeatsInterval beatsInterval)
   {
      this.beatsInterval = beatsInterval;
      beatsSlider = new JSlider(JSlider.HORIZONTAL, 0, 120, 60);
      beatsSlider.setMajorTickSpacing(5);
      beatsSlider.setMinorTickSpacing(1);
      beatsSlider.setPaintTicks(true);
      beatsSlider.setPaintLabels(true);
      beatsSlider.setSnapToTicks(true);
      setLayout(new BorderLayout());
      add(beatsSlider, BorderLayout.CENTER);
      setBorder(BorderFactory.createTitledBorder("Beats per minute"));
      beatsSlider.addMouseListener(new AbstractMouseListener()
      {
         @Override
         public void mouseReleased(MouseEvent e)
         {
            beatsInterval.setValue(beatsSlider.getValue() / 60.0);
         }
      });
   }

   @Override
   public void update(Observable o, Object arg)
   {
      if (beatsInterval == o)
      {
         beatsSlider.setValue((int) (60 / beatsInterval.getValue()));
      }
   }
}
