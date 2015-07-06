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
   public void setExamMode()
   {
      for (int i = 0; i < radioButtons.length; i++)
      {
         if (radioButtons[i].getText() == nonShuffleRadioButtonName)
         {
            radioButtons[i].setSelected(true);
         }

         if (radioButtons[i].getText() == eachPassShuffleRadioButtonName)
         {
            radioButtons[i].setEnabled(false);
         }
      }
   }
}
