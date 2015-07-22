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

   /**
    * Creates a new word with its value and translation.
    * 
    * @param block
    *           Reference to the parent section of the dictionary.
    * @param value
    *           Value of new word.
    * @param translation
    *           Translation of new word
    */
   public Word(Block block, String value, String translation)
   {
      this.block = block;
      this.value = value;
      this.translation = translation;
   }

   /**
    * Get a value of the word.
    * 
    * @return Value of the word.
    */
   public String getValue()
   {
      return value;
   }

   /**
    * Get a translation of the word.
    * 
    * @return Translation of the word.
    */
   public String getTranslation()
   {
      return translation;
   }

   /**
    * Get the parent block of the dictionary.
    * 
    * @return Reference to the parent section of the dictionary.
    */
   public Block getBlock()
   {
      return block;
   }
}
