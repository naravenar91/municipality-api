package cl.aravena.infrastructure.adapter.out.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "municipality")
public class MunicipalityEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    public String address;

    @Column(name = "created_at", insertable = false, updatable = false)
    public LocalDateTime createdAt;

    @Column(name = "region_id")
    public Long region;

    @OneToMany(mappedBy = "municipality", cascade = CascadeType.ALL)
    public Set<UserMunicipalityEntity> municipalityServiceId = new HashSet<>();
}
