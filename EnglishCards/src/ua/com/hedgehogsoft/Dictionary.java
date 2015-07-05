package ua.com.hedgehogsoft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
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
      Map<String, String> result = new LinkedHashMap<String, String>();

      for (Entry<String, Map<String, String>> entry : words.entrySet())
      {
         result.putAll(entry.getValue());
      }

      return result;
   }

   public List<String> getBlockNames()
   {
      List<String> names = new ArrayList<String>(words.size());

      for (String name : words.keySet())
      {
         names.add(name);
      }

      Collections.sort(names);

      return names;
   }
}
