package com.uade.propertiesbackend.core.usecase.impl;

import com.uade.propertiesbackend.core.usecase.CreateImages;
import com.uade.propertiesbackend.core.usecase.UploadImage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DefaultCreateImages implements CreateImages {

  private final String BASE_64_PREFIX = "base64";
  private final UploadImage uploadImage;

  public DefaultCreateImages(UploadImage uploadImage) {
    this.uploadImage = uploadImage;
  }

  @Override
  public List<String> apply(List<String> images) {
    List<String> newImages = new ArrayList<>();
    for (String image : images) {
      if (image.contains(BASE_64_PREFIX)) {
        newImages.add(uploadImage.apply(image));
      } else {
        newImages.add(image);
      }
    }
    return newImages;
  }
}
