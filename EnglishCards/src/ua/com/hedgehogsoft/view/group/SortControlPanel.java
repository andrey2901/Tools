package ua.com.hedgehogsoft.view.group;

public class SortControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public SortControlPanel()
   {
      super(initialRadioButtonName, alphabeticRadioButtonName);

      setTitledBorder(sortControlTitledBorderName);
   }

   @Override
   void setExamMode()
   {

   }
}