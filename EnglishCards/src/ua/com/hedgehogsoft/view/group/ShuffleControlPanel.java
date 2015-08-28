package ua.com.hedgehogsoft.view.group;

import ua.com.hedgehogsoft.props.Properties;

public class ShuffleControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public ShuffleControlPanel()
   {
      super(Properties.getProperty("cards.radiobutton.nonshuffle.name"),
            Properties.getProperty("cards.radiobutton.onceshuffle.name"),
            Properties.getProperty("cards.radiobutton.eachpassshuffle.name"));

      setTitledBorder(Properties.getProperty("cards.panel.shuffle.name"));
   }

   public void setUnshuffleMode()
   {
      for (int i = 0; i < radioButtons.length; i++)
      {
         if (radioButtons[i].getText() == Properties.getProperty("cards.radiobutton.nonshuffle.name"))
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
