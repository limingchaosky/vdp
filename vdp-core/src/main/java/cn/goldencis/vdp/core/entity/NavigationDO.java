package cn.goldencis.vdp.core.entity;

import cn.goldencis.vdp.common.entity.BaseEntity;

import java.io.Serializable;

public class NavigationDO extends BaseEntity implements Serializable {
    private String id;

    private Integer compositor;

    private String title;

    private String url;

    private Integer parentId;

    private String iconUrl;

    private Integer nLevel;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCompositor() {
        return compositor;
    }

    public void setCompositor(Integer compositor) {
        this.compositor = compositor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public Integer getnLevel() {
        return nLevel;
    }

    public void setnLevel(Integer nLevel) {
        this.nLevel = nLevel;
    }

    public String getPrimaryKey() {
        return this.id;
    }
}