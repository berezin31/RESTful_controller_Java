package ru.model;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/scottqueue"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
}, mappedName = "java:/jms/scottqueue")
public class GetMessage implements MessageListener {
    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            try {
                System.out.println(">>>>>>>>>>>>>"+((TextMessage) message).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
