package ua.com.hedgehogsoft.task;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;

import ua.com.hedgehogsoft.Labels;
import ua.com.hedgehogsoft.props.Properties;
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
         if (button.getText() == Properties.getProperty("cards.radiobutton.singlepass.name"))
         {
            taskConfig.setPassConfig(PassConfig.SINGLE);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.nonstoppass.name"))
         {
            taskConfig.setPassConfig(PassConfig.NON_STOP);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.directtranslation.name"))
         {
            taskConfig.setTranslationDirection(TranslationDirection.DIRECT);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.reversetranslation.name"))
         {
            taskConfig.setTranslationDirection(TranslationDirection.REVERSE);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.simplelist.name"))
         {
            taskConfig.setListConfig(ListConfig.SIMPLE);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.translatedlist.name"))
         {
            taskConfig.setListConfig(ListConfig.WITH_TRANSLATION);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.doublelist.name"))
         {
            taskConfig.setListConfig(ListConfig.DOUBLE_VIEW);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.onceshuffle.name"))
         {
            taskConfig.setShuffleConfig(ShuffleConfig.ONCE);
         }

         if (button.getText() == Properties.getProperty("cards.radiobutton.eachpassshuffle.name"))
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
