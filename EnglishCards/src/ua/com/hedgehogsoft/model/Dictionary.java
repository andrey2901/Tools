package ua.com.hedgehogsoft.model;

import java.util.ArrayList;
import java.util.List;
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
   private String name = null;
   private List<Block> blocks = null;
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

      blocks = new ArrayList<Block>();
   }

   /**
    * Creates a new dictionary.
    * 
    * @param name
    *           Unique name for the dictionary.
    * @param blocks
    *           Sections of the dictionary.
    */
   public Dictionary(String name, List<Block> blocks)
   {
      this.name = name;

      this.blocks = new ArrayList<Block>();

      addBlocks(blocks);
   }

   /**
    * Creates and adds new section to dictionary with existing words.
    * 
    * @param blockName
    *           Unique name for a section inside the dictionary.
    * @param words
    *           Words for new section.
    */
   public void addBlock(String blockName, List<Word> words)
   {
      Block block = new Block(size(), blockName, this, words);

      addBlock(block);
   }

   /**
    * Adds an existing block to the current dictionary.
    * 
    * @param block
    *           An existing block
    */
   public void addBlock(Block block)
   {
      if (blocks.contains(block))
      {
         logger.error("Block [" + block.getName() + "] wasn't added to dictionary [" + name
               + "]. Block with such name already exists.");
      }
      else
      {
         block.setDictionary(this);

         block.setIndex(size());

         blocks.add(block);

         logger.info("New block [" + block.getName() + "] was added to dictionary [" + name + "].");
      }
   }

   /**
    * Adds collection of blocks to the current dictionary.
    * 
    * @param blocks
    *           Collection of blocks to the current dictionary.
    */
   public void addBlocks(List<Block> blocks)
   {
      for (Block block : blocks)
      {
         addBlock(block);
      }
   }

   /**
    * Returns the block with specified name.
    * 
    * @param blockName
    *           Name of dictionary's section.
    * @return The block with specified name.
    */
   public Block getBlock(String blockName)
   {
      Block result = null;

      for (Block block : blocks)
      {
         if (block.getName() == blockName)
         {
            result = block;

            break;
         }
      }

      if (result == null)
      {
         logger.error("Block [" + blockName + "] wasn't found in the dictionary [" + name + "].");
      }

      return result;
   }

   /**
    * Returns all sections from current dictionary.
    * 
    * @return All block from current dictionary.
    */
   public List<Block> getBlocks()
   {
      return blocks;
   }

   /**
    * Returns all words in the same order as they was added to the current
    * dictionary.
    * 
    * @return All words from the current dictionary.
    */
   public List<Word> getWords()
   {
      List<Word> words = new ArrayList<Word>();

      for (Block block : blocks)
      {
         for (Word word : block.getWords())
         {
            words.add(word);
         }
      }

      return words;
   }

   /**
    * Returns a copy of the current dictionary.
    * 
    * @return A copy of the current dictionary.
    */
   public Dictionary copy()
   {
      Dictionary copyDictionary = new Dictionary("copy_" + this.getName());

      for (Block block : this.getBlocks())
      {
         Block copyBlock = new Block(copyDictionary.size(), block.getName(), copyDictionary);

         for (Word word : block.getWords())
         {
            new Word(copyBlock.size(), new String(word.getValue()), new String(word.getTranslation()), copyBlock);
         }
      }

      return copyDictionary;
   }

   public void sort()
   {
   };

   public void shuffle()
   {
   };

   /**
    * Returns a name of the current dictionary.
    * 
    * @return A name of the current dictionary.
    */
   public String getName()
   {
      return name;
   }

   /**
    * Returns the amount of blocks in the current dictionary.
    * 
    * @return The amount of blocks in the current dictionary.
    */
   public int size()
   {
      return blocks.size();
   }
}
