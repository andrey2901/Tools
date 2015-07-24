package ua.com.hedgehogsoft.view.group;

public class ShuffleControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public ShuffleControlPanel()
   {
      super(nonShuffleRadioButtonName, onceShuffleRadioButtonName, eachPassShuffleRadioButtonName);

      setTitledBorder(shuffleControlTitledBorderName);
   }

   public void setUnshuffleMode()
   {
      for (int i = 0; i < radioButtons.length; i++)
      {
         if (radioButtons[i].getText() == nonShuffleRadioButtonName)
         {
            radioButtons[i].setSelected(true);

            break;
         }
      }
   }

   @Override
   void setExamMode()
   {
   }
}
