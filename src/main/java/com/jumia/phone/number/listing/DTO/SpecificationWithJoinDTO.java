package com.jumia.phone.number.listing.DTO;

public class SpecificationWithJoinDTO<V> {
  private final String entityName;
  private final String attributeName;
  private final V attributeValue;

  public SpecificationWithJoinDTO(String entityName, String attributeName, V attributeValue) {
    this.entityName = entityName;
    this.attributeName = attributeName;
    this.attributeValue = attributeValue;
  }

  public String getEntityName() {
    return entityName;
  }

  public String getAttributeName() {
    return attributeName;
  }

  public V getAttributeValue() {
    return attributeValue;
  }
}
