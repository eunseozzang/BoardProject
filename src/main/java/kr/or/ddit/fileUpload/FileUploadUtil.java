package kr.or.ddit.fileUpload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadUtil {
   private static final Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);
   //form-data; name="img"; filename="sally.png"
   // ==> sally.png
   
   //FileUploadUtilTest
   public static String getFilename(String contentDisposition) {
      String filename = "";
      String[] values = contentDisposition.split("; ");
      for (String index : values) {
         String[] value = index.split("=");
         if (value[0].equals("filename")){
//            filename = value[1];
            return value[1].replaceAll("\"", "");
         }
      }
      return filename;
   }
   
   public static String getExtension(String filename) {
	   if(filename == null || filename.indexOf(".") == -1) {
		   return "";
	   } else {
		   return filename.split("\\.")[1];
	   }
	   
   }
}