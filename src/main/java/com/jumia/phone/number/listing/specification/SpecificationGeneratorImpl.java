package com.jumia.phone.number.listing.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

public class SpecificationGeneratorImpl<T> implements SpecificationGenerator<T> {

  @Override
  public <V> Specification<T> getEqualitySpecification(Map<String, V> attributeValueByAttributeName) {
    return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder)-> {
      Predicate predicate = builder.conjunction();
      for (Map.Entry<String, V> entry : attributeValueByAttributeName.entrySet()) {
        String key = entry.getKey();
        V value = entry.getValue();
        predicate = builder.and(predicate, builder.equal(root.get(key), value));
      }
      return predicate;
    };
  }
}
