package group.estoquepreco.conections;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.stereotype.Component;

import my.dependency.constantes.RabbitMQConstantes;

@Component
public class RabbitMQConection {
    
    private static final String NAME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin){
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String name){
        return new Queue(name, true, false, false);
    }

    private DirectExchange troca(){
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    public void adicionar(){
        Queue estoque = this.fila(RabbitMQConstantes.FILA_ESTOQUE);
        Queue preco = this.fila(RabbitMQConstantes.FILA_PRECO);
    
        DirectExchange troca = this.troca();

        Binding relacionar1 = this.relacionamento(estoque, troca);
        Binding relacionar2 = this.relacionamento(preco, troca);
    
        amqpAdmin.declareQueue(estoque);
        amqpAdmin.declareQueue(preco);

        amqpAdmin.declareExchange(troca);

        amqpAdmin.declareBinding(relacionar1);
        amqpAdmin.declareBinding(relacionar2);
    }

}
