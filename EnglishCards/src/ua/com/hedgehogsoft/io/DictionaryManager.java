package ua.com.hedgehogsoft.io;

import java.util.ArrayList;
import java.util.List;

import ua.com.hedgehogsoft.io.reader.IDictionaryReader;
import ua.com.hedgehogsoft.model.Dictionary;

public class DictionaryManager
{
   List<Dictionary> dictionaries = null;
   static DictionaryManager manager = null;

   private DictionaryManager()
   {
      this.dictionaries = new ArrayList<Dictionary>();
   }

   public static DictionaryManager getInstance()
   {
      if (manager == null)
      {
         manager = new DictionaryManager();
      }

      return manager;
   }

   public Dictionary loadDictionary(IDictionaryReader reader)
   {
      Dictionary dictionary = reader.getDictionary();

      dictionaries.add(dictionary);

      return dictionary.copy();
   }

   public Dictionary getDictionary(String name)
   {
      Dictionary result = null;

      for (Dictionary dictionary : dictionaries)
      {
         if (dictionary.getName() == name)
         {
            result = dictionary;
            break;
         }
      }

      return result.copy();
   }
}
