package ua.com.hedgehogsoft.view.group;

import ua.com.hedgehogsoft.props.Properties;

public class SortControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public SortControlPanel()
   {
      super(Properties.getProperty("cards.radiobutton.sort.initial.name"),
            Properties.getProperty("cards.radiobutton.sort.alphabetic.name"),
            Properties.getProperty("cards.radiobutton.sort.translation.name"));

      setTitledBorder(Properties.getProperty("cards.panel.sort.name"));
   }

   @Override
   void setExamMode()
   {

   }
}
