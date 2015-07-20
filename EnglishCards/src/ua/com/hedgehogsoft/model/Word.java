package ua.com.hedgehogsoft.model;

/**
 * 
 * The class <code>Word</code> emulates the dictionary's unit that contains the
 * word with its translation.
 * 
 * @author Andrii Vynnyk
 * @see Dictionary
 * @see Word
 * @since 0.1
 * 
 */
public class Word
{
   private String value;
   private String translation;
   private Block block;

   public Word(Block block, String value, String translation)
   {
      this.block = block;
      this.value = value;
      this.translation = translation;
   }

   public String getValue()
   {
      return value;
   }

   public String getTranslation()
   {
      return translation;
   }

   public Block getBlock()
   {
      return block;
   }
}
