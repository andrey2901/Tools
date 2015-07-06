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
   public void setExamMode()
   {
      for (int i = 0; i < radioButtons.length; i++)
      {
         if (radioButtons[i].getText() == simpleListConfigurationRadioButtonName)
         {
            radioButtons[i].setSelected(true);
         }

         if (radioButtons[i].getText() == doubleListConfigurationRadioButtonName)
         {
            radioButtons[i].setEnabled(false);
         }
      }
   }
}
