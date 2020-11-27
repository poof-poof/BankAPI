package org.bataykin.transformer.user;

import org.bataykin.dto.AuthUserDto;
import org.bataykin.security.model.UserDetailsImpl;
import org.bataykin.transformer.ToDtoTransformer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserDetailsTransformer extends ToDtoTransformer<UserDetailsImpl, AuthUserDto> {

    @Override
    @Mapping(target = "roles", ignore = true)
    AuthUserDto transform(UserDetailsImpl entity);
}
