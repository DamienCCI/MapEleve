package fr.damienseyve.mapeleve;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class SerializationUtil {

     // deserialize to Object from given file
     public static Object deserialize(String fileName) {
         Object obj = null;
         try {
             FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis);
              obj = ois.readObject();
             ois.close();
         }
         catch(Exception ex)
         {
             ex.printStackTrace();
         }
     return obj;
     }

     // serialize the given object and save it to file
     public static void serialize(Object obj, String fileName)  {
         try {
             FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
             oos.writeObject(obj);
             fos.close();
         }
         catch(Exception e)
         {
             e.printStackTrace();
         }
     }

 }