package com.ecpss.util;
import java.util.Properties;   

import org.springframework.beans.factory.FactoryBean;   
  
public class PropertiesEncryptFactoryBean implements FactoryBean {   
  
    private Properties properties;   
       
    public Object getObject() throws Exception {   
        return getProperties();   
    }   
  
    public Class getObjectType() {   
        return java.util.Properties.class;   
    }   
  
    public boolean isSingleton() {   
        return true;   
    }   
  
    public Properties getProperties() {   
        return properties;   
    }   
  
    public void setProperties(Properties inProperties) {   
        this.properties = inProperties;   
        String originalUsername = properties.getProperty("user");   
        String originalPassword = properties.getProperty("password");   
        if (originalUsername != null){   
            String newUsername = AES.getDecrypt(originalUsername, "54a0902703a0a6f347d4d63d879a0bab");   
            properties.put("user", newUsername);   
        }   
        if (originalPassword != null){   
            String newPassword = AES.getDecrypt(originalPassword, "54a0902703a0a6f347d4d63d879a0bab");   
            properties.put("password",newPassword);   
        }   
    }   
       

  
}  

