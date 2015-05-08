package ua.com.hedgehogsoft.provider.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ua.com.hedgehogsoft.provider.Provider;

public class PlainTextProvider implements Provider
{

   @Override
   public Map<String, String> getWords()
   {
      Map<String, String> dictionary = new HashMap<String, String>();
      try (BufferedReader br = new BufferedReader(new FileReader("classpath:../../../EnglishCards/resource/dictionary.txt")))
      {
         String currentLine = null;

         while ((currentLine = br.readLine()) != null)
         {
            String[] words = currentLine.split(":");
            dictionary.put(words[0], words[1]);
         }

      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      return dictionary;
   }

}
