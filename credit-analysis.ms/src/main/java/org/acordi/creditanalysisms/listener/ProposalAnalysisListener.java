package org.acordi.creditanalysisms.listener;

import org.acordi.creditanalysisms.service.CreditAnalysisService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.acordi.creditanalysisms.domain.Proposal;
import org.springframework.stereotype.Component;

@Component
public class ProposalAnalysisListener {

    @Autowired
    private CreditAnalysisService creditAnalysisService;

    @RabbitListener(queues = "${rabbitmq.queue.proposal.pending}")
    public void proposalInAnalysis(Proposal proposal){
        creditAnalysisService.analyze(proposal);
    }
}
