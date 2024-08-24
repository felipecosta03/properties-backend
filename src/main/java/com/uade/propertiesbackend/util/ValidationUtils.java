package com.uade.propertiesbackend.util;

import static com.uade.propertiesbackend.util.Validate.validateNotBlank;
import static com.uade.propertiesbackend.util.Validate.validateNotEmpty;
import static com.uade.propertiesbackend.util.Validate.validateNotNull;
import static com.uade.propertiesbackend.util.Validate.validateNotNullAndPositive;
import static com.uade.propertiesbackend.util.Validate.validateNotNullAndZeroOrPositive;

import com.uade.propertiesbackend.core.domain.PropertySortBy;
import com.uade.propertiesbackend.core.domain.PropertyType;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtils {

  public static void validatePropertyId(Long propertyId) {
    validateNotNullAndPositive(propertyId, "PropertyId");
  }


  public static void validateBeds(Integer beds) {
    validateNotNullAndPositive(beds, "Beds");
  }

  public static void validateBathrooms(Integer bathrooms) {
    validateNotNullAndPositive(bathrooms, "Bathrooms");
  }

  public static void validateCountry(String country) {
    validateNotBlank(country, "Country");
  }

  public static void validateCity(String city) {
    validateNotBlank(city, "City");
  }

  public static void validateState(String state) {
    validateNotBlank(state, "State");
  }

  public static void validateRooms(Integer rooms) {
    validateNotNullAndPositive(rooms, "Rooms");
  }

  public static void validateSurfaceCovered(Double surface) {
    validateNotNullAndPositive(surface, "Surface covered");
  }

  public static void validateSurfaceTotal(Double surface) {
    validateNotNullAndPositive(surface, "Surface total");
  }

  public static void validateTitle(String title) {
    validateNotBlank(title, "Title");
  }

  public static void validateDescription(String description) {
    validateNotBlank(description, "Description");
  }

  public static void validateLatitude(Double latitude) {
    validateNotNull(latitude, "Latitude");
  }

  public static void validateLongitude(Double longitude) {
    validateNotNull(longitude, "Longitude");
  }

  public static void validateImages(List<String> images) {
    validateNotEmpty(images, "Images");
  }

  public static void validateUserId(Long userId) {
    validateNotNull(userId, "UserId");
  }

  public static void validateAddress(String address) {
    validateNotBlank(address, "Address");
  }

  public static void validateStoreys(Integer storeys) {
    validateNotNullAndPositive(storeys, "Storeys");
  }

  public static void validatePrice(Double price) {
    validateNotNullAndPositive(price, "Price");
  }

  public static void validateMinPrice(Double price) {
    validateNotNullAndZeroOrPositive(price, "Price");
  }

  public static void validatePropertyType(PropertyType propertyType) {
    validateNotNull(propertyType, "PropertyType");
  }

  public static void validateGarages(Integer garages) {
    validateNotNullAndZeroOrPositive(garages, "garages");
  }

  public static void validatePage(Integer page) {
    validateNotNullAndZeroOrPositive(page, "page");
  }

  public static void validatePropertySortBy(PropertySortBy sortBy) {
    validateNotNull(sortBy, "PropertySortBy");
  }

  public static void validateActive(Boolean active) {
    validateNotNull(active, "active");
  }
}
