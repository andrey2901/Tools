package ua.com.hedgehogsoft;

import ua.com.hedgehogsoft.provider.Provider;
import ua.com.hedgehogsoft.provider.ProviderType;
import ua.com.hedgehogsoft.provider.impl.PlainTextProvider;

public class ProviderManager
{
   private Provider provider = null;

   public ProviderManager()
   {
      provider = new PlainTextProvider();
   }

   public ProviderManager(ProviderType type)
   {
      if (type == ProviderType.SIMPLE)
      {
         provider = new PlainTextProvider();
      }
   }

   public Provider getProvider()
   {
      provider = new PlainTextProvider();

      return provider;
   }

   public Provider getProvider(String path)
   {
      provider = new PlainTextProvider(path);

      return provider;
   }
}
