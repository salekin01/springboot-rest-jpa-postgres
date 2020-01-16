/*

package springboot_rest_jpa_postgres;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.Queue;

@Configuration
public class ArtemisConfig {

    @Value("${jms.queue.destination}")
    String destinationQueue;
    @Value("${activemq.broker-url}")
    String brokerUrl;

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(destinationQueue);
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() throws JMSException {
        return new JmsTemplate(activeMQConnectionFactory());
    }
}
*/

