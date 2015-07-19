package ua.com.hedgehogsoft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ua.com.hedgehogsoft.provider.IProvider;

public class Dictionary
{
   private Map<String, Map<String, String>> blocks;

   public Dictionary(IProvider provider)
   {
      blocks = provider.getWords();
   }

   public Map<String, String> getBlockWords(String blockName)
   {
      return blocks.get(blockName);
   }

   public Map<String, String> getAllWords()
   {
      Map<String, String> result = new LinkedHashMap<String, String>();

      for (Entry<String, Map<String, String>> entry : blocks.entrySet())
      {
         result.putAll(entry.getValue());
      }

      return result;
   }

   public List<String> getBlockNames()
   {
      List<String> names = new ArrayList<String>(blocks.size());

      for (String name : blocks.keySet())
      {
         names.add(name);
      }

      Collections.sort(names);

      return names;
   }
}
