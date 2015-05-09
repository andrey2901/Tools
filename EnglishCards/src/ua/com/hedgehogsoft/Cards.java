package ua.com.hedgehogsoft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ua.com.hedgehogsoft.listener.ExitAction;
import ua.com.hedgehogsoft.listener.StartAction;
import ua.com.hedgehogsoft.listener.StopAction;
import ua.com.hedgehogsoft.task.ChangeWordsTaskState;

public class Cards implements Labels
{
   private Map<String, String> dictionary = null;
   private JLabel wordLabel = null;
   private JButton startButton = null;
   private JButton stopButton = null;
   private JButton exitButton = null;
   private ChangeWordsTaskState state = null;

   public Cards()
   {
      dictionary = new ProviderManager().getProvider().getWords();

      JFrame f = new JFrame(mainFrameTitle);

      wordLabel = new JLabel("", JLabel.CENTER);

      startButton = new JButton(startButtonName);

      startButton.addActionListener(new StartAction(wordLabel, dictionary, state));

      stopButton = new JButton(stopButtonName);

      stopButton.addActionListener(new StopAction(wordLabel, dictionary, startButton));

      exitButton = new JButton(exitButtonName);

      exitButton.addActionListener(new ExitAction());

      /*-------------------------------Pass Control Panel-------------------------------*/
      JPanel passControlPanel = new JPanel();

      passControlPanel.setLayout(new BoxLayout(passControlPanel, BoxLayout.Y_AXIS));

      JRadioButton singlePassRadioButton = new JRadioButton(singlePassRadioButtonName);

      singlePassRadioButton.setSelected(true);

      JRadioButton nonStopPassRadioButton = new JRadioButton(nonStopPassRadioButtonName);

      ButtonGroup passControlGroup = new ButtonGroup();

      passControlGroup.add(singlePassRadioButton);

      passControlGroup.add(nonStopPassRadioButton);

      passControlPanel.add(singlePassRadioButton);

      passControlPanel.add(nonStopPassRadioButton);

      passControlPanel.setBorder(BorderFactory.createTitledBorder(passControlTitledBorderName));
      /*----------------------------End Of Pass Control Panel----------------------------*/

      /*------------------------Translation Direction Control Panel------------------------*/
      JPanel translationDirectionControlPanel = new JPanel();

      translationDirectionControlPanel.setLayout(new BoxLayout(translationDirectionControlPanel, BoxLayout.Y_AXIS));

      JRadioButton directTranslationRadioButton = new JRadioButton(directTranslationRadioButtonName);

      directTranslationRadioButton.setSelected(true);

      JRadioButton reverseTranslationRadioButton = new JRadioButton(reverseTranslationRadioButtonName);

      ButtonGroup translationDirectionControlGroup = new ButtonGroup();

      translationDirectionControlGroup.add(directTranslationRadioButton);

      translationDirectionControlGroup.add(reverseTranslationRadioButton);

      translationDirectionControlPanel.add(directTranslationRadioButton);

      translationDirectionControlPanel.add(reverseTranslationRadioButton);

      translationDirectionControlPanel.setBorder(BorderFactory.createTitledBorder(translationDirectionControlTitledBorderName));
      /*---------------------End Of Translation Direction Control Panel---------------------*/

      /*------------------------List Configuration Control Panel------------------------*/

      JPanel listConfigurationControlPanel = new JPanel();

      listConfigurationControlPanel.setLayout(new BoxLayout(listConfigurationControlPanel, BoxLayout.Y_AXIS));

      JRadioButton simpleListConfigurationRadioButton = new JRadioButton(simpleListConfigurationRadioButtonName);

      simpleListConfigurationRadioButton.setSelected(true);

      JRadioButton translatedListConfigurationRadioButton = new JRadioButton(translatedListConfigurationRadioButtonName);

      JRadioButton doubleListConfigurationRadioButton = new JRadioButton(doubleListConfigurationRadioButtonName);

      ButtonGroup listConfigurationControlGroup = new ButtonGroup();

      listConfigurationControlGroup.add(simpleListConfigurationRadioButton);

      listConfigurationControlGroup.add(translatedListConfigurationRadioButton);

      listConfigurationControlGroup.add(doubleListConfigurationRadioButton);

      listConfigurationControlPanel.add(simpleListConfigurationRadioButton);

      listConfigurationControlPanel.add(translatedListConfigurationRadioButton);

      listConfigurationControlPanel.add(doubleListConfigurationRadioButton);

      listConfigurationControlPanel.setBorder(BorderFactory.createTitledBorder(listConfigurationControlTitledBorderName));
      /*---------------------End Of List Configuration Control Panel--------------------*/

      /*-------------------------------Shuffle Control Panel-------------------------------*/
      JPanel shuffleControlPanel = new JPanel();

      shuffleControlPanel.setLayout(new BoxLayout(shuffleControlPanel, BoxLayout.Y_AXIS));

      JCheckBox shuffleCheckBox = new JCheckBox(shuffleCheckBoxName);

      shuffleCheckBox.setSelected(true);

      shuffleControlPanel.add(shuffleCheckBox);
      /*----------------------------End Of Shuffle Control Panel---------------------------*/

      /*-------------------------------Settings Control Panel-------------------------------*/
      JPanel settingsPanel = new JPanel();

      settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

      settingsPanel.add(passControlPanel);

      settingsPanel.add(translationDirectionControlPanel);

      settingsPanel.add(listConfigurationControlPanel);

      settingsPanel.add(shuffleControlPanel);

      settingsPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.black));
      /*----------------------------End Of Settings Control Panel----------------------------*/

      /*-------------------------------Button Control Panel-------------------------------*/
      JPanel buttonPanel = new JPanel();

      buttonPanel.setLayout(new BorderLayout());

      buttonPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.black));

      JPanel functionButtonPanel = new JPanel();

      functionButtonPanel.add(startButton, BorderLayout.WEST);

      functionButtonPanel.add(stopButton, BorderLayout.EAST);

      buttonPanel.add(functionButtonPanel, BorderLayout.NORTH);

      JPanel exitButtonPanel = new JPanel();

      exitButtonPanel.add(exitButton);

      buttonPanel.add(exitButtonPanel, BorderLayout.SOUTH);
      /*----------------------------End Of Button Control Panel----------------------------*/

      f.setLayout(new BorderLayout());

      f.add(wordLabel, BorderLayout.CENTER);

      f.add(settingsPanel, BorderLayout.EAST);

      f.add(buttonPanel, BorderLayout.SOUTH);

      f.pack();

      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      f.setSize(900, 500);

      f.setLocationRelativeTo(null);

      f.setVisible(true);
   }

   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            @SuppressWarnings("unused")
            final Cards clock = new Cards();
         }
      });
   }
}