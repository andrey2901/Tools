package ua.com.hedgehogsoft.task;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import ua.com.hedgehogsoft.props.Properties;
import ua.com.hedgehogsoft.task.config.TaskConfig;
import ua.com.hedgehogsoft.task.config.enums.ListConfig;
import ua.com.hedgehogsoft.task.config.enums.PassConfig;
import ua.com.hedgehogsoft.task.config.enums.ShuffleConfig;
import ua.com.hedgehogsoft.task.config.enums.TranslationDirection;

public class ChangeWordsTaskSettingsResolver
{
   private List<JRadioButton> selectedRadioButtons = null;
   private List<JComboBox<?>> comboBoxList = null;
   private StopTaskMessage stopMessage = null;

   public ChangeWordsTaskSettingsResolver(ChangeWordsTaskSettings settings)
   {
      selectedRadioButtons = new ArrayList<JRadioButton>();

      comboBoxList = new ArrayList<>(1);

      JComponent[] controlComponents = settings.getUiControlComponents();

      for (int i = 0; i < controlComponents.length; i++)
      {
         JComponent component = controlComponents[i];

         if (component instanceof JRadioButton)
         {
            if (((JRadioButton) component).isSelected())
            {
               selectedRadioButtons.add((JRadioButton) component);
            }
         }
         if (component instanceof JComboBox)
         {
            comboBoxList.add((JComboBox<?>) component);
         }
      }
      stopMessage = settings.getStopMessage();
   }

   public TaskConfig getTaskConfig()
   {
      TaskConfig taskConfig = new TaskConfig();

      for (JRadioButton button : selectedRadioButtons)
      {
         if (button.getText() == Properties.getProperty("cards.radiobutton.pass.single.text"))
         {
            taskConfig.setPassConfig(PassConfig.SINGLE);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.pass.nonstop.text"))
         {
            taskConfig.setPassConfig(PassConfig.NON_STOP);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.translation.direct.text"))
         {
            taskConfig.setTranslationDirection(TranslationDirection.DIRECT);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.translation.reverse.text"))
         {
            taskConfig.setTranslationDirection(TranslationDirection.REVERSE);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.list.simple.text"))
         {
            taskConfig.setListConfig(ListConfig.SIMPLE);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.list.translated.text"))
         {
            taskConfig.setListConfig(ListConfig.WITH_TRANSLATION);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.list.double.text"))
         {
            taskConfig.setListConfig(ListConfig.DOUBLE_VIEW);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.shuffle.once.text"))
         {
            taskConfig.setShuffleConfig(ShuffleConfig.ONCE);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.shuffle.eachpass.text"))
         {
            taskConfig.setShuffleConfig(ShuffleConfig.EACH_PASS);
         }
      }

      JComboBox<?> timePeriodComboBox = comboBoxList.get(0);

      int timePeriod = (int) timePeriodComboBox.getSelectedItem();

      taskConfig.setTimePeriod(timePeriod);

      taskConfig.setStopMessage(stopMessage);

      return taskConfig;
   }
}
