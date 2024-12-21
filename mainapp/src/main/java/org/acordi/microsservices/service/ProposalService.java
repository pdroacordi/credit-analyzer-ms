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
import java.util.Objects;

@Service
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final UserService userService;
    private final NotificationService notificationService;
    private final String exchange;

    public ProposalService(ProposalRepository proposalRepository, UserService userService, NotificationService notificationService, @Value("${rabbitmq.exchange.proposal.pending}") String exchange) {
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

    public boolean update(Proposal proposal){
        Proposal temp = proposalRepository.findById(proposal.getId()).orElse(null);

        if(temp == null) throw new RuntimeException("Proposta Inexistente.");

        if( temp.getPaymentTerm() != proposal.getPaymentTerm()) temp.setPaymentTerm( proposal.getPaymentTerm() );
        if( !Objects.equals(temp.getAskedValue(), proposal.getAskedValue())) temp.setAskedValue( proposal.getAskedValue() );
        if( temp.getApproved() != proposal.getApproved()) temp.setApproved( proposal.getApproved() );
        if( !Objects.equals(temp.getObservation(), proposal.getObservation() ) ) temp.setObservation( proposal.getObservation() );

        proposalRepository.save( temp );
        return true;
    }
}
