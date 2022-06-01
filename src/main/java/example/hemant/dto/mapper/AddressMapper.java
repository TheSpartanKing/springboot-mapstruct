package example.hemant.dto.mapper;

import example.hemant.dto.AddressDto;
import example.hemant.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressDto toDto(Address address);

    Address toEntity(AddressDto addressDto);
}

