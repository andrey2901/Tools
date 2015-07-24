package ua.com.hedgehogsoft.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JRadioButton;

import ua.com.hedgehogsoft.model.enums.SortType;
import ua.com.hedgehogsoft.view.MainFrame;
import ua.com.hedgehogsoft.view.group.SortControlPanel;

public class SortAction implements ActionListener
{
   public SortAction(MainFrame mainFrame)
   {
      this.mainFrame = mainFrame;
   }

   private MainFrame mainFrame = null;

   @Override
   public void actionPerformed(ActionEvent e)
   {
      JRadioButton b = ((SortControlPanel) ((JButton) e.getSource()).getParent()).getSelected();

      SortType type = SortType.valueOf(b.getText().toUpperCase());

      mainFrame.getDictionary().sort(type);
   }
}
