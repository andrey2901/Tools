package ua.com.hedgehogsoft.view.group;

public class ShuffleControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public ShuffleControlPanel()
   {
      super(nonShuffleRadioButtonName, onceShuffleRadioButtonName, eachPassShuffleRadioButtonName);

      setTitledBorder(shuffleControlTitledBorderName);
   }

   @Override
   void setExamMode()
   {
   }
}
