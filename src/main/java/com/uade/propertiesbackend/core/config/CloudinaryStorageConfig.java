package com.uade.propertiesbackend.core.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CloudinaryStorageConfig {

  private final Environment env;

  public CloudinaryStorageConfig(Environment env) {
    this.env = env;
  }

  @Bean
  public Cloudinary cloudinary() {
    Cloudinary cloudinary = new Cloudinary(env.getProperty("CLOUDINARY_URL"));
    cloudinary.config.secure = true;
    return cloudinary;
  }
}
