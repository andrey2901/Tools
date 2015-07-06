package ua.com.hedgehogsoft.view.group;

public class ListConfigurationControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public ListConfigurationControlPanel()
   {
      super(simpleListConfigurationRadioButtonName, translatedListConfigurationRadioButtonName,
            doubleListConfigurationRadioButtonName);

      setTitledBorder(listConfigurationControlTitledBorderName);
   }

   @Override
   void setExamMode()
   {
   }
}
