package org.acordi.microsservices.service;

import org.acordi.microsservices.dto.ProposeRequestDTO;
import org.acordi.microsservices.dto.ProposeResponseDTO;
import org.acordi.microsservices.entity.Propose;
import org.acordi.microsservices.mapper.ProposeMapper;
import org.acordi.microsservices.repository.ProposeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposeService {

    @Autowired
    private ProposeRepository proposeRepository;

    public ProposeResponseDTO create(ProposeRequestDTO request) {
        Propose propose = ProposeMapper.INSTANCE.toPropose(request);
        propose = proposeRepository.save(propose);
        return ProposeMapper.INSTANCE.toProposeResponseDTO(propose);
    }
}
