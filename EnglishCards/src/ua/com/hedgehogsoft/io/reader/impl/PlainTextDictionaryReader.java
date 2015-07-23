package ua.com.hedgehogsoft.io.reader.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import ua.com.hedgehogsoft.io.reader.IDictionaryReader;
import ua.com.hedgehogsoft.model.Block;
import ua.com.hedgehogsoft.model.Dictionary;
import ua.com.hedgehogsoft.model.Word;

public class PlainTextDictionaryReader implements IDictionaryReader
{
   private String path = null;
   private static final Logger logger = Logger.getLogger(PlainTextDictionaryReader.class);

   public PlainTextDictionaryReader()
   {
      path = "classpath:../../../EnglishCards/resource/dictionary.txt";
   }

   public PlainTextDictionaryReader(String path)
   {
      this.path = path;
   }

   @Override
   public Dictionary getDictionary()
   {
      Dictionary dictionary = null;

      File f = null;

      try (BufferedReader br = new BufferedReader(new FileReader(f = new File(path))))
      {
         dictionary = new Dictionary(f.getName());

         String currentLine = null;

         Block block = null;

         while ((currentLine = br.readLine()) != null)
         {
            if (!currentLine.isEmpty())
            {
               if (currentLine.startsWith("[") && currentLine.endsWith("]"))
               {
                  block = new Block(currentLine.substring(1, currentLine.length() - 1));

                  dictionary.addBlock(block);
               }
               else if (currentLine.endsWith(";"))
               {
                  if (block == null)
                  {
                     block = new Block("Default");

                     dictionary.addBlock(block);
                  }

                  String[] words = currentLine.split(":");

                  Word word = new Word(words[0].trim(), (words[1].substring(0, words[1].length() - 1)).trim());

                  block.addWord(word);
               }
            }
         }
      }
      catch (FileNotFoundException e)
      {
         logger.error("File [" + path + "] wasn't found.", e);
      }
      catch (IOException e)
      {
         logger.error("I/O exception during the getting a file [" + path + "].", e);
      }
      return dictionary;
   }
}
