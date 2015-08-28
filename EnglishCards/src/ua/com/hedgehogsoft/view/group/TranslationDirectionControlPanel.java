package ua.com.hedgehogsoft.view.group;

import ua.com.hedgehogsoft.props.Properties;

public class TranslationDirectionControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public TranslationDirectionControlPanel()
   {
      super(Properties.getProperty("cards.radiobutton.directtranslation.name"),
            Properties.getProperty("cards.radiobutton.reversetranslation.name"));

      setTitledBorder(Properties.getProperty("cards.panel.direction.title.name"));
   }

   @Override
   void setExamMode()
   {

   }
}
