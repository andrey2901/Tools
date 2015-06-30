package ua.com.hedgehogsoft.provider;

import java.util.Map;

public interface Provider
{
   Map<String, Map<String, String>> getWords();
}
