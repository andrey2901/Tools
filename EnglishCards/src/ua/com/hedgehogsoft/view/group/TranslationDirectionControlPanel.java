package ua.com.hedgehogsoft.view.group;

import ua.com.hedgehogsoft.props.Properties;

public class TranslationDirectionControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public TranslationDirectionControlPanel()
   {
      super(Properties.getProperty("cards.radiobutton.translation.direct.text"),
            Properties.getProperty("cards.radiobutton.translation.reverse.text"));

      setTitledBorder(Properties.getProperty("cards.panel.translation.direction.title"));
   }

   @Override
   void setExamMode()
   {

   }
}
