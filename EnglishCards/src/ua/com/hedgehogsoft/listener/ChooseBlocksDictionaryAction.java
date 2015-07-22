package ua.com.hedgehogsoft.listener;

import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import ua.com.hedgehogsoft.Labels;
import ua.com.hedgehogsoft.model.Block;
import ua.com.hedgehogsoft.model.Dictionary;
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

      int count = getBlockNames(mainFrame.getDictionary()).size();

      blocksGroup.setLayout(new GridLayout(count, 1));

      for (String name : getBlockNames(mainFrame.getDictionary()))
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
               Dictionary dictionary = new Dictionary(mainFrame.getDictionary().getName());

               for (String selection : blocksGroup.getSelectedItems())
               {
                  dictionary.addBlock(mainFrame.getDictionary().getBlock(selection));
               }

               mainFrame.setDictionary(dictionary);
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
   
   private List<String> getBlockNames(Dictionary dictionary)
   {
      List<String> names = new ArrayList<String>(dictionary.getBlocks().size());

      for (Block block : dictionary.getBlocks())
      {
         names.add(block.getName());
      }

      Collections.sort(names);

      return names;
   }
}
