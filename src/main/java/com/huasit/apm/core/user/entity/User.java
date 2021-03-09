package com.huasit.apm.core.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huasit.apm.business.thirdparty.entity.Thirdparty;
import com.huasit.apm.core.role.entity.Role;
import com.huasit.apm.core.role.entity.RoleGroupUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 */
@Entity
@Table(name = "USER")
public class User implements Serializable {

    public enum Type {
        INSIDE, OUTSIDE, THIRDPARTY;
    }

    public enum State {
        NORMAL, DISABLE, DELETE;
    }

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
    private Type type;

    /**
     *
     */
    @Column(nullable = false)
    private State state;

    /**
     *
     */
    @Column(nullable = false, length = 100)
    private String name;

    /**
     *
     */
    @Column(nullable = false, length = 100)
    private String username;

    /**
     *
     */
    @Column(nullable = false, length = 100)
    private String password;

    /**
     *
     */
    @Column(length = 100)
    private String email;

    /**
     *
     */
    @Column(length = 100)
    private String telphone;

    /**
     *
     */
    @Column(length = 100)
    private String deptCode;

    /**
     *
     */
    @Column(length = 100)
    private String deptName;

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
    @JsonIgnore
    @Transient
    private UserToken token;

    /**
     *
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="thirdpartyId")
    private Thirdparty thirdparty;

    /**
     *
     */
    @Transient
    private boolean admin;

    /**
     *
     */
    @Transient
    private List<Type> types;

    /**
     *
     */
    @Transient
    private List<State> states;

    /**
     *
     */
    @Transient
    private List<RoleGroupUser> roleGroups;

    /**
     *
     */
    @Transient
    private List<Role> roles;

    /**
     *
     */
    @Transient
    private Long thirdpartyId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return this.id != null && this.id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
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

    public UserToken getToken() {
        return token;
    }

    public void setToken(UserToken token) {
        this.token = token;
    }

    public Thirdparty getThirdparty() {
        return thirdparty;
    }

    public void setThirdparty(Thirdparty thirdparty) {
        this.thirdparty = thirdparty;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public List<RoleGroupUser> getRoleGroups() {
        return roleGroups;
    }

    public void setRoleGroups(List<RoleGroupUser> roleGroups) {
        this.roleGroups = roleGroups;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getThirdpartyId() {
        return thirdpartyId;
    }

    public void setThirdpartyId(Long thirdpartyId) {
        this.thirdpartyId = thirdpartyId;
    }
}