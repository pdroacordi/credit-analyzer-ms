package org.acordi.microsservices.service;

import org.acordi.microsservices.dto.ProposalRequestDTO;
import org.acordi.microsservices.dto.ProposalResponseDTO;
import org.acordi.microsservices.entity.Proposal;
import org.acordi.microsservices.entity.User;
import org.acordi.microsservices.mapper.ProposalMapper;
import org.acordi.microsservices.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final UserService userService;
    private final NotificationService notificationService;
    private final String exchange;

    public ProposalService(ProposalRepository proposalRepository, UserService userService, NotificationService notificationService, @Value("${rabbitmq.pendingproposal.exchange}") String exchange) {
        this.proposalRepository = proposalRepository;
        this.userService = userService;
        this.notificationService = notificationService;
        this.exchange = exchange;
    }

    public ProposalResponseDTO create(ProposalRequestDTO request) {
        Proposal proposal = ProposalMapper.INSTANCE.toProposal(request);

        User user = userService.findUserByCPF( request.getCpf() );
        if(user != null) {
            if (user.getIncome() != request.getIncome()) user.setIncome(request.getIncome());
            if (!user.getName().equalsIgnoreCase(request.getName())) user.setName(request.getName());
            if (!user.getLastname().equalsIgnoreCase(request.getLastname())) user.setLastname(request.getLastname());
            if (!user.getPhone().equalsIgnoreCase(request.getPhone())) user.setPhone(request.getPhone());

            proposal.setUser(user);
        }
        proposal = proposalRepository.save(proposal);
        notifyRabbitMq(proposal);

        return ProposalMapper.INSTANCE.toProposalResponseDTO(proposal);
    }

    private void notifyRabbitMq(Proposal proposal){
        try {
            notificationService.notify(proposal, exchange);
        }catch (RuntimeException e){
            proposal.setIntegrated(false);
            proposalRepository.save(proposal);
        }
    }

    public List<ProposalResponseDTO> getProposals() {
        return ProposalMapper.INSTANCE.toListOfProposalResponseDto( proposalRepository.findAll() );
    }
}
