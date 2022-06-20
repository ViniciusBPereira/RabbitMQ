package group.estoquepreco.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {
    
    @Autowired
    private RabbitTemplate template;

    public void enviarMsg(String nomeFila, Object msg){
        template.convertAndSend(nomeFila, msg);
    }

}
