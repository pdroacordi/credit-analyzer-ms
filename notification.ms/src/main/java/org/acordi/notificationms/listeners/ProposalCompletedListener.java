package org.acordi.notificationms.listeners;

import org.acordi.notificationms.constant.ConstantMessage;
import org.acordi.notificationms.domain.Proposal;
import org.acordi.notificationms.service.NotificationSnsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.scanner.Constant;

@Component
public class ProposalCompletedListener {

    private final NotificationSnsService notificationSnsService;

    public ProposalCompletedListener(NotificationSnsService notificationSnsService) {
        this.notificationSnsService = notificationSnsService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposal.completed}")
    public void completedProposal(Proposal proposal){
        String successOrFailMessage = String.format(proposal.getApproved() ? ConstantMessage.PROPOSAL_SUCCESS : ConstantMessage.PROPOSAL_FAIL, proposal.getUser().getName(), proposal.getAskedValue());
        String failTypeMessage = proposal.getObservation() != null ? proposal.getObservation() : successOrFailMessage;
        String message = proposal.getApproved() ? successOrFailMessage : failTypeMessage;
        notificationSnsService.notifyUser(proposal.getUser().getPhone(), message);
    }
}
