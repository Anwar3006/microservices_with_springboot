package microservices_book.gamification_service.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;




@Configuration
public class RabbitMQConfiguration implements RabbitListenerConfigurer {

    // Configuring the exchange, queue and binding the two together
    @Bean
    public TopicExchange multiplicationExchange(@Value("${multiplication.exchange}") final String exchangeName){
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue multiplicationQueue(@Value("${multiplication.queue}") final String queueName){
        return new Queue(queueName, true);
    }

    @Bean
    public Binding bindQueueToExchange(final Queue queueName, final TopicExchange exchangeName, @Value("${multiplication.asterisk.routingKey}") final String routingKey){
        return BindingBuilder.bind(queueName).to(exchangeName).with(routingKey);
    }

    //configuring json deserializer
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter(){
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory handlerMethodFactory(){
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(handlerMethodFactory());
    }
}
