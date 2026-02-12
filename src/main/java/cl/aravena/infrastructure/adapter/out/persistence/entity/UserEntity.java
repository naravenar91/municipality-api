package cl.aravena.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "user_uuid", nullable = false, length = 36)
    public String userUuid;

    @Column(name = "user_name", nullable = false, unique = true, length = 12)
    public String userName;

    @Column(nullable = false, length = 50)
    public String name;

    @Column(nullable = false, length = 150)
    public String email;

    @Column(nullable = false, length = 255)
    public String address;

    @Column(nullable = false, length = 255)
    public String phone;

    @Column(name = "is_active")
    public boolean isActive;

    @Column(name = "created_at", insertable = false, updatable = false)
    public LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<UserMunicipalityEntity> userServiceId = new HashSet<>();
}