package ua.com.hedgehogsoft.view.group;

public class PassControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public PassControlPanel()
   {
      super(singlePassRadioButtonName, nonStopPassRadioButtonName);

      setTitledBorder(passControlTitledBorderName);
   }

   @Override
   public void setExamMode()
   {
      for (int i = 0; i < radioButtons.length; i++)
      {
         if (radioButtons[i].getText() == singlePassRadioButtonName)
         {
            radioButtons[i].setSelected(true);
         }
         else
         {
            radioButtons[i].setEnabled(false);
         }
      }
   }
}
