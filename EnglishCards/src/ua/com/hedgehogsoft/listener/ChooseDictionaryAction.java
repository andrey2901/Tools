package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import ua.com.hedgehogsoft.Dictionary;
import ua.com.hedgehogsoft.ProviderManager;
import ua.com.hedgehogsoft.view.MainFrame;

public class ChooseDictionaryAction implements ActionListener
{
   private MainFrame mainFrame = null;

   public ChooseDictionaryAction(MainFrame mainFrame)
   {
      this.mainFrame = mainFrame;
   }

   JFileChooser fc = new JFileChooser();

   FileFilter filter = new FileNameExtensionFilter("TXT file", new String[] { "txt", "TXT" });

   @Override
   public void actionPerformed(ActionEvent e)
   {
      fc.setFileFilter(filter);

      int returnVal = fc.showOpenDialog(null);

      if (returnVal == JFileChooser.APPROVE_OPTION)
      {
         File file = fc.getSelectedFile();

         Dictionary dictionary = new Dictionary(new ProviderManager().getProvider(file.getAbsolutePath()));

         mainFrame.setDictionary(dictionary);

         Map<String, String> words = dictionary.getAllBlocks();

         mainFrame.setWords(words);
      }
   }
}
