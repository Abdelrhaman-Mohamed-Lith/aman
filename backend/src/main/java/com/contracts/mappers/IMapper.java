package com.contracts.mappers;

public interface IMapper<E, D> {
    public D toDTO(E entity);

    /**
     * Convert DTO to Entity
     */
    public E toEntity(D dto);

    /**
     * Each subclass must define how to instantiate its DTO and Entity.
     */
     abstract D createDTO();

     abstract E createEntity();
}
