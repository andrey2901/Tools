package ua.com.hedgehogsoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.Dictionary;
import ua.com.hedgehogsoft.Labels;
import ua.com.hedgehogsoft.ProviderManager;
import ua.com.hedgehogsoft.button.ExitButton;
import ua.com.hedgehogsoft.button.NextButton;
import ua.com.hedgehogsoft.button.StartButton;
import ua.com.hedgehogsoft.button.StopButton;
import ua.com.hedgehogsoft.button.TranslateButton;
import ua.com.hedgehogsoft.listener.ChooseBlocksDictionaryAction;
import ua.com.hedgehogsoft.listener.ChooseDictionaryAction;
import ua.com.hedgehogsoft.listener.ExitAction;
import ua.com.hedgehogsoft.listener.StartAction;
import ua.com.hedgehogsoft.listener.StopAction;
import ua.com.hedgehogsoft.task.StopTaskMessage;
import ua.com.hedgehogsoft.view.group.AbstractRadioButtonGroupPanel;
import ua.com.hedgehogsoft.view.group.ListConfigurationControlPanel;
import ua.com.hedgehogsoft.view.group.PassControlPanel;
import ua.com.hedgehogsoft.view.group.ShuffleControlPanel;
import ua.com.hedgehogsoft.view.group.TranslationDirectionControlPanel;

public class MainFrame extends JFrame implements Labels
{
   private static final long serialVersionUID = 1L;
   private Dictionary dictionary = null;
   private Map<String, String> words = null;
   private JLabel wordLabel = null;
   private JLabel blockLabel = null;
   private JProgressBar prgBar = null;
   private JButton startButton = null;
   private JButton stopButton = null;
   private JButton exitButton = null;
   private JButton startExamButton = null;
   private JButton nextExamButton = null;
   private JButton translateExamButton = null;
   private JButton stopExamButton = null;
   private JComboBox<Integer> listIntervals = null;
   private JCheckBox examModeButton = null;
   private StopTaskMessage stopMessage = null;
   private AbstractRadioButtonGroupPanel passControlPanel = null;
   private AbstractRadioButtonGroupPanel translationDirectionControlPanel = null;
   private AbstractRadioButtonGroupPanel listConfigurationControlPanel = null;
   private AbstractRadioButtonGroupPanel shuffleControlPanel = null;

   public MainFrame(String title)
   {
      super(title);

      dictionary = new Dictionary(new ProviderManager().getProvider());

      words = dictionary.getAllBlocks();

      final JFrame mainFrame = new JFrame(mainFrameTitle);

      /*-------------------------------Word Label Panel-------------------------------*/
      JPanel wordLabelControlPanel = new JPanel();

      wordLabelControlPanel.setLayout(new BorderLayout());

      wordLabel = new JLabel("", JLabel.CENTER);

      blockLabel = new JLabel("Block: ", JLabel.CENTER);

      blockLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

      prgBar = new JProgressBar();

      prgBar.setSize(wordLabelControlPanel.getWidth(), 30);

      prgBar.setStringPainted(true);

      JPanel wordsPanel = new JPanel(new BorderLayout());

      wordsPanel.add(blockLabel, BorderLayout.NORTH);

      wordsPanel.add(wordLabel, BorderLayout.CENTER);

      wordLabelControlPanel.add(wordsPanel, BorderLayout.CENTER);

      wordLabelControlPanel.add(prgBar, BorderLayout.SOUTH);
      /*----------------------------End Of Word Label Panel----------------------------*/

      /*----------------------------Buttons initialization-----------------------------*/
      startButton = new StartButton(startButtonName, this);

      startButton.addActionListener(new StartAction(this, null));

      stopButton = new StopButton(stopButtonName);

      stopButton.addActionListener(new StopAction(this));

      exitButton = new ExitButton(exitButtonName);

      exitButton.addActionListener(new ExitAction());

      startExamButton = new StartButton("Start", null);

      startExamButton.setVisible(false);

      nextExamButton = new NextButton("Next");

      nextExamButton.setVisible(false);

      translateExamButton = new TranslateButton("Translate");

      translateExamButton.setVisible(false);

      stopExamButton = new StopButton("Stop");

      stopExamButton.setVisible(false);
      /*-------------------------End Of Buttons initialization--------------------------*/

      /*----------------------------Stop Task Message initialization-----------------------------*/
      stopMessage = new StopTaskMessage();

      stopMessage.addObserver((Observer) startButton);
      /*-------------------------End Of Stop Task Message initialization--------------------------*/

      /*-------------------------------Pass Control Panel-------------------------------*/
      passControlPanel = new PassControlPanel();
      /*----------------------------End Of Pass Control Panel----------------------------*/

      /*------------------------Translation Direction Control Panel------------------------*/
      translationDirectionControlPanel = new TranslationDirectionControlPanel();
      /*---------------------End Of Translation Direction Control Panel---------------------*/

      /*------------------------List Configuration Control Panel------------------------*/
      listConfigurationControlPanel = new ListConfigurationControlPanel();
      /*---------------------End Of List Configuration Control Panel--------------------*/

      /*-------------------------------Shuffle Control Panel-------------------------------*/
      shuffleControlPanel = new ShuffleControlPanel();
      /*----------------------------End Of Shuffle Control Panel---------------------------*/

      /*----------------------------Time Interval Control Panel----------------------------*/
      JPanel timeIntervalControlPanel = new JPanel();

      timeIntervalControlPanel.setLayout(new GridLayout(1, 2));

      Integer[] intervals = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

      listIntervals = new JComboBox<Integer>(intervals);

      JLabel timeUnitLabel = new JLabel(timeUnitLabelText);

      timeIntervalControlPanel.add(listIntervals);

      timeIntervalControlPanel.add(timeUnitLabel);

      timeIntervalControlPanel.setBorder(BorderFactory.createTitledBorder(timeIntervalControlTitledBorderName));
      /*-------------------------End Of Time Interval Control Panel------------------------*/

      /*-----------------------------Dictionary Control Panel---------------------------*/

      JPanel dictionaryControlPanel = new JPanel();

      dictionaryControlPanel.setLayout(new GridLayout(2, 1));

      JButton chooseDictionaryButton = new JButton(chooseDictionaryButtonName);

      dictionaryControlPanel.add(chooseDictionaryButton);

      chooseDictionaryButton.addActionListener(new ChooseDictionaryAction(this));

      JButton blocksButton = new JButton(chooseBlockDictionaryButtonName);

      dictionaryControlPanel.add(blocksButton);

      blocksButton.addActionListener(new ChooseBlocksDictionaryAction(this));

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

      /*-------------------------------Exam Mode Button----------------------------------*/
      examModeButton = new JCheckBox("Exam Mode");

      examModeButton.addItemListener(new ItemListener()
      {
         @Override
         public void itemStateChanged(ItemEvent e)
         {
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
               passControlPanel.setExamMode();

               translationDirectionControlPanel.setExamMode();

               listConfigurationControlPanel.setExamMode();

               shuffleControlPanel.setExamMode();

               startExamButton.setVisible(true);

               nextExamButton.setVisible(true);

               translateExamButton.setVisible(true);

               stopExamButton.setVisible(true);

               startButton.setVisible(false);

               stopButton.setVisible(false);
            }
            if (e.getStateChange() == ItemEvent.DESELECTED)
            {
               passControlPanel.unsetExamMode();

               translationDirectionControlPanel.unsetExamMode();

               listConfigurationControlPanel.unsetExamMode();

               shuffleControlPanel.unsetExamMode();

               startExamButton.setVisible(false);

               nextExamButton.setVisible(false);

               translateExamButton.setVisible(false);

               stopExamButton.setVisible(false);

               startButton.setVisible(true);

               stopButton.setVisible(true);
            }
         }
      });

      /*-----------------------------End Of Exam Mode Button-----------------------------*/

      /*-------------------------------Button Control Panel-------------------------------*/
      JPanel buttonsPanel = new JPanel();

      buttonsPanel.setLayout(new BorderLayout());

      buttonsPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.black));

      JPanel functionButtonPanel = new JPanel();

      functionButtonPanel.setLayout(new BorderLayout());

      JPanel startStopButtonPanel = new JPanel();

      startStopButtonPanel.add(startButton);

      startStopButtonPanel.add(stopButton);

      startStopButtonPanel.add(nextExamButton);

      startStopButtonPanel.add(translateExamButton);

      functionButtonPanel.add(startStopButtonPanel, BorderLayout.NORTH);

      JPanel exitButtonPanel = new JPanel();

      exitButtonPanel.add(startExamButton);

      exitButtonPanel.add(stopExamButton);

      exitButtonPanel.add(exitButton);

      functionButtonPanel.add(exitButtonPanel, BorderLayout.SOUTH);

      buttonsPanel.add(functionButtonPanel, BorderLayout.CENTER);

      buttonsPanel.add(examModeButton, BorderLayout.EAST);
      /*----------------------------End Of Button Control Panel----------------------------*/

      mainFrame.setLayout(new BorderLayout());

      mainFrame.add(wordLabelControlPanel, BorderLayout.CENTER);

      mainFrame.add(settingsPanel, BorderLayout.EAST);

      mainFrame.add(buttonsPanel, BorderLayout.SOUTH);

      mainFrame.pack();

      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      mainFrame.setSize(900, 534);

      mainFrame.setResizable(false);

      mainFrame.setLocationRelativeTo(null);

      mainFrame.setVisible(true);
   }

   public JLabel getWordLabel()
   {
      return wordLabel;
   }

   public JProgressBar getPrgBar()
   {
      return prgBar;
   }

   public JComponent[] getSettingComponents()
   {
      return new JComponent[] { passControlPanel.getSelected(), translationDirectionControlPanel.getSelected(),
            listConfigurationControlPanel.getSelected(), shuffleControlPanel.getSelected(), listIntervals };
   }

   public StopTaskMessage getStopMessage()
   {
      return stopMessage;
   }

   public Dictionary getDictionary()
   {
      return dictionary;
   }

   public void setDictionary(Dictionary dictionary)
   {
      this.dictionary = dictionary;
   }

   public Map<String, String> getWords()
   {
      return words;
   }

   public void setWords(Map<String, String> words)
   {
      this.words = words;
   }
}
