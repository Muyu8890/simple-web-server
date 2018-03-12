package com.github.likeabook.webserver.service;

import com.github.likeabook.webserver.exception.CoreExceptionEnum;
import com.github.likeabook.webserver.exception.ErrorException;
import com.github.likeabook.webserver.mapper.BaseMapper;
import com.github.likeabook.webserver.query.Page;
import com.github.likeabook.webserver.query.Query;
import com.github.likeabook.webserver.query.ParamUtils;
import com.github.likeabook.webserver.util.EntityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

public abstract class BaseService<T, M extends BaseMapper<T>> {
	private static Logger logger = Logger.getLogger(BaseService.class);

    public abstract M getMapper();

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

    public int saveOrUpdate(T entity){
        Object idValue = EntityUtils.getIdValue(entity);
        if (idValue == null) {
            save(entity);
            return 1;
        } else {
            return update(entity);
        }
    }

    public int saveOrUpdateNotNull(T entity){
        Object idValue = EntityUtils.getIdValue(entity);
        if (idValue == null) {
            save(entity);
            return 1;
        } else {
            return updateNotNull(entity);
        }
    }
    public int update(T entity) {
		return getMapper().update(entity);
	}
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
