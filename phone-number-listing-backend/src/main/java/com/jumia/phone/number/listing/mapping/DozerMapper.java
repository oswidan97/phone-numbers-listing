package com.jumia.phone.number.listing.mapping;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

@Component
public class DozerMapper implements Mapper{
  private final DozerBeanMapper dozerBeanMapper;

  public DozerMapper() {
    this.dozerBeanMapper = new DozerBeanMapper();
  }


  @Override
  public <T, R> R map(T toBeMapped, Class<R> mappedTo) {
    return dozerBeanMapper.map(toBeMapped, mappedTo);
  }
}
