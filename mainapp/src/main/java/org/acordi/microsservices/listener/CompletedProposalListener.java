package org.acordi.microsservices.listener;

import org.acordi.microsservices.entity.Proposal;
import org.acordi.microsservices.service.ProposalService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CompletedProposalListener {

    private ProposalService proposalService;

    public CompletedProposalListener(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposal.completed}")
    public void completedProposal(Proposal proposal){
        proposalService.update(proposal);
    }


}
