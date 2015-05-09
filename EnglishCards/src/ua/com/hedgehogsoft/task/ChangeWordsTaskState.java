package ua.com.hedgehogsoft.task;

import java.util.List;

public class ChangeWordsTaskState
{
   private List<String> keys = null;
   private String word = null;
   private int counter = 0;
   private double progressBarStep = 0.0;

   public ChangeWordsTaskState(List<String> keys, String word, int counter, double progressBarStep)
   {
      this.keys = keys;
      this.word = word;
      this.counter = counter;
      this.progressBarStep = progressBarStep;
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

   public double getProgressBarStep()
   {
      return progressBarStep;
   }
}