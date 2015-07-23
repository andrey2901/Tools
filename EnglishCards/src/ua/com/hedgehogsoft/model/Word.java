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
   private int index;

   /**
    * Creates a new word with its value and translation.
    * 
    * @param index
    *           Ordinal number of the current word inside the dictionary's
    *           section.
    * @param value
    *           Value of new word.
    * @param translation
    *           Translation of new word
    * @param block
    *           Reference to the parent section of the dictionary.
    */
   public Word(int index, String value, String translation, Block block)
   {
      this.index = index;

      this.value = value;

      this.translation = translation;

      this.block = block;
   }

   /**
    * Returns an index of the current word inside dictionary's section.
    * 
    * @return An index of the current word.
    */
   public int getIndex()
   {
      return index;
   }

   /**
    * Sets an index of the current word inside dictionary's section.
    * 
    * @param index
    *           An index of the current word.
    */
   public void setIndex(int index)
   {
      this.index = index;
   }

   /**
    * Returns a value of the word.
    * 
    * @return Value of the word.
    */
   public String getValue()
   {
      return value;
   }

   /**
    * Returns a translation of the word.
    * 
    * @return Translation of the word.
    */
   public String getTranslation()
   {
      return translation;
   }

   /**
    * Returns the parent block of the dictionary.
    * 
    * @return Reference to the parent section of the dictionary.
    */
   public Block getBlock()
   {
      return block;
   }

   /**
    * Sets the parent block of the dictionary.
    * 
    * @param block
    *           Reference to the parent section of the dictionary.
    */
   public void setBlock(Block block)
   {
      this.block = block;
   }
}
