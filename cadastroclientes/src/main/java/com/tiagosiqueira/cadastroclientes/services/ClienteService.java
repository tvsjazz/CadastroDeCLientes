package com.tiagosiqueira.cadastroclientes.services;

import com.tiagosiqueira.cadastroclientes.dto.request.ClienteDTO;
import com.tiagosiqueira.cadastroclientes.dto.response.MessageResponseDTO;
import com.tiagosiqueira.cadastroclientes.exception.ClientNotFoundException;
import com.tiagosiqueira.cadastroclientes.mapper.ClienteMapper;
import com.tiagosiqueira.cadastroclientes.models.Cliente;
import com.tiagosiqueira.cadastroclientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    private MessageResponseDTO createMessageResponse(Long id, String message) {
        return MessageResponseDTO.builder().message(message + id).build();
    }

    public MessageResponseDTO saveClient(ClienteDTO clienteDTO) {
        Cliente clientToSave = clienteMapper.toModel(clienteDTO);
        LocalDate dataHoje = LocalDate.now();
        clientToSave.setDataCadastro(dataHoje);

        Cliente clienteSaved = clienteRepository.save(clientToSave);

        return createMessageResponse(clienteSaved.getId(), "Created client with ID ");
    }

    public List<ClienteDTO> listAll() {
        List<Cliente> allClients = clienteRepository.findAll();
        return allClients.stream()
                .map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO findById(Long id) throws ClientNotFoundException {
        Cliente cliente = verifyIfExists(id);
        return clienteMapper.toDTO(cliente);
    }

    private Cliente verifyIfExists(Long id) throws ClientNotFoundException {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    public MessageResponseDTO updateById(Long id, ClienteDTO clienteDTO) throws ClientNotFoundException {
        verifyIfExists(id);

        Cliente clientToUpdate = clienteMapper.toModel(clienteDTO);
        LocalDate dataHoje = LocalDate.now();
        clientToUpdate.setDataAtualizacao(dataHoje);
        Cliente updatedClient = clienteRepository.save(clientToUpdate);
        return createMessageResponse(updatedClient.getId(), "Updated client with ID ");
    }

    public void delete(Long id) throws ClientNotFoundException {
        verifyIfExists(id);

        clienteRepository.deleteById(id);
    }
}
