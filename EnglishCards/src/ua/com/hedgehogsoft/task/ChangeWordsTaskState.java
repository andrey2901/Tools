package ua.com.hedgehogsoft.task;

import java.util.List;

public class ChangeWordsTaskState
{
   private List<String> keys = null;
   private String word = null;
   private int counter = 0;

   public ChangeWordsTaskState(List<String> keys, String word, int counter)
   {
      this.keys = keys;
      this.word = word;
      this.counter = counter;
   }

   public List<String> getKeys()
   {
      return keys;
   }

   public String getWord()
   {
      return word;
   }

   public int getCounter()
   {
      return counter;
   }
}