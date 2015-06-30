package ua.com.hedgehogsoft.provider.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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
   public Map<String, String> getWords()
   {
      Map<String, String> dictionary = new HashMap<String, String>();
      try (BufferedReader br = new BufferedReader(new FileReader(path)))
      {
         String currentLine = null;

         while ((currentLine = br.readLine()) != null)
         {
            if (!currentLine.isEmpty())
            {
               String[] words = currentLine.split(":");
               dictionary.put(words[0], words[1]);
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
