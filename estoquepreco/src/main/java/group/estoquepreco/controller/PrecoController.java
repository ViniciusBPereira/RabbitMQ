package group.estoquepreco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import group.estoquepreco.service.RabbitMQService;
import my.dependency.constantes.RabbitMQConstantes;
import my.dependency.dto.PrecoDto;

@RequestMapping("/preco")
@RestController
public class PrecoController {
    
    @Autowired
    private RabbitMQService service;

    @PutMapping
    private ResponseEntity<?> alteraPreco(@RequestBody PrecoDto preco){
        service.enviarMsg(RabbitMQConstantes.FILA_PRECO, preco);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
