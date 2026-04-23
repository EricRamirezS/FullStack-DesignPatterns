package com.aethelgard.archive.domain.model;

public class ManaWell {
    private Long id;
    private String location;
    private int powerLevel;

    public ManaWell() {}

    public ManaWell(Long id, String location, int powerLevel) {
        this.id = id;
        this.location = location;
        this.powerLevel = powerLevel;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public int getPowerLevel() { return powerLevel; }
    public void setPowerLevel(int powerLevel) { this.powerLevel = powerLevel; }
}
