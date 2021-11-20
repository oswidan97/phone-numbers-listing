package com.jumia.phone.number.listing.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface SpecificationGenerator<T> {
  <V> Specification<T> getEqualitySpecification(Map<String, V> attributeValueByAttributeName);
}
