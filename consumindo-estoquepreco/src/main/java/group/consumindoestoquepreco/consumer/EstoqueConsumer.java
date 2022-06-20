package group.consumindoestoquepreco.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import my.dependency.dto.EstoqueDto;

@Component
public class EstoqueConsumer {
    
    @RabbitListener(queues = "ESTOQUE")
    private void consumidor(EstoqueDto estoque){
        System.out.println(estoque.codproduto);
        System.out.println(estoque.quantidade);
    }

}
