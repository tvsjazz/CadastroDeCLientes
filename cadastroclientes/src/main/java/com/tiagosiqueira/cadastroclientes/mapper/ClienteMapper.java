package com.tiagosiqueira.cadastroclientes.mapper;

import com.tiagosiqueira.cadastroclientes.dto.request.ClienteDTO;
import com.tiagosiqueira.cadastroclientes.models.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    @Mapping(target = "dataNascimento", source = "dataNascimento", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "dataCadastro", source = "dataCadastro", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "dataAtualizacao", source = "dataAtualizacao", dateFormat = "yyyy-MM-dd")
    Cliente toModel(ClienteDTO clienteDTO);

    ClienteDTO toDTO(Cliente cliente);
}
