package org.acordi.notificationms.listeners;

import org.acordi.notificationms.constant.ConstantMessage;
import org.acordi.notificationms.domain.Proposal;
import org.acordi.notificationms.service.NotificationSnsService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProposalPendingListener {

    private NotificationSnsService notificationSnsService;

    public ProposalPendingListener(NotificationSnsService notificationSnsService) {
        this.notificationSnsService = notificationSnsService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposal.pending}")
    public void pendingProposal(Proposal proposal){
        String message = String.format(ConstantMessage.PROPOSAL_IN_ANALYSIS, proposal.getUser().getName());
        notificationSnsService.notifyUser(proposal.getUser().getPhone(), message);
    }
}
