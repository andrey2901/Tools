package ua.com.hedgehogsoft.props;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Properties
{
   private static Properties props = null;
   private java.util.Properties properties = null;

   private Properties()
   {
      properties = new java.util.Properties();

      InputStream input = null;

      try
      {

         URL url = this.getClass().getClassLoader().getResource("config.properties");

         input = new FileInputStream(url.getPath());

         properties.load(input);

      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (IOException ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         if (input != null)
         {
            try
            {
               input.close();
            }
            catch (IOException e)
            {
               e.printStackTrace();
            }
         }
      }

   }

   static public String getProperty(String key)
   {
      if (props == null)
      {
         props = new Properties();
      }
      return props.properties.getProperty(key);
   }
}
