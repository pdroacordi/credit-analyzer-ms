package org.acordi.microsservices.listener;

import org.acordi.microsservices.dto.ProposalResponseDTO;
import org.acordi.microsservices.entity.Proposal;
import org.acordi.microsservices.mapper.ProposalMapper;
import org.acordi.microsservices.service.ProposalService;
import org.acordi.microsservices.service.WebSocketService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompletedProposalListener {

    @Autowired
    private ProposalService proposalService;

    @Autowired
    private WebSocketService webSocketService;


    @RabbitListener(queues = "${rabbitmq.queue.proposal.completed}")
    public void completedProposal(Proposal proposal){
        proposalService.update(proposal);
        ProposalResponseDTO proposalResponseDTO = ProposalMapper.INSTANCE.toProposalResponseDTO(proposal);
        webSocketService.notify(proposalResponseDTO);
    }


}
