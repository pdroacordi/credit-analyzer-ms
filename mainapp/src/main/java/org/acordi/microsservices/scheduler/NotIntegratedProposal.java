package org.acordi.microsservices.scheduler;

import org.acordi.microsservices.entity.Proposal;
import org.acordi.microsservices.repository.ProposalRepository;
import org.acordi.microsservices.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class NotIntegratedProposal {

    private final ProposalRepository proposalRepository;
    private final NotificationService notificationService;
    private final String exchange;

    private final Logger logger = LoggerFactory.getLogger(NotIntegratedProposal.class);

    public NotIntegratedProposal(ProposalRepository proposalRepository, NotificationService notificationService, @Value("${rabbitmq.pendingproposal.exchange}") String exchange) {
        this.proposalRepository = proposalRepository;
        this.notificationService = notificationService;
        this.exchange = exchange;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void searchNotIntegratedProposals(){
        proposalRepository.findAllByIntegratedIsFalse().forEach(proposal -> {
            try{
                notificationService.notify(proposal, exchange);
                updateProposal(proposal);
            } catch (RuntimeException ex){
                logger.error(ex.getMessage());
            }
        });
    }

    private void updateProposal(Proposal proposal){
        proposal.setIntegrated(true);
        proposalRepository.save(proposal);
    }
}
