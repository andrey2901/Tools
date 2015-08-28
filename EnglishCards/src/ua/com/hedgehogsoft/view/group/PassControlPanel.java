package ua.com.hedgehogsoft.view.group;

import ua.com.hedgehogsoft.props.Properties;

public class PassControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public PassControlPanel()
   {
      super(Properties.getProperty("cards.radiobutton.pass.single.text"),
            Properties.getProperty("cards.radiobutton.pass.nonstop.text"));

      setTitledBorder(Properties.getProperty("cards.panel.pass.title"));
   }

   @Override
   public void setExamMode()
   {
      setExamMode(Properties.getProperty("cards.radiobutton.pass.single.text"));
   }
}
