package org.acordi.microsservices.service;

import org.acordi.microsservices.dto.ProposeRequestDTO;
import org.acordi.microsservices.dto.ProposeResponseDTO;
import org.acordi.microsservices.entity.Propose;
import org.acordi.microsservices.entity.User;
import org.acordi.microsservices.mapper.ProposeMapper;
import org.acordi.microsservices.repository.ProposeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposeService {

    @Autowired
    private ProposeRepository proposeRepository;

    @Autowired
    private UserService userService;

    public ProposeResponseDTO create(ProposeRequestDTO request) {
        Propose propose = ProposeMapper.INSTANCE.toPropose(request);

        User user = userService.findUserByCPF( request.getCpf() );
        if(user != null) {
            if (user.getIncome() != request.getIncome()) user.setIncome(request.getIncome());
            if (!user.getName().equalsIgnoreCase(request.getName())) user.setName(request.getName());
            if (!user.getLastname().equalsIgnoreCase(request.getLastname())) user.setLastname(request.getLastname());
            if (!user.getPhone().equalsIgnoreCase(request.getPhone())) user.setPhone(request.getPhone());

            propose.setUser(user);
        }
        propose = proposeRepository.save(propose);
        return ProposeMapper.INSTANCE.toProposeResponseDTO(propose);
    }

    public List<ProposeResponseDTO> getProposes() {
        return ProposeMapper.INSTANCE.toListOfProposeResponseDto( proposeRepository.findAll() );
    }
}
