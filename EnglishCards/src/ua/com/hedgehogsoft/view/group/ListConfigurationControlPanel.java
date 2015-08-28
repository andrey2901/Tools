package ua.com.hedgehogsoft.view.group;

import ua.com.hedgehogsoft.props.Properties;

public class ListConfigurationControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public ListConfigurationControlPanel()
   {
      super(Properties.getProperty("cards.radiobutton.simplelist.name"),
            Properties.getProperty("cards.radiobutton.translatedlist.name"),
            Properties.getProperty("cards.radiobutton.doublelist.name"));

      setTitledBorder(Properties.getProperty("cards.panel.listconfiguration.name"));
   }

   @Override
   void setExamMode()
   {
   }
}
