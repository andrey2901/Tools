package ua.com.hedgehogsoft.view.group;

import ua.com.hedgehogsoft.props.Properties;

public class SortControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public SortControlPanel()
   {
      super(Properties.getProperty("cards.radiobutton.sort.initial.text"),
            Properties.getProperty("cards.radiobutton.sort.alphabetic.text"),
            Properties.getProperty("cards.radiobutton.sort.translation.text"));

      setTitledBorder(Properties.getProperty("cards.panel.sort.title"));
   }

   @Override
   void setExamMode()
   {

   }
}
