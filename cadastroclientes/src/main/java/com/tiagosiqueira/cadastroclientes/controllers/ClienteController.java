package com.tiagosiqueira.cadastroclientes.controllers;

import com.tiagosiqueira.cadastroclientes.dto.request.ClienteDTO;
import com.tiagosiqueira.cadastroclientes.dto.response.MessageResponseDTO;
import com.tiagosiqueira.cadastroclientes.exception.ClientNotFoundException;
import com.tiagosiqueira.cadastroclientes.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/client")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO newClient(@RequestBody @Valid ClienteDTO clienteDTO) {
        return clienteService.saveClient(clienteDTO);
    }

    @GetMapping
    public List<ClienteDTO>  listAll() {
        return clienteService.listAll();
    }

    @GetMapping("/{id}")
    public ClienteDTO getClientById(@PathVariable Long id ) throws ClientNotFoundException {
        return clienteService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid ClienteDTO clienteDTO) throws ClientNotFoundException {
        return clienteService.updateById(id, clienteDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws ClientNotFoundException {
        clienteService.delete(id);
    }

}
