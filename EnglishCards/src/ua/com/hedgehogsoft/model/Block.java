package ua.com.hedgehogsoft.model;

import java.util.LinkedHashMap;
import java.util.Map;

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

   /**
    * Creates a new dictionary's section.
    * 
    * @param name
    *           Unique name for block inside the dictionary.
    */
   public Block(String name)
   {
      this.name = name;

      words = new LinkedHashMap<String, Word>();
   }

   /**
    * Creates a new dictionary's section.
    * 
    * @param name
    *           Unique name for block inside the dictionary.
    * @param words
    *           Words from dictionary's section.
    */
   public Block(String name, Map<String, Word> words)
   {
      this.name = name;

      this.words = new LinkedHashMap<String, Word>();

      this.words.putAll(words);
   }

   public void addWord(String value, String translation)
   {
      words.put(value, new Word(this, value, translation));
   }

   public void addWord(Word word)
   {
      words.put(word.getValue(), word);
   }

   public void addWords(Map<String, Word> words)
   {
      this.words.putAll(words);
   }

   public Word getWord(String word)
   {
      return words.get(word);
   }

   public Map<String, Word> getWords()
   {
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
}
