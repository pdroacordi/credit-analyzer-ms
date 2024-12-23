package org.acordi.microsservices.service;

import org.acordi.microsservices.dto.ProposalResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void notify(ProposalResponseDTO proposal) {
        messagingTemplate.convertAndSend("/proposals", proposal);
    }
}
