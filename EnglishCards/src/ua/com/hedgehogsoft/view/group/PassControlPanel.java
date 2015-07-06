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
      setExamMode(singlePassRadioButtonName);
   }
}
