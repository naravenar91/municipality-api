package cl.aravena.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserMunicipalityId implements Serializable {

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "muni_id")
    public Long muniId;

    public UserMunicipalityId() {}

    public UserMunicipalityId(Long userId, Long muniId) {
        this.userId = userId;
        this.muniId = muniId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMunicipalityId that = (UserMunicipalityId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(muniId, that.muniId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, muniId);
    }
}