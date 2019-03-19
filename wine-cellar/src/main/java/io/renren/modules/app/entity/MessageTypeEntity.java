package io.renren.modules.app.entity;

import io.renren.modules.cellar.entity.CellarMemberMessageDbEntity;

/**
 * 消息类型实体类
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2019-01-16 17:34:20
 */
public class MessageTypeEntity {

    /**
     * 消息类型
     */
    private Integer key;
    /**
     * 消息类型
     */
    private String value;
    /**
     * 未读数量
     */
    private Integer unReadCount;
    /**
     * 所有数量
     */
    private Integer allCount;
    /**
     * 会员最近一条消息
     */
    private CellarMemberMessageDbEntity cellarMemberMessageDbEntity;

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(Integer unReadCount) {
        this.unReadCount = unReadCount;
    }

    public Integer getAllCount() {
        return allCount;
    }

    public void setAllCount(Integer allCount) {
        this.allCount = allCount;
    }

    public CellarMemberMessageDbEntity getCellarMemberMessageDbEntity() {
        return cellarMemberMessageDbEntity;
    }

    public void setCellarMemberMessageDbEntity(CellarMemberMessageDbEntity cellarMemberMessageDbEntity) {
        this.cellarMemberMessageDbEntity = cellarMemberMessageDbEntity;
    }
}
