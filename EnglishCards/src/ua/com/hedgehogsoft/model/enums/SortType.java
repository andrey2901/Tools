package ua.com.hedgehogsoft.model.enums;

/**
 * 
 * The class <code>SortType</code> defines an enumeration that is used for
 * dictionary's sorting.
 * 
 * @author Andrii Vynnyk
 * @since 0.1
 * 
 */
public enum SortType
{
   /**
    * The sorting constant used to specify the initial order (the same order in
    * which the words were added to a dictionary).
    */
   INITIAL,

   /**
    * The sorting constant used to specify the alphabetic order inside a
    * dictionary.
    * 
    */
   ALPHABETIC,

   /**
    * The sorting constant used to specify the alphabetic order for translation
    * values.
    * 
    */
   TRANSLATION;
}
