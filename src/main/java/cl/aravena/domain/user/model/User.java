package cl.aravena.domain.user.model;

import cl.aravena.domain.common.valueobject.Name;

import java.time.LocalDateTime;

public record User(Long id,
                   String userUuid,
                   String userName,
                   Name name,
                   String email,
                   String address,
                   String phone,
                   boolean isActive,
                   LocalDateTime createdAt) {

    public static User createNew(String userName, Name name, String email,
                                 String address, String phone) {
        return new User(
                null,
                java.util.UUID.randomUUID().toString(),
                userName,
                name,
                email,
                address,
                phone,
                true,
                LocalDateTime.now()
        );
    }
}