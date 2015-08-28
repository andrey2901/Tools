package ua.com.hedgehogsoft.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import ua.com.hedgehogsoft.model.Dictionary;
import ua.com.hedgehogsoft.model.enums.SortType;
import ua.com.hedgehogsoft.props.Properties;
import ua.com.hedgehogsoft.button.BlocksButton;
import ua.com.hedgehogsoft.button.ChooseDictionaryButton;
import ua.com.hedgehogsoft.button.ExitButton;
import ua.com.hedgehogsoft.button.NextButton;
import ua.com.hedgehogsoft.button.SortButton;
import ua.com.hedgehogsoft.button.StartButton;
import ua.com.hedgehogsoft.button.StopButton;
import ua.com.hedgehogsoft.button.TranslateButton;
import ua.com.hedgehogsoft.io.DictionaryManager;
import ua.com.hedgehogsoft.io.reader.impl.PlainTextDictionaryReader;
import ua.com.hedgehogsoft.listener.ChooseBlocksDictionaryAction;
import ua.com.hedgehogsoft.listener.ChooseDictionaryAction;
import ua.com.hedgehogsoft.listener.NextAction;
import ua.com.hedgehogsoft.listener.StartAction;
import ua.com.hedgehogsoft.listener.TranslateAction;
import ua.com.hedgehogsoft.task.StartTaskMessage;
import ua.com.hedgehogsoft.task.StopTaskMessage;
import ua.com.hedgehogsoft.view.group.AbstractRadioButtonGroupPanel;
import ua.com.hedgehogsoft.view.group.ListConfigurationControlPanel;
import ua.com.hedgehogsoft.view.group.PassControlPanel;
import ua.com.hedgehogsoft.view.group.ShuffleControlPanel;
import ua.com.hedgehogsoft.view.group.SortControlPanel;
import ua.com.hedgehogsoft.view.group.TranslationDirectionControlPanel;

public class MainFrame extends JFrame
{
   private static final long serialVersionUID = 1L;
   private Dictionary dictionary = null;
   private JLabel wordLabel = null;
   private JLabel blockLabel = null;
   private JProgressBar prgBar = null;
   private JComboBox<Integer> listIntervals = null;
   private StartTaskMessage startMessage = null;
   private StopTaskMessage stopMessage = null;
   private AbstractRadioButtonGroupPanel passControlPanel = null;
   private AbstractRadioButtonGroupPanel translationDirectionControlPanel = null;
   private AbstractRadioButtonGroupPanel listConfigurationControlPanel = null;
   private AbstractRadioButtonGroupPanel shuffleControlPanel = null;
   private AbstractRadioButtonGroupPanel sortControlPanel = null;

   public MainFrame(String title)
   {
      super(title);

      dictionary = DictionaryManager.getInstance().loadDictionary(new PlainTextDictionaryReader());

      final JFrame mainFrame = new JFrame(title);

      /*--------------------------------Word Label Panel--------------------------------*/
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
      /*----------------------------End Of Word Label Panel-----------------------------*/

      /*-----------------------------Buttons initialization-----------------------------*/
      StartButton startButton = new StartButton(Properties.getProperty("cards.button.start.text"), this);

      startButton.addActionListener(new StartAction(this));

      NextButton nextButton = new NextButton(Properties.getProperty("cards.button.next.text"), startButton);

      nextButton.addActionListener(new NextAction(this));

      nextButton.setEnabled(false);

      TranslateButton translateButton = new TranslateButton(Properties.getProperty("cards.button.translate.text"),
            startButton);

      translateButton.addActionListener(new TranslateAction(this));

      translateButton.setEnabled(false);

      StopButton stopButton = new StopButton(Properties.getProperty("cards.button.stop.text"));

      stopButton.addActionListener(event ->
      {
         getStopMessage().send();
      });

      ExitButton exitButton = new ExitButton(Properties.getProperty("cards.button.exit.text"));

      exitButton.addActionListener(event ->
      {
         System.exit(0);
      });
      /*-------------------------End Of Buttons initialization--------------------------*/

      /*-------------------------------Pass Control Panel-------------------------------*/
      passControlPanel = new PassControlPanel();
      /*----------------------------End Of Pass Control Panel---------------------------*/

      /*-----------------------Translation Direction Control Panel----------------------*/
      translationDirectionControlPanel = new TranslationDirectionControlPanel();
      /*-------------------End Of Translation Direction Control Panel-------------------*/

      /*------------------------List Configuration Control Panel------------------------*/
      listConfigurationControlPanel = new ListConfigurationControlPanel();
      /*---------------------End Of List Configuration Control Panel--------------------*/

      /*------------------------------Shuffle Control Panel-----------------------------*/
      shuffleControlPanel = new ShuffleControlPanel();
      /*--------------------------End Of Shuffle Control Panel--------------------------*/

      /*-------------------------------Sort Control Panel-------------------------------*/
      sortControlPanel = new SortControlPanel();

      GridLayout layout = (GridLayout) sortControlPanel.getLayout();

      layout.setRows(layout.getRows() + 1);

      SortButton sortButton = new SortButton(Properties.getProperty("cards.button.sort.text"));

      sortButton.addActionListener(event ->
      {
         getDictionary().sort(SortType.valueOf(sortControlPanel.getSelected().getText().toUpperCase()));

         ((ShuffleControlPanel) shuffleControlPanel).setUnshuffleMode();
      });

      sortControlPanel.add(sortButton);
      /*-----------------------------End Of Sort Control Panel--------------------------*/

      /*----------------------------Time Interval Control Panel-------------------------*/
      JPanel timeIntervalControlPanel = new JPanel();

      timeIntervalControlPanel.setLayout(new GridLayout(1, 2));

      Integer[] intervals = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

      listIntervals = new JComboBox<Integer>(intervals);

      JLabel timeUnitLabel = new JLabel(Properties.getProperty("cards.time.unit.label"));

      timeIntervalControlPanel.add(listIntervals);

      timeIntervalControlPanel.add(timeUnitLabel);

      timeIntervalControlPanel
            .setBorder(BorderFactory.createTitledBorder(Properties.getProperty("cards.panel.time.interval.title")));
            /*------------------------End Of Time Interval Control Panel----------------------*/

      /*-----------------------------Dictionary Control Panel---------------------------*/

      JPanel dictionaryControlPanel = new JPanel();

      dictionaryControlPanel.setLayout(new GridLayout(2, 1));

      ChooseDictionaryButton chooseDictionaryButton = new ChooseDictionaryButton(
            Properties.getProperty("cards.button.choose.dictionary.text"));

      dictionaryControlPanel.add(chooseDictionaryButton);

      chooseDictionaryButton.addActionListener(new ChooseDictionaryAction(this));

      BlocksButton blocksButton = new BlocksButton(Properties.getProperty("cards.button.choose.blocks.text"));

      dictionaryControlPanel.add(blocksButton);

      blocksButton.addActionListener(new ChooseBlocksDictionaryAction(this));

      /*-------------------------End Of Dictionary Control Panel------------------------*/

      /*-----------------------------Settings Control Panel-----------------------------*/
      JPanel settingsPanel = new JPanel();

      settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));

      settingsPanel.add(passControlPanel);

      settingsPanel.add(translationDirectionControlPanel);

      settingsPanel.add(listConfigurationControlPanel);

      settingsPanel.add(shuffleControlPanel);

      settingsPanel.add(sortControlPanel);

      settingsPanel.add(timeIntervalControlPanel);

      settingsPanel.add(dictionaryControlPanel);

      settingsPanel.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, Color.black));
      /*--------------------------End Of Settings Control Panel-------------------------*/

      /*------------------------------Button Control Panel------------------------------*/
      JPanel buttonPanel = new JPanel();

      buttonPanel.setLayout(new BorderLayout());

      buttonPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.black));

      JPanel functionButtonPanel = new JPanel();

      functionButtonPanel.add(startButton, BorderLayout.WEST);

      functionButtonPanel.add(nextButton, BorderLayout.WEST);

      functionButtonPanel.add(translateButton, BorderLayout.WEST);

      functionButtonPanel.add(stopButton, BorderLayout.EAST);

      buttonPanel.add(functionButtonPanel, BorderLayout.NORTH);

      JPanel exitButtonPanel = new JPanel();

      exitButtonPanel.add(exitButton);

      buttonPanel.add(exitButtonPanel, BorderLayout.SOUTH);
      /*--------------------------End Of Button Control Panel---------------------------*/

      /*------------------Start and Stop Task Message initialization--------------------*/
      startMessage = new StartTaskMessage();

      startMessage.addObserver(chooseDictionaryButton);

      startMessage.addObserver(blocksButton);

      startMessage.addObserver(sortButton);

      startMessage.addObserver(nextButton);

      startMessage.addObserver(translateButton);

      stopMessage = new StopTaskMessage();

      stopMessage.addObserver(startButton);

      stopMessage.addObserver(chooseDictionaryButton);

      stopMessage.addObserver(blocksButton);

      stopMessage.addObserver(sortButton);

      stopMessage.addObserver(nextButton);

      stopMessage.addObserver(translateButton);
      /*----------------End Of Start and Stop Task Message initialization---------------*/

      mainFrame.setLayout(new BorderLayout());

      mainFrame.add(wordLabelControlPanel, BorderLayout.CENTER);

      mainFrame.add(settingsPanel, BorderLayout.EAST);

      mainFrame.add(buttonPanel, BorderLayout.SOUTH);

      mainFrame.pack();

      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      mainFrame.setSize(900, 661);

      mainFrame.setResizable(false);

      mainFrame.setLocationRelativeTo(null);

      mainFrame.setVisible(true);
   }

   public JLabel getWordLabel()
   {
      return wordLabel;
   }

   public JLabel getBlockLabel()
   {
      return blockLabel;
   }

   public JProgressBar getPrgBar()
   {
      return prgBar;
   }

   public JComponent[] getSettingComponents()
   {
      return new JComponent[] {passControlPanel.getSelected(),
                               translationDirectionControlPanel.getSelected(),
                               listConfigurationControlPanel.getSelected(),
                               shuffleControlPanel.getSelected(),
                               listIntervals};
   }

   public StartTaskMessage getStartMessage()
   {
      return startMessage;
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
}
