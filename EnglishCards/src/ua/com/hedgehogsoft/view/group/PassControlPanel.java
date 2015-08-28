package ua.com.hedgehogsoft.view.group;

import ua.com.hedgehogsoft.props.Properties;

public class PassControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public PassControlPanel()
   {
      super(Properties.getProperty("cards.radiobutton.singlepass.name"),
            Properties.getProperty("cards.radiobutton.nonstoppass.name"));

      setTitledBorder(Properties.getProperty("cards.control.pass.title.name"));
   }

   @Override
   public void setExamMode()
   {
      setExamMode(Properties.getProperty("cards.radiobutton.singlepass.name"));
   }
}
