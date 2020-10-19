package com.huasit.apm.business.material.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 */
@Entity
@Table(name = "MATERIAL_GROUP_DETAIL")
public class MaterialGroupDetail implements Serializable {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     */
    @Column
    private boolean required;

    /**
     *
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupId", nullable = false)
    private MaterialGroup group;

    /**
     *
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "materialId")
    private Material material;

    /**
     *
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public MaterialGroup getGroup() {
        return group;
    }

    public void setGroup(MaterialGroup group) {
        this.group = group;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}