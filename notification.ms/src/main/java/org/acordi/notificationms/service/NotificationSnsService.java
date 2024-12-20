package org.acordi.notificationms.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationSnsService {

    @Autowired
    private AmazonSNS amazonSNS;

    private final Logger logger = LoggerFactory.getLogger(NotificationSnsService.class);

    public void notifyUser(String phone, String message){
        PublishRequest publishRequest = new PublishRequest().withMessage(message).withPhoneNumber(phone);
        amazonSNS.publish(publishRequest);
        logger.info(String.format("Publishing message to number: %s, message: '%s'", phone, message));
    }
}
