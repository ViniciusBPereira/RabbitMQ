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
import my.dependency.dto.EstoqueDto;

@RequestMapping("/estoque")
@RestController
public class EstoqueController {
    
    @Autowired
    private RabbitMQService service;

    @PutMapping
    private ResponseEntity<?> alteraEstoque(@RequestBody EstoqueDto estoque){
        System.out.println(estoque.codproduto);
        service.enviarMsg(RabbitMQConstantes.FILA_ESTOQUE, estoque);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
