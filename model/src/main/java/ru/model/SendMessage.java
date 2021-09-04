package ru.model;

import ru.modeldb.Emp;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jms.*;

@Stateless
public class SendMessage {
    @Resource(mappedName = "java:/jms/scottfactory")
    private XAQueueConnectionFactory factory;
    @Resource(mappedName = "java:/jms/scottqueue")
    private Queue queue;

    public void sendToJMS(Emp emp) {
      //  System.out.println(factory+"   "+queue);
        try(Connection connection=factory.createXAQueueConnection();
            Session session=connection.createSession();
            MessageProducer prod=session.createProducer(queue)){
            TextMessage message=session.createTextMessage();
            message.setText(emp.toString());
            prod.send(message);
        }catch(Exception e){
            throw new RuntimeException("Error!"+ e.getMessage());
        }
    }
}
