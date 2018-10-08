package app.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.*;

@Component
public class MessageSender {

    @Autowired
    private Queue queue;

    @Autowired
    private JMSProducer jmsProducer;

    public void sendMessage(String txt) {
            jmsProducer.send(queue,txt);
    }

//    public void sendMessage(String txt) {
//
//        Context ctx = null;
//        Properties prop = new Properties();
//        prop.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
//        prop.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
//        prop.put("jboss.naming.client.ejb.context", true);
//        prop.put(Context.SECURITY_PRINCIPAL, "root");
//        prop.put(Context.SECURITY_CREDENTIALS, "root");
//        try {
//            //Create and start connection
//            ctx = new InitialContext(prop);
//            QueueConnectionFactory f = (QueueConnectionFactory) ctx.lookup("jms/RemoteConnectionFactory");
//            QueueConnection con = f.createQueueConnection("root", "root");
//            con.start();
//            //2) create queue session
//            QueueSession ses = con.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
//            //3) get the Queue object
//            Queue t = ses.createQueue("ExpiryQueue");
//            //4)create QueueSender object
//            QueueSender sender = ses.createSender(t);
//            //5) create TextMessage object
//            TextMessage msg = ses.createTextMessage();
//            msg.setText(txt);
//            sender.send(msg);
//            con.close();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }

}