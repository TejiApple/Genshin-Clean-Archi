package com.example.genshininfoapp.models;

import java.io.Serializable;

public class WeaponDetailsModel implements Serializable {
        String skill;

    public WeaponDetailsModel(String skill) {
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
