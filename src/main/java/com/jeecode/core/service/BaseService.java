package com.jeecode.core.service;

import com.jeecode.core.exception.CoreExceptionEnum;
import com.jeecode.core.exception.ErrorException;
import com.jeecode.core.mapper.BaseMapper;
import com.jeecode.core.query.Page;
import com.jeecode.core.query.Query;
import com.jeecode.core.query.ParamUtils;
import com.jeecode.core.util.EntityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * 增删改查的service层
 * @Description 校验，整理数据等都在这个类中
 * @version 1.0.0
 * 
 * @author 刘加胜
 * @date 2014-9-29
 * 
 */
//@Service("baseService")
@Transactional
public class BaseService<T, M extends BaseMapper<T>> {
	private static Logger logger = Logger.getLogger(BaseService.class);
    @Resource
    private M baseMapper;
    public  M getMapper(){
	    return baseMapper;
    }
	public int save(T entity) {
        List<T> entityList = new ArrayList<>();
        entityList.add(entity);
        return saveBatch(entityList, false);
    }
	public int saveBatch(Collection<T> entityCollection) {
        return saveBatch(entityCollection, true);
	}
    private int saveBatch(Collection<T> entityCollection, boolean batch) {
        if (CollectionUtils.isEmpty(entityCollection)) {
            return 0;
        }
        Date now = new Date();
        Field idField = null;
        List<Field> fieldList = null;
        int i = 0;
        String generateIdValue = now.getTime() + "_" + UUID.randomUUID().toString().replace("-", "");
        for (T entity : entityCollection) {
            fieldList = EntityUtils.getTableFieldList(entity.getClass());

            if (idField == null) {
                idField = EntityUtils.getIdField(entity.getClass());
            }
            // id
            Object idValue = EntityUtils.getValue(entity, idField);
            if (idField != null && idValue == null) {
                if (batch) {
                    idValue = generateIdValue + "_" + i;
                } else {
                    idValue = generateIdValue;
                }
                try {
                    idField.set(entity, idValue);
                } catch (IllegalAccessException e) {
                    throw new ErrorException(CoreExceptionEnum.CODE_84);
                }
                i++;
            }
        }
        i = 0;
        Map<String, Object> param = new HashMap<>();
        for (T entity : entityCollection) {
            for (Field field : fieldList) {
                param.put(field.getName() + "__" + i + "__", EntityUtils.getValue(entity, field));
            }
            i++;
        }
        param.put("entityCollection", entityCollection);
        // 设置id，创建人，修改人等信息
        return getMapper().saveBatch(param);
    }

    /**
     * 保存或更新，根据id判断，若不存在则新增，存在则更新
     * 更新操作会更新entity所有字段
     * @param entity
     * @return
     */
    public int saveOrUpdate(T entity){
        Object idValue = EntityUtils.getIdValue(entity);
        if (idValue == null) {
            save(entity);
            return 1;
        } else {
            return update(entity);
        }
    }

    /**
     * 保存或更新，根据id判断，若不存在则新增，存在则更新
     * 更新操作不包含非空字段
     * @param entity
     * @return
     */
    public int saveOrUpdateNotNull(T entity){
        Object idValue = EntityUtils.getIdValue(entity);
        if (idValue == null) {
            save(entity);
            return 1;
        } else {
            return updateNotNull(entity);
        }
    }
    /**
     * 更新操作会更新entity所有字段
     * @param entity
     * @return
     */
	public int update(T entity) {
		return getMapper().update(entity);
	}
    /**
     * 更新操作不包含非空字段
     * @param entity
     * @return
     */
	public int updateNotNull(T entity) {
		return getMapper().updateNotNull(entity);
	}
    public void delete(T entity) {
        // TODO
    }
    public void delete(Class<T> entityClass, Serializable id) {
        // TODO
    }
    public void logicDelete(T entityCondition, Query query) {
        getMapper().logicDelete(ParamUtils.getParamMap(entityCondition, query));
    }
    public void logicDelete(T entityCondition) {
        logicDelete(entityCondition, null);
    }
    public T get(Class<T> entityClass, Serializable id) {
	    if (id == null) {
	        return null;
        }
        return getMapper().get(entityClass, id);
    }
    public T find(T entityCondition) {
        return find(entityCondition, null);
    }
    public T find(T entityCondition, Query query) {
        return getMapper().find(ParamUtils.getParamMap(entityCondition, query));
    }
    public List<T> findList(T entityCondition) {
        return findList(entityCondition, null);
    }
    public List<T> findList(T entityCondition, Query query) {
        return getMapper().findList(ParamUtils.getParamMap(entityCondition, query));
    }
    public int count(T entityCondition) {
        return count(entityCondition, null);
    }
    public int count(T entityCondition, Query query) {
        return getMapper().count(ParamUtils.getParamMap(entityCondition, query));
    }
    public Page<T> findPage(T entityCondition, Query query) {
	    // 设置页数页码
        ParamUtils.setPageInfo(query);
        Map<String, Object> paramMap = ParamUtils.getParamMap(entityCondition, query);
        Page<T> page;
        int count = getMapper().count(paramMap);
        if (count == 0) {
            page = new Page<>(query.getPageNo(), query.getPageSize(), count, new ArrayList<>());
        } else {
            List<T> entityList = getMapper().findPageList(paramMap);
            page = new Page<>(query.getPageNo(), query.getPageSize(), count, entityList);
        }
		return page;
	}

}
