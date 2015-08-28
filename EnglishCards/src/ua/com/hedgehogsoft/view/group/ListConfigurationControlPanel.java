package ua.com.hedgehogsoft.view.group;

import ua.com.hedgehogsoft.props.Properties;

public class ListConfigurationControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public ListConfigurationControlPanel()
   {
      super(Properties.getProperty("cards.radiobutton.list.simple.text"),
            Properties.getProperty("cards.radiobutton.list.translated.text"),
            Properties.getProperty("cards.radiobutton.list.double.text"));

      setTitledBorder(Properties.getProperty("cards.panel.list.title"));
   }

   @Override
   void setExamMode()
   {
   }
}
