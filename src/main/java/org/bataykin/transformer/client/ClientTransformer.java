package org.bataykin.transformer.client;

import org.bataykin.dto.client.ClientDto;
import org.bataykin.model.client.Client;
import org.bataykin.transformer.Transformer;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientTransformer extends Transformer<Client, ClientDto> {

    @Override
    @Mapping(target = "cardId", ignore = true)
    @Mapping(target = "fullName", ignore = true)
    @Mapping(target = "fullName.firstName", source = "firstName")
    @Mapping(target = "fullName.lastName", source = "lastName")
    @Mapping(target = "fullName.middleName", source = "middleName")
    Client transform(ClientDto dto);

    @Override
    @InheritInverseConfiguration(name = "transform")
    ClientDto transform(Client entity);
}
