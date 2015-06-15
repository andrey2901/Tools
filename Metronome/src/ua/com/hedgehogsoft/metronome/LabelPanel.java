package ua.com.hedgehogsoft.metronome;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabelPanel extends JPanel
{
   private static final long serialVersionUID = 1L;
   JLabel label;

   public LabelPanel(String title)
   {
      label = new JLabel("qwertret");
      label.setBackground(Color.WHITE);
      setLayout(new BorderLayout());
      add(label, BorderLayout.CENTER);
      setBorder(BorderFactory.createTitledBorder(title));
   }

   public void setValue(String value)
   {
      label.setText(value);
   }

   public String getValue()
   {
      return label.getText();
   }
}
