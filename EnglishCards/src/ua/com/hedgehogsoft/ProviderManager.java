package ua.com.hedgehogsoft;

import ua.com.hedgehogsoft.provider.IProvider;
import ua.com.hedgehogsoft.provider.ProviderType;
import ua.com.hedgehogsoft.provider.impl.PlainTextProvider;

public class ProviderManager
{
   private IProvider provider = null;

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

   public IProvider getProvider()
   {
      return provider;
   }

   public IProvider getProvider(String path)
   {
      provider = new PlainTextProvider(path);

      return provider;
   }
}
