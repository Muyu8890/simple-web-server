package com.github.likeabook.webserver.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BaseEntity<T extends BaseEntity> {

    // 删除标示
    private Boolean deleteFlag;
    // 创建人ID
    private String creatorId;
    // 创建人姓名
    private String creatorName;
    // 创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    // 修改人ID
    private String modifierId;
    // 修改人姓名
    private String modifierName;
    // 修改时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public T setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
        return (T) this;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public T setCreatorId(String creatorId) {
        this.creatorId = creatorId;
        return (T) this;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public T setCreatorName(String creatorName) {
        this.creatorName = creatorName;
        return (T) this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public T setCreateTime(Date createTime) {
        this.createTime = createTime;
        return (T) this;
    }

    public String getModifierId() {
        return modifierId;
    }

    public T setModifierId(String modifierId) {
        this.modifierId = modifierId;
        return (T) this;
    }

    public String getModifierName() {
        return modifierName;
    }

    public T setModifierName(String modifierName) {
        this.modifierName = modifierName;
        return (T) this;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public T setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
        return (T) this;
    }
}
