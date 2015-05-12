package ua.com.hedgehogsoft.task;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import ua.com.hedgehogsoft.Labels;
import ua.com.hedgehogsoft.task.config.TaskConfig;
import ua.com.hedgehogsoft.task.config.enums.ListConfig;
import ua.com.hedgehogsoft.task.config.enums.PassConfig;
import ua.com.hedgehogsoft.task.config.enums.ShuffleConfig;
import ua.com.hedgehogsoft.task.config.enums.TranslationDirection;

public class ChangeWordsTaskSettingsResolver implements Labels
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
         if (button.getText() == singlePassRadioButtonName)
         {
            taskConfig.setPassConfig(PassConfig.SINGLE);
         }

         if (button.getText() == nonStopPassRadioButtonName)
         {
            taskConfig.setPassConfig(PassConfig.NON_STOP);
         }

         if (button.getText() == directTranslationRadioButtonName)
         {
            taskConfig.setTranslationDirection(TranslationDirection.DIRECT);
         }

         if (button.getText() == reverseTranslationRadioButtonName)
         {
            taskConfig.setTranslationDirection(TranslationDirection.REVERSE);
         }

         if (button.getText() == simpleListConfigurationRadioButtonName)
         {
            taskConfig.setListConfig(ListConfig.SIMPLE);
         }

         if (button.getText() == translatedListConfigurationRadioButtonName)
         {
            taskConfig.setListConfig(ListConfig.WITH_TRANSLATION);
         }

         if (button.getText() == doubleListConfigurationRadioButtonName)
         {
            taskConfig.setListConfig(ListConfig.DOUBLE_VIEW);
         }

         if (button.getText() == onceShuffleRadioButtonName)
         {
            taskConfig.setShuffleConfig(ShuffleConfig.ONCE);
         }

         if (button.getText() == eachPassShuffleRadioButtonName)
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
