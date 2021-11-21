package com.jumia.phone.number.listing.mapping;

public interface Mapper {

  <T, R> R map(T toBeMapped, Class<R> mappedTo);
}
