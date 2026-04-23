package com.aethelgard.archive.infrastructure.persistence.entity;

import com.aethelgard.archive.domain.model.ManaWell;
import jakarta.persistence.*;

@Entity
@Table(name = "mana_wells")
public class ManaWellJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String location;

    @Column(name = "power_level", nullable = false)
    private int powerLevel;

    public ManaWellJpaEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getPowerLevel() { return powerLevel; }
    public void setPowerLevel(int powerLevel) { this.powerLevel = powerLevel; }

    public ManaWell toDomain() {
        return new ManaWell(id, location, powerLevel);
    }

    public static ManaWellJpaEntity fromDomain(ManaWell model) {
        if(model == null) return null;
        ManaWellJpaEntity entity = new ManaWellJpaEntity();
        entity.setId(model.getId());
        entity.setLocation(model.getLocation());
        entity.setPowerLevel(model.getPowerLevel());
        return entity;
    }
}
