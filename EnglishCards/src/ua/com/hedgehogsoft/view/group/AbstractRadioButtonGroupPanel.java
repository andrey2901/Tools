package ua.com.hedgehogsoft.view.group;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public abstract class AbstractRadioButtonGroupPanel extends JPanel
{
   private static final long serialVersionUID = 1L;
   protected JRadioButton[] radioButtons = null;

   protected AbstractRadioButtonGroupPanel(String... radioButtonNames)
   {
      setLayout(new GridLayout(radioButtonNames.length, 1));

      radioButtons = new JRadioButton[radioButtonNames.length];

      ButtonGroup group = new ButtonGroup();

      for (int i = 0; i < radioButtonNames.length; i++)
      {
         radioButtons[i] = new JRadioButton(radioButtonNames[i]);

         group.add(radioButtons[i]);

         add(radioButtons[i]);
      }
      this.radioButtons[0].setSelected(true);
   }

   public void setTitledBorder(String title)
   {
      this.setBorder(BorderFactory.createTitledBorder(title));
   }

   public JRadioButton getSelected()
   {
      JRadioButton result = null;

      for (int i = 0; i < this.radioButtons.length; i++)
      {
         if (radioButtons[i].isSelected())
         {
            result = radioButtons[i];

            break;
         }
      }
      return result;
   }

   abstract void setExamMode();

   protected void setExamMode(String radioButtonName)
   {
      for (int i = 0; i < radioButtons.length; i++)
      {
         if (radioButtons[i].getText() == radioButtonName)
         {
            radioButtons[i].setSelected(true);
         }
         else
         {
            radioButtons[i].setEnabled(false);
         }
      }
   }

   protected void unsetExamMode()
   {
      for (int i = 0; i < radioButtons.length; i++)
      {
         radioButtons[i].setEnabled(true);
      }
   };
}
