package ua.com.hedgehogsoft.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 * The class <code>Dictionary</code> emulates the dictionary.
 * <p>
 * Imagine it like a separate dictionary-book and its blocks like the sections
 * of this book.
 * 
 * @author Andrii Vynnyk
 * @see Block
 * @since 0.1
 * 
 */
public class Dictionary
{
   private String name;
   private Map<String, Block> blocks;
   private static final Logger logger = Logger.getLogger(Dictionary.class);

   /**
    * Creates a new dictionary.
    * 
    * @param name
    *           Unique name for dictionary.
    */
   public Dictionary(String name)
   {
      this.name = name;

      blocks = new LinkedHashMap<String, Block>();
   }

   /**
    * Creates a new dictionary.
    * 
    * @param name
    *           Unique name for the dictionary.
    * @param blocks
    *           Sections of the dictionary.
    */
   public Dictionary(String name, Map<String, Block> blocks)
   {
      this.name = name;

      this.blocks = new LinkedHashMap<String, Block>();

      this.blocks.putAll(blocks);
   }

   /**
    * Creates and adds new section to dictionary with existing words.
    * 
    * @param blockName
    *           Unique name for a section inside the dictionary.
    * @param words
    *           Words for new section.
    */
   public void addBlock(String blockName, Map<String, Word> words)
   {
      if (blocks.containsKey(blockName))
      {
         logger.error("Block [" + blockName + "] wasn't added to dictionary [" + name
               + "]. Block with such name already exists");
      }
      else
      {
         blocks.put(blockName, new Block(blockName, this, words));

         logger.info("New block [" + blockName + "] was added to dictionary [" + name + "].");
      }
   }

   /**
    * Adds an existing block to the current dictionary.
    * 
    * @param block
    *           An existing block
    */
   public void addBlock(Block block)
   {
      if (blocks.containsKey(block.getName()))
      {
         logger.error("Block [" + block.getName() + "] wasn't added to dictionary [" + name
               + "]. Block with such name already exists.");
      }
      else
      {
         blocks.put(block.getName(), block);

         logger.info("New block [" + block.getName() + "] was added to dictionary [" + name + "].");
      }
   }

   /**
    * Returns the block with specified name.
    * 
    * @param blockName
    *           Name of dictionary's section.
    * @return the block with specified name.
    */
   public Block getBlock(String blockName)
   {
      if (blocks.containsKey(blockName))
      {
         return blocks.get(blockName);
      }
      else
      {
         logger.error("Block [" + blockName + "] wasn't found in the dictionary [" + name + "].");

         return null;
      }
   }

   /**
    * Get all sections from current dictionary.
    * 
    * @return all block from current dictionary.
    */
   public List<Block> getBlocks()
   {
      List<Block> blocks = new ArrayList<Block>();

      for (Map.Entry<String, Block> block : this.blocks.entrySet())
      {
         blocks.add(block.getValue());
      }

      return blocks;
   }

   /**
    * Get all words in the same order as they was added to the current
    * dictionary.
    * 
    * @return All words from the current dictionary.
    */
   public List<Word> getWords()
   {
      List<Word> words = new ArrayList<Word>();

      for (Map.Entry<String, Block> block : blocks.entrySet())
      {
         for (Word word : block.getValue().getWords())
         {
            words.add(word);
         }
      }

      return words;
   }

   /**
    * Get a name of the current dictionary.
    * 
    * @return a name of the current dictionary.
    */
   public String getName()
   {
      return name;
   }
}
