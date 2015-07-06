package ua.com.hedgehogsoft.view.group;

public class TranslationDirectionControlPanel extends AbstractRadioButtonGroupPanel
{
   private static final long serialVersionUID = 1L;

   public TranslationDirectionControlPanel()
   {
      super(directTranslationRadioButtonName, reverseTranslationRadioButtonName);

      setTitledBorder(translationDirectionControlTitledBorderName);
   }

   @Override
   void setExamMode()
   {

   }
}
