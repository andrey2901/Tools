package ua.com.hedgehogsoft;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CheckBoxGroup extends JPanel
{
   private static final long serialVersionUID = 5231102527393161398L;

   public List<String> getSelectedItems()
   {
      List<String> result = new ArrayList<String>();

      Component[] components = getComponents();

      for (int i = 0; i < components.length; i++)
      {
         if (((JCheckBox) components[i]).isSelected())
         {
            result.add(((JCheckBox) components[i]).getText());
         }
      }

      return result;
   }
}
