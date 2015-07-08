package ua.com.hedgehogsoft.provider;

import java.util.Map;

public interface IProvider
{
   Map<String, Map<String, String>> getWords();
}
