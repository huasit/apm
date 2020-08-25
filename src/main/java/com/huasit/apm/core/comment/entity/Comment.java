package com.huasit.apm.core.comment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huasit.apm.core.user.entity.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENT")
public class Comment {

    /**
     *
     */
    public enum CommentType {
        DISALLOW, ALLOW, DEFAULT;
        public static CommentType get(int v) {
            switch(v) {
                case 0:
                    return DISALLOW;
                case 1:
                    return ALLOW;
                case 2:
                    return DEFAULT;
                default:
                    return null;
            }
        }
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
    @JsonIgnore
    @Column(nullable = false)
    private boolean del;

    /**
     *
     */
    @Column(nullable = false)
    private String target;

    /**
     *
     */
    @Column(nullable = false)
    private Long targetId;

    /**
     *
     */
    @Column(nullable = false)
    private CommentType type;

    /**
     *
     */
    @Transient
    private String typeStr;

    /**
     *
     */
    @Column(nullable = false)
    private String stage;

    /**
     *
     */
    @Transient
    @Column(nullable = false)
    private String stageStr;

    /**
     *
     */
    @Column(nullable = false)
    private String content;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name="creatorId")
    private User creator;

    /**
     *
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createTime;

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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public CommentType getType() {
        return type;
    }

    public void setType(CommentType type) {
        this.type = type;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getStageStr() {
        return stageStr;
    }

    public void setStageStr(String stageStr) {
        this.stageStr = stageStr;
    }
}