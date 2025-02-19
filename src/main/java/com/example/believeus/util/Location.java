package com.example.believeus.util;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class Location {
    private String city;        // 시
    private String district;    // 구
    private String neighborhood; // 동

    public Location(String city, String district, String neighborhood) {
        this.city = city;
        this.district = district;
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getNeighborhood() {
        return neighborhood;
    }
}
