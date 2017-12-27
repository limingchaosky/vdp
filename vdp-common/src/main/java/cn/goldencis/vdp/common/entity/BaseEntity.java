package cn.goldencis.vdp.common.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 *
 * @author liuzhh
 *
 */
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract String getPrimaryKey();

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
