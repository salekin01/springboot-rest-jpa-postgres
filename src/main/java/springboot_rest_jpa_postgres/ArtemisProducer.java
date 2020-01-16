package springboot_rest_jpa_postgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
//import javax.jms.Queue;

@Component
public class ArtemisProducer {
    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${jms.queue.destination}")
    String destinationQueue;

    //----bellow Autowired is needed if we use additional ArtemisConfig class----#
    //@Autowired
    //Queue queue;

    public void send(String msg){
        jmsTemplate.convertAndSend(destinationQueue, msg);
    }
}
