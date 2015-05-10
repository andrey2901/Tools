package ua.com.hedgehogsoft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

import ua.com.hedgehogsoft.listener.ExitAction;
import ua.com.hedgehogsoft.listener.StartAction;
import ua.com.hedgehogsoft.listener.StopAction;

public class Cards implements Labels
{
   private Map<String, String> dictionary = null;
   private JLabel wordLabel = null;
   private JButton startButton = null;
   private JButton stopButton = null;
   private JButton exitButton = null;
   private JProgressBar prgBar = null;

   public Cards()
   {
      dictionary = new ProviderManager().getProvider().getWords();

      JFrame mainFrame = new JFrame(mainFrameTitle);

      /*-------------------------------Word Label Panel-------------------------------*/
      JPanel wordLabelControlPanel = new JPanel();

      wordLabelControlPanel.setLayout(new BorderLayout());

      wordLabel = new JLabel("", JLabel.CENTER);

      prgBar = new JProgressBar();

      prgBar.setSize(wordLabelControlPanel.getWidth(), 30);

      prgBar.setStringPainted(true);

      wordLabelControlPanel.add(wordLabel, BorderLayout.CENTER);

      wordLabelControlPanel.add(prgBar, BorderLayout.SOUTH);
      /*----------------------------End Of Word Label Panel----------------------------*/

      /*----------------------------Buttons initialization-----------------------------*/
      startButton = new JButton(startButtonName);

      startButton.addActionListener(new StartAction(wordLabel, prgBar, dictionary, null));

      stopButton = new JButton(stopButtonName);

      stopButton.addActionListener(new StopAction(wordLabel, prgBar, dictionary, startButton));

      exitButton = new JButton(exitButtonName);

      exitButton.addActionListener(new ExitAction());
      /*-------------------------End Of Buttons initialization--------------------------*/

      /*-------------------------------Pass Control Panel-------------------------------*/
      JPanel passControlPanel = new JPanel();

      passControlPanel.setLayout(new GridLayout(2, 1));

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

      translationDirectionControlPanel.setLayout(new GridLayout(2, 1));

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

      listConfigurationControlPanel.setLayout(new GridLayout(3, 1));

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

      shuffleControlPanel.setLayout(new GridLayout(2, 1));

      JRadioButton onceShuffleRadioButton = new JRadioButton(onceShuffleRadioButtonName);

      onceShuffleRadioButton.setSelected(true);

      JRadioButton eachPassShuffleRadioButton = new JRadioButton(eachPassShuffleRadioButtonName);

      ButtonGroup shuffleControlGroup = new ButtonGroup();

      shuffleControlGroup.add(onceShuffleRadioButton);

      shuffleControlGroup.add(eachPassShuffleRadioButton);

      shuffleControlPanel.add(onceShuffleRadioButton);

      shuffleControlPanel.add(eachPassShuffleRadioButton);

      shuffleControlPanel.setBorder(BorderFactory.createTitledBorder(shuffleControlTitledBorderName));
      /*----------------------------End Of Shuffle Control Panel---------------------------*/

      /*----------------------------Time Interval Control Panel----------------------------*/
      JPanel timeIntervalControlPanel = new JPanel();

      timeIntervalControlPanel.setLayout(new GridLayout(1, 2));

      Integer[] intervals = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

      JComboBox<Integer> list = new JComboBox<Integer>(intervals);

      JLabel timeUnitLabel = new JLabel(timeUnitLabelText);

      timeIntervalControlPanel.add(list);

      timeIntervalControlPanel.add(timeUnitLabel);

      timeIntervalControlPanel.setBorder(BorderFactory.createTitledBorder(timeIntervalControlTitledBorderName));
      /*-------------------------End Of Time Interval Control Panel------------------------*/

      /*-----------------------------Dictionary Control Panel---------------------------*/

      JPanel dictionaryControlPanel = new JPanel();

      dictionaryControlPanel.setLayout(new GridLayout(1, 1));

      JButton chooseDictionaryButton = new JButton(chooseDictionaryButtonName);

      dictionaryControlPanel.add(chooseDictionaryButton);

      /*-------------------------End Of Dictionary Control Panel------------------------*/

      /*-------------------------------Settings Control Panel-------------------------------*/
      JPanel settingsPanel = new JPanel();

      settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

      settingsPanel.add(passControlPanel);

      settingsPanel.add(translationDirectionControlPanel);

      settingsPanel.add(listConfigurationControlPanel);

      settingsPanel.add(shuffleControlPanel);

      settingsPanel.add(timeIntervalControlPanel);

      settingsPanel.add(dictionaryControlPanel);

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

      mainFrame.setLayout(new BorderLayout());

      mainFrame.add(wordLabelControlPanel, BorderLayout.CENTER);

      mainFrame.add(settingsPanel, BorderLayout.EAST);

      mainFrame.add(buttonPanel, BorderLayout.SOUTH);

      mainFrame.pack();

      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      mainFrame.setSize(900, 500);

      mainFrame.setResizable(false);

      mainFrame.setLocationRelativeTo(null);

      mainFrame.setVisible(true);
   }

   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            @SuppressWarnings("unused")
            final Cards cards = new Cards();
         }
      });
   }
}