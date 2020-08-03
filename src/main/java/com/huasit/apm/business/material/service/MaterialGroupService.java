package com.huasit.apm.business.material.service;

import com.huasit.apm.business.material.entity.*;
import com.huasit.apm.core.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MaterialGroupService {

    /**
     *
     */
    public List<MaterialGroup> getAll() {
        return this.materialGroupRepository.findAll();
    }

    /**
     *
     */
    public MaterialGroup getById(Long id) {
        return this.materialGroupRepository.findMaterialById(id);
    }

    /**
     *
     */
    public MaterialGroup add(String name, Long[] materialIds, User loginUser) {
        MaterialGroup materialGroup = new MaterialGroup();
        materialGroup.setName(name);
        materialGroup.setDel(false);
        materialGroup.setCreatorId(loginUser.getId());
        materialGroup.setCreateTime(new Date());
        materialGroup.setModifyId(materialGroup.getCreatorId());
        materialGroup.setModifyTime(materialGroup.getCreateTime());
        List<MaterialGroupDetail> details = new ArrayList<>();
        if(materialIds != null && materialIds.length > 0) {
            for(Long materialId : materialIds) {
                Material material = this.materialRepository.findMaterialById(materialId);
                if(material == null) {
                    continue;
                }
                MaterialGroupDetail detail = new MaterialGroupDetail();
                detail.setMaterial(material);
                detail.setGroup(materialGroup);
                details.add(detail);
            }
            materialGroup.setDetails(details);
        }
        this.materialGroupRepository.save(materialGroup);
        return materialGroup;
    }

    /**
     *
     */
    public MaterialGroup update(Long id, String name, Long[] materialIds, User loginUser) {
        MaterialGroup db = this.materialGroupRepository.findMaterialById(id);
        db.setName(name);
        db.setModifyId(loginUser.getId());
        db.setModifyTime(new Date());
        if(materialIds != null && materialIds.length > 0) {
            List<MaterialGroupDetail> details = new ArrayList<>();
            for(Long materialId : materialIds) {
                Material material = this.materialRepository.findMaterialById(materialId);
                if(material == null) {
                    continue;
                }
                MaterialGroupDetail detail = new MaterialGroupDetail();
                detail.setMaterial(material);
                detail.setGroup(db);
                details.add(detail);
            }
            db.setDetails(details);
        } else {
            db.setDetails(new ArrayList<>());
        }
        this.materialGroupRepository.save(db);
        return db;
    }

    /**
     *
     */
    public void delete(Long id, User loginUser) {
        MaterialGroup materialGroup = this.materialGroupRepository.findMaterialById(id);
        if(materialGroup == null) {
            return;
        }
        materialGroup.setDel(true);
        materialGroup.setModifyId(loginUser.getId());
        materialGroup.setModifyTime(new Date());
        this.materialGroupRepository.save(materialGroup);
    }

    /**
     *
     */
    @Autowired
    MaterialRepository materialRepository;

    /**
     *
     */
    @Autowired
    MaterialGroupRepository materialGroupRepository;
}
