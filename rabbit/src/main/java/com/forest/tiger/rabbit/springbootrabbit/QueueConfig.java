package com.forest.tiger.rabbit.springbootrabbit;


import org.springframework.amqp.core.*;
import org.springframework.amqp.core.Binding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className
 * @Description TODO
 * @Author 付林虎
 * @Date 2020/12/3 8:42
 * @Version V1.0
 */
@Configuration
public class QueueConfig {

    @Bean("queuehello")
    public Queue queuehello(){
        return new Queue("queuehello");
    }

    @Bean("queue")
    public Queue queue(){
        return new Queue("boot_queue");
    }
    @Bean("exchange")
    public Exchange exchange(){
        Exchange exchange = new TopicExchange("boot_topic_ex");
        return exchange;
    }
    @Bean
    public Binding  bind(@Qualifier("exchange") Exchange exchange, @Qualifier("queue") Queue queue){
        return BindingBuilder.bind(queue).to(exchange).with("boot.#").noargs();
    }

}
