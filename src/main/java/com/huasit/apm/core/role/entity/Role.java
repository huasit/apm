package com.huasit.apm.core.role.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ROLE")
public class Role {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     */
    @Column(nullable = false)
    private boolean del;

    /**
     *
     */
    @Column(nullable = false)
    private String rkey;

    /**
     *
     */
    @Column(nullable = false)
    private String name;

    /**
     *
     */
    @Column
    private Long modifyId;

    /**
     *
     */
    @Column
    private Date modifyTime;

    /**
     *
     */
    @Column
    private Long creatorId;

    /**
     *
     */
    @Column
    private Date createTime;

    /**
     *
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="rid")
    private List<RoleUser> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public String getRkey() {
        return rkey;
    }

    public void setRkey(String rkey) {
        this.rkey = rkey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getModifyId() {
        return modifyId;
    }

    public void setModifyId(Long modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<RoleUser> getUsers() {
        return users;
    }

    public void setUsers(List<RoleUser> users) {
        this.users = users;
    }
}
