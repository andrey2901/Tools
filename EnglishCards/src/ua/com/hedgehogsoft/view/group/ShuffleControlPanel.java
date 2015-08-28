package ua.com.hedgehogsoft.view.group;

import ua.com.hedgehogsoft.props.Properties;

public class ShuffleControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public ShuffleControlPanel()
   {
      super(Properties.getProperty("cards.radiobutton.shuffle.non.text"),
            Properties.getProperty("cards.radiobutton.shuffle.once.text"),
            Properties.getProperty("cards.radiobutton.shuffle.eachpass.text"));

      setTitledBorder(Properties.getProperty("cards.panel.shuffle.title"));
   }

   public void setUnshuffleMode()
   {
      for (int i = 0; i < radioButtons.length; i++)
      {
         if (radioButtons[i].getText() == Properties.getProperty("cards.radiobutton.shuffle.non.text"))
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
