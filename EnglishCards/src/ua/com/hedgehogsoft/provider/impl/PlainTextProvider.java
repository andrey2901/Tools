package ua.com.hedgehogsoft.provider.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import ua.com.hedgehogsoft.provider.Provider;

public class PlainTextProvider implements Provider
{
   String path = null;

   public PlainTextProvider()
   {
      path = "classpath:../../../EnglishCards/resource/dictionary.txt";
   }

   public PlainTextProvider(String path)
   {
      this.path = path;
   }

   @Override
   public Map<String, Map<String, String>> getWords()
   {
      Map<String, Map<String, String>> dictionary = new LinkedHashMap<String, Map<String, String>>();

      Map<String, String> block = null;

      try (BufferedReader br = new BufferedReader(new FileReader(path)))
      {
         String currentLine = null;

         while ((currentLine = br.readLine()) != null)
         {
            if (!currentLine.isEmpty())
            {
               if (currentLine.startsWith("[") && currentLine.endsWith("]"))
               {
                  block = new LinkedHashMap<String, String>();

                  dictionary.put(currentLine.substring(1, currentLine.length() - 1), block);
               }
               else if (currentLine.endsWith(";"))
               {
                  if (block == null)
                  {
                     block = new LinkedHashMap<String, String>();

                     dictionary.put("Default", block);
                  }

                  String[] words = currentLine.split(":");

                  block.put(words[0], words[1].substring(0, words[1].length() - 1));
               }
            }
         }

      }

      catch (IOException e)
      {
         e.printStackTrace();
      }

      return dictionary;
   }
}
