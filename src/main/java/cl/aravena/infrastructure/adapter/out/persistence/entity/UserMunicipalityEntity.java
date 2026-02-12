package cl.aravena.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_municipality")
public class UserMunicipalityEntity {
    @EmbeddedId
    public UserMunicipalityId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId") // Vincula con el campo userId de la llave compuesta
    @JoinColumn(name = "user_id")
    public UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("muniId") // Vincula con el campo muniId de la llave compuesta
    @JoinColumn(name = "muni_id")
    public MunicipalityEntity municipality;

    @Column(name = "is_active")
    public boolean isActive = true;

    @Column(name = "created_at", insertable = false, updatable = false)
    public LocalDateTime createdAt;
}
