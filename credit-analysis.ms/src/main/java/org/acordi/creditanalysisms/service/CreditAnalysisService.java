package org.acordi.creditanalysisms.service;

import org.acordi.creditanalysisms.domain.Proposal;
import org.acordi.creditanalysisms.exceptions.StrategyException;
import org.acordi.creditanalysisms.service.strategy.CreditComputation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditAnalysisService {

    private final List<CreditComputation> creditComputationList;
    private final RabbitMQNotificationService rabbitMQNotificationService;

    @Value("${rabbitmq.completedproposal.exchange}")
    private String exchange;

    public CreditAnalysisService(List<CreditComputation> creditComputationList, RabbitMQNotificationService rabbitMQNotificationService) {
        this.creditComputationList = creditComputationList;
        this.rabbitMQNotificationService = rabbitMQNotificationService;
    }

    public void analyze(Proposal proposal){
        try{
            boolean approved = creditComputationList.stream().mapToInt(impl -> impl.compute(proposal)).sum() > 450;
            proposal.setApproved(approved);
        }catch (StrategyException e){
            proposal.setApproved(false);
            proposal.setObservation(e.getMessage());
        }
        rabbitMQNotificationService.notify(exchange, proposal);
    }
}
