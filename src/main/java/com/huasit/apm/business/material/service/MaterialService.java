package com.huasit.apm.business.material.service;

import com.huasit.apm.business.material.entity.Material;
import com.huasit.apm.business.material.entity.MaterialRepository;
import com.huasit.apm.core.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MaterialService {

    /**
     *
     */
    public List<Material> getAll() {
        return this.materialRepository.findAll();
    }

    /**
     *
     */
    public Material getById(Long id) {
        return this.materialRepository.findMaterialById(id);
    }

    /**
     *
     */
    public void add(Material material, User loginUser) {
        material.setDel(false);
        material.setCreatorId(loginUser.getId());
        material.setCreateTime(new Date());
        material.setModifyId(material.getCreatorId());
        material.setModifyTime(material.getCreateTime());
        this.materialRepository.save(material);
    }

    /**
     *
     */
    public void update(Material material, User loginUser) {
        Material db = this.materialRepository.findMaterialById(material.getId());
        if(db == null) {
            return;
        }
        db.setName(material.getName());
        db.setRequired(material.isRequired());
        db.setDescription(material.getDescription());
        db.setModifyId(loginUser.getId());
        db.setModifyTime(new Date());
        this.materialRepository.save(db);
    }

    /**
     *
     */
    public void delete(Long id, User loginUser) {
        Material material = this.materialRepository.findMaterialById(id);
        if(material == null) {
            return;
        }
        material.setDel(true);
        material.setModifyId(loginUser.getId());
        material.setModifyTime(new Date());
        this.materialRepository.save(material);
    }

    /**
     *
     */
    @Autowired
    MaterialRepository materialRepository;
}
