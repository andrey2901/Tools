package ua.com.hedgehogsoft.model;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * 
 * The class <code>Block</code> emulates the dictionary's sections with words.
 * 
 * @author Andrii Vynnyk
 * @see Dictionary
 * @see Word
 * @since 0.1
 * 
 */
public class Block
{
   private String name = null;
   private List<Word> words = null;
   private Dictionary dictionary = null;
   private int index;
   private static final Logger logger = Logger.getLogger(Block.class);

   /**
    * Creates a new dictionary's section.
    * 
    * @param name
    *           Unique name for block inside the dictionary.
    */
   public Block(String name)
   {
      this.name = name;

      words = new ArrayList<Word>();
   }

   /**
    * Creates a new dictionary's section.
    * 
    * @param name
    *           Unique name for block inside the dictionary.
    * @param words
    *           Words from dictionary's section that will be added.
    */
   public Block(String name, List<Word> words)
   {
      this.name = name;

      this.words = new ArrayList<Word>();

      addWords(words);
   }

   /**
    * Creates and adds new word to current dictionary's block.
    * 
    * @param value
    *           Value of new word.
    * @param translation
    *           Its translation.
    */
   public void addWord(String value, String translation)
   {
      Word word = new Word(value, translation);

      addWord(word);
   }

   /**
    * Adds an existing word to the current dictionary's section.
    * 
    * @param word
    *           An existing word
    */
   public void addWord(Word word)
   {
      if (words.contains(word))
      {
         logger.error("Word [" + word.getValue() + "] wasn't added to block [" + this.name + "] from dictionary ["
               + this.dictionary.getName() + "]. Such word already exists");
      }
      else
      {
         word.setBlock(this);

         word.setIndex(size());

         words.add(word);

         logger.trace("New word [" + word.getValue() + "] was added to block [" + this.name + "] from dictionary ["
               + this.dictionary.getName() + "].");
      }
   }

   /**
    * Adds collection of words to the current block.
    * 
    * @param words
    *           Collection of words for adding.
    */
   public void addWords(List<Word> words)
   {
      for (Word word : words)
      {
         addWord(word);
      }
   }

   /**
    * Returns the word with specified value.
    * 
    * @param word
    *           Value of required word.
    * @return Required word with specified name.
    */
   public Word getWord(String word)
   {
      Word result = null;

      for (Word wrd : words)
      {
         if (wrd.getValue() == word)
         {
            result = wrd;

            break;
         }
      }

      if (result == null)
      {
         logger.error("Word [" + word + "] wasn't found in the block [" + name + "] from the dictionary["
               + dictionary.getName() + "].");
      }

      return result;
   }

   /**
    * Returns list of the words in the same order as they was added to the
    * current block.
    * 
    * @return All words from the current block.
    */
   public List<Word> getWords()
   {
      return words;
   }

   /**
    * Returns a name of the current block.
    * 
    * @return a name of the current block.
    */
   public String getName()
   {
      return name;
   }

   /**
    * Returns an index of the current block inside the dictionary.
    * 
    * @return An index of the current block.
    */

   public int getIndex()
   {
      return index;
   }

   /**
    * Sets an index of the current block inside the dictionary.
    * 
    * @param index
    *           An index of the current block.
    */
   public void setIndex(int index)
   {
      this.index = index;
   }

   /**
    * Returns the parent dictionary for the current block.
    * 
    * @return Parent dictionary for the current block.
    */
   public Dictionary getDictionary()
   {
      return dictionary;
   }

   /**
    * Sets the reference to parent dictionary
    * 
    * @param dictionary
    *           Parent dictionary
    */
   public void setDictionary(Dictionary dictionary)
   {
      this.dictionary = dictionary;
   }

   /**
    * Returns the amount of words in the current block.
    * 
    * @return The amount of words in the current block.
    */
   public int size()
   {
      return words.size();
   }
}
