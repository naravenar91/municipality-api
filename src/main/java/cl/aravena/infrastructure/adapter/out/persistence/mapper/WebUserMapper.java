package cl.aravena.infrastructure.adapter.out.persistence.mapper;

import cl.aravena.domain.user.model.User;
import cl.aravena.infrastructure.adapter.in.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.CDI)
public interface WebUserMapper {
    @Mapping(target = "name", source = "name.value")
    UserResponse toResponse(User domain);

    List<UserResponse> toResponseList(List<User> domainList);
}
