package org.acordi.microsservices.mapper;

import org.acordi.microsservices.dto.ProposeRequestDTO;
import org.acordi.microsservices.dto.ProposeResponseDTO;
import org.acordi.microsservices.entity.Propose;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProposeMapper {

    ProposeMapper INSTANCE = Mappers.getMapper(ProposeMapper.class);

    @Mapping(target = "user.name", source = "name")
    @Mapping(target = "user.lastname", source = "lastname")
    @Mapping(target = "user.cpf", source = "cpf")
    @Mapping(target = "user.phone", source = "phone")
    @Mapping(target = "user.income", source = "income")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "approved", ignore = true)
    @Mapping(target = "integrated", ignore = true)
    @Mapping(target = "description", ignore = true)
    Propose toPropose(ProposeRequestDTO proposeRequestDTO);

    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "lastname", source = "user.lastname")
    @Mapping(target = "cpf", source = "user.cpf")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "income", source = "user.income")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "askedValue", source = "askedValue")
    @Mapping(target = "paymentTerm", source = "paymentTerm")
    @Mapping(target = "approved", source = "approved")
    @Mapping(target = "observation", ignore = true)
    ProposeResponseDTO toProposeResponseDTO(Propose propose);

    List<ProposeResponseDTO> toListOfProposeResponseDto(Iterable<Propose> proposes);

}
