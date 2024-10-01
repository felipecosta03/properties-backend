package com.uade.propertiesbackend.core.usecase.impl;


import static java.util.Objects.isNull;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.uade.propertiesbackend.core.exception.BadRequestException;
import com.uade.propertiesbackend.core.exception.FailedDependencyException;
import com.uade.propertiesbackend.core.usecase.UploadImage;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultUploadImage implements UploadImage {

  private final Cloudinary cloudinary;

  public DefaultUploadImage(Cloudinary cloudinary) {
    this.cloudinary = cloudinary;
  }

  @Override
  public String apply(String image) {
    validateImage(image);
    try {
      Map<?, ?> imageInfo = cloudinary.uploader().upload(image, ObjectUtils.emptyMap());
      log.info("Image uploaded successfully");
      return imageInfo.get("url").toString().replace("http", "https");
    } catch (Exception e) {
      throw new FailedDependencyException(e.getMessage());
    }
  }

  private void validateImage(String image) {
    if (isNull(image)) {
      throw new BadRequestException("Image is required");
    }
  }
}
