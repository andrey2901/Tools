package ua.com.hedgehogsoft;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import ua.com.hedgehogsoft.provider.Provider;

public class Dictionary
{
   private Map<String, Map<String, String>> words;

   public Dictionary(Provider provider)
   {
      words = provider.getWords();
   }

   public Map<String, String> getBlock(String blockName)
   {
      return words.get(blockName);
   }

   public Map<String, String> getAllBlocks()
   {
      Map<String, String> result = new HashMap<String, String>();

      for (Entry<String, Map<String, String>> entry : words.entrySet())
      {
         result.putAll(entry.getValue());
      }

      return result;
   }
}
