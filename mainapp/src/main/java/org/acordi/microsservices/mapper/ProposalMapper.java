package org.acordi.microsservices.mapper;

import org.acordi.microsservices.dto.ProposalRequestDTO;
import org.acordi.microsservices.dto.ProposalResponseDTO;
import org.acordi.microsservices.entity.Proposal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProposalMapper {

    ProposalMapper INSTANCE = Mappers.getMapper(ProposalMapper.class);

    @Mapping(target = "user.name", source = "name")
    @Mapping(target = "user.lastname", source = "lastname")
    @Mapping(target = "user.cpf", source = "cpf")
    @Mapping(target = "user.phone", source = "phone")
    @Mapping(target = "user.income", source = "income")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "approved", ignore = true)
    @Mapping(target = "integrated", constant = "true")
    @Mapping(target = "observation", ignore = true)
    Proposal toProposal(ProposalRequestDTO proposalRequestDTO);

    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "lastname", source = "user.lastname")
    @Mapping(target = "cpf", source = "user.cpf")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "income", source = "user.income")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "askedValue", source = "askedValue")
    @Mapping(target = "paymentTerm", source = "paymentTerm")
    @Mapping(target = "approved", source = "approved")
    @Mapping(target = "observation", source = "observation")
    ProposalResponseDTO toProposalResponseDTO(Proposal proposal);

    List<ProposalResponseDTO> toListOfProposalResponseDto(Iterable<Proposal> proposals);

}
