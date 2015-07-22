package ua.com.hedgehogsoft.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
   private String name;
   private Map<String, Word> words;
   private Dictionary dictionary;
   private static final Logger logger = Logger.getLogger(Block.class);

   /**
    * Creates a new dictionary's section.
    * 
    * @param name
    *           Unique name for block inside the dictionary.
    * @param dictionary
    *           Parent dictionary
    */
   public Block(String name, Dictionary dictionary)
   {
      this.name = name;

      this.dictionary = dictionary;

      words = new LinkedHashMap<String, Word>();
   }

   /**
    * Creates a new dictionary's section.
    * 
    * @param name
    *           Unique name for block inside the dictionary.
    * @param dictionary
    *           Parent dictionary
    * @param words
    *           Words from dictionary's section that will be added.
    */
   public Block(String name, Dictionary dictionary, Map<String, Word> words)
   {
      this.name = name;

      this.dictionary = dictionary;

      this.words = new LinkedHashMap<String, Word>();

      this.words.putAll(words);
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
      if (words.containsKey(value))
      {
         logger.error("Word [" + value + "] wasn't added to block [" + this.name + "] from dictionary ["
               + this.dictionary.getName() + "]. Such word already exists");
      }
      else
      {
         words.put(value, new Word(this, value, translation));

         logger.info("New word [" + value + "] was added to block [" + this.name + "] from dictionary ["
               + this.dictionary.getName() + "].");
      }
   }

   /**
    * Adds an existing word to the current dictionary's section.
    * 
    * @param word
    *           An existing word
    */
   public void addWord(Word word)
   {
      if (words.containsKey(word.getValue()))
      {
         logger.error("Word [" + word.getValue() + "] wasn't added to block [" + this.name + "] from dictionary ["
               + this.dictionary.getName() + "]. Such word already exists");
      }
      else
      {
         words.put(word.getValue(), word);

         logger.info("New word [" + word.getValue() + "] was added to block [" + this.name + "] from dictionary ["
               + this.dictionary.getName() + "].");
      }
   }

   /**
    * Adds collection of words to the current block.
    * 
    * @param words
    *           Collection of words for adding.
    */
   public void addWords(Map<String, Word> words)
   {
      for (Map.Entry<String, Word> word : words.entrySet())
      {
         addWord(word.getValue());
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
      return words.get(word);
   }

   /**
    * Get list of the words in the same order as they was added to the current
    * block.
    * 
    * @return All words from the current block.
    */
   public List<Word> getWords()
   {
      List<Word> words = new ArrayList<Word>();

      for (Map.Entry<String, Word> word : this.words.entrySet())
      {
         words.add(word.getValue());
      }

      return words;
   }

   /**
    * Get a name of the current block.
    * 
    * @return a name of the current block.
    */
   public String getName()
   {
      return name;
   }

   /**
    * Get the parent dictionary for the current block.
    * 
    * @return Parent dictionary for the current block.
    */
   public Dictionary getDictionary()
   {
      return dictionary;
   }
}
