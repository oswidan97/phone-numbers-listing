package com.jumia.phone.number.listing.specification;

import com.jumia.phone.number.listing.DTO.SpecificationWithJoinDTO;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.Map;

@Component
public class SpecificationGeneratorImpl<T> implements SpecificationGenerator<T> {

  @Override
  public <V> Specification<T> getEqualitySpecification(Map<String, V> attributeValueByAttributeName) {
    return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
      Predicate predicate = builder.conjunction();
      for (Map.Entry<String, V> entry : attributeValueByAttributeName.entrySet()) {
        String key = entry.getKey();
        V value = entry.getValue();
        if (key != null && value != null) {
          predicate = builder.and(predicate, builder.equal(root.get(key), value));
        }
      }
      return predicate;
    };
  }

  @Override
  public <V> Specification<T> getEqualitySpecificationWithJoinedAttribute(SpecificationWithJoinDTO<V> specificationWithJoinDTO) {
    return (Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
      Join<T, ?> joinResult = root.join(specificationWithJoinDTO.getEntityName());
      return builder.equal(joinResult.get(specificationWithJoinDTO.getAttributeName()), specificationWithJoinDTO.getAttributeValue());
    };
  }
}
