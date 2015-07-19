package ua.com.hedgehogsoft.listener;

import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import ua.com.hedgehogsoft.Labels;
import ua.com.hedgehogsoft.view.MainFrame;
import ua.com.hedgehogsoft.view.group.CheckBoxGroup;

public class ChooseBlocksDictionaryAction implements ActionListener, Labels
{
   private MainFrame mainFrame = null;

   public ChooseBlocksDictionaryAction(MainFrame mainFrame)
   {
      this.mainFrame = mainFrame;
   }

   @Override
   public void actionPerformed(ActionEvent e)
   {
      CheckBoxGroup blocksGroup = new CheckBoxGroup();

      int count = mainFrame.getDictionary().getBlockNames().size();

      blocksGroup.setLayout(new GridLayout(count, 1));

      for (String name : mainFrame.getDictionary().getBlockNames())
      {
         blocksGroup.add(new JCheckBox(name));
      }

      JButton okay = new JButton(okButtonName);

      okay.addActionListener(new ActionListener()
      {
         @Override
         public void actionPerformed(ActionEvent e)
         {
            if (blocksGroup.getSelectedItems().size() > 0)
            {
               Map<String, String> words = new LinkedHashMap<String, String>();

               for (String selection : blocksGroup.getSelectedItems())
               {
                  words.putAll(mainFrame.getDictionary().getBlockWords(selection));
               }

               mainFrame.setWords(words);
            }
            Window w = SwingUtilities.getWindowAncestor((JButton) e.getSource());

            if (w != null)
            {
               w.dispose();
            }
         }
      });

      JOptionPane.showOptionDialog(null,
                                   new JScrollPane(blocksGroup),
                                   chooseBlockDictionaryWindowName,
                                   JOptionPane.OK_OPTION,
                                   JOptionPane.QUESTION_MESSAGE,
                                   null,
                                   new Object[] { okay },
                                   okay);
   }
}
