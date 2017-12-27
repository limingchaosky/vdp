/**
 *
 */
package cn.goldencis.vdp.common.service.impl;

import cn.goldencis.vdp.common.dao.BaseDao;
import cn.goldencis.vdp.common.entity.BaseEntity;
import cn.goldencis.vdp.common.service.BaseService;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 *
 * @param <T>
 * @param <C><br/>
 * @author liuzhh.
 * @date Oct 11, 2016
 * @version 1.0.0-SNAPSHOT
 */
public abstract class AbstractBaseServiceImpl<T extends BaseEntity, C> implements BaseService<T, C> {

    protected abstract BaseDao<T, C> getDao();

    public T create(T entity) {
        getDao().insert(entity);
        return entity;
    }

    public T createSelective(T entity) {
        getDao().insertSelective(entity);
        return entity;
    }

    public void update(T entity) {
        getDao().updateByPrimaryKey(entity);
    }

    public void updateWithBlob(T entity) {
        getDao().updateByPrimaryKeyWithBLOBs(entity);
    }

    public void updateByExampleSelective(T entity, C example) {
        getDao().updateByExampleSelective(entity, example);
    }

    public void updateByExample(T entity, C example) {
        getDao().updateByExample(entity, example);
    }

    public void update(List<T> list) {
        if (list != null && !list.isEmpty()) {
            for (T t : list) {
                update(t);
            }
        }
    }

    public void create(List<T> list) {
        if (list != null && !list.isEmpty()) {
            for (T t : list) {
                create(t);
            }
        }
    }

    public T getById(long id) {
        return this.getDao().selectByPrimaryKey(id);
    }

    /**
     * 根据ID删除记录.
     *
     * @param id .
     */
    public void deleteByPrimaryKey(String primaryKey) {
        this.getDao().deleteByPrimaryKey(primaryKey);
    }

    /**
     * 分页查询 .
     *
     * @param pageParam 分页参数.
     * @param paramMap 业务条件查询参数.
     * @return
     */
    public List<T> listPage(int page, int limit, C example) {
        if (page < 1) {
            throw new IllegalArgumentException("offset should start from 1.");
        }
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return this.getDao().selectByExampleWithRowbounds(example, rowBounds);
    }

    /**
     * 根据条件查询 listBy: <br/>
     *
     * @param example
     * @return 返回集合
     */
    public List<T> listBy(C example) {
        return this.getDao().selectByExample(example);
    }

    /**
     * 根据条件查询 listPageWithBLOBsBy: <br/>
     *
     * @param example
     * @return 返回集合
     */
    public List<T> listPageWithBLOBsBy(int page, int limit, C example) {
        if (page < 1) {
            throw new IllegalArgumentException("offset should start from 1.");
        }
        RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
        return this.getDao().selectByExampleWithBLOBsWithRowbounds(example, rowBounds);
    }

    /**
     * 根据条件查询 listWithBLOBsBy: <br/>
     *
     * @param example
     * @return 返回集合
     */
    public List<T> listWithBLOBsBy(C example) {
        return this.getDao().selectByExampleWithBLOBs(example);
    }

    /**
     *
     */
    public T getBy(C example) {
        List<T> results = this.getDao().selectByExample(example);
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    /**
     *
     */
    public T getByWithBlob(C example) {
        List<T> results = this.getDao().selectByExampleWithBLOBs(example);
        if (results != null && !results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    public boolean isExists(String primaryKey) {
        return (getByPrimaryKey(primaryKey) != null);
    }

    @Override
    public T getByPrimaryKey(String primaryKey) {
        return getDao().selectByPrimaryKey(primaryKey);
    }

}
