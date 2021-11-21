package com.jumia.phone.number.listing.specification;

import com.jumia.phone.number.listing.DTO.SpecificationWithJoinDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public interface SpecificationGenerator<T> {
  <V> Specification<T> getEqualitySpecification(Map<String, V> attributeValueByAttributeName);

  <V> Specification<T> getEqualitySpecificationWithJoinedAttribute(SpecificationWithJoinDTO<V> specificationWithJoinDTO);

}
