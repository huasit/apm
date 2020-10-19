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
    public void addOrUpdate(MaterialGroup materialGroup, User loginUser) {
        Date now = new Date();
        if(materialGroup.getId() == null) {
            materialGroup.setDel(false);
            materialGroup.setCreateTime(now);
            materialGroup.setCreatorId(loginUser.getId());
        } else {
            MaterialGroup db = this.materialGroupRepository.findMaterialById(materialGroup.getId());
            materialGroup.setDel(db.isDel());
            materialGroup.setCreateTime(db.getCreateTime());
            materialGroup.setCreatorId(db.getCreatorId());
        }
        materialGroup.setModifyTime(now);
        materialGroup.setModifyId(loginUser.getId());
        List<MaterialGroupDetail> details = new ArrayList<>();
        if(materialGroup.getDetails() != null) {
            for(MaterialGroupDetail detail : materialGroup.getDetails()) {
                Material material = this.materialRepository.findMaterialById(detail.getMaterial().getId());
                if(material == null) {
                    continue;
                }
                detail.setMaterial(material);
                detail.setGroup(materialGroup);
                details.add(detail);
            }
        }
        materialGroup.setDetails(details);
        this.materialGroupRepository.save(materialGroup);
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
