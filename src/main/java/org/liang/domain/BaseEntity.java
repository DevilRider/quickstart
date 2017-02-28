package org.liang.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

public abstract class BaseEntity<ID> implements Serializable {
    
    private static final long serialVersionUID = 5025123186898659461L;

    @Id
    private ID id;

    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;

    private Date createtime;

    private Date updatetime;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

}
