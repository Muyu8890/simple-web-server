package com.github.likeabook.webserver.mapper;

import com.github.likeabook.webserver.query.Query;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by 刘少年 on 2017/8/8.
 */
@Mapper
public interface BaseMapper<T> {

    @InsertProvider(type = BaseMapperProvider.class, method = "saveBatch")
    int saveBatch(Map<String, Object> param);
//    void saveBatch(Collection<T> entityCollection);
//    void saveBatch(@Param("entityCollection") Collection<T> entityCollection);

    @UpdateProvider(type = BaseMapperProvider.class, method = "update")
    int update(T entity);

    @UpdateProvider(type = BaseMapperProvider.class, method = "updateNotNull")
    int updateNotNull(T entity);


    @UpdateProvider(type = BaseMapperProvider.class, method = "logicDelete")
    void logicDelete(Map<String, Object> param);

    @SelectProvider(type = BaseMapperProvider.class, method = "get")
    T get(@Param("entityClass") Class<T> entityClass, @Param("id") final Serializable id);

    @SelectProvider(type = BaseMapperProvider.class, method = "find")
    T find(Map<String, Object> param);

    @SelectProvider(type = BaseMapperProvider.class, method = "findList")
    List<T> findList(Map<String, Object> param);

    @SelectProvider(type = BaseMapperProvider.class, method = "count")
    int count(Map<String, Object> param);

    @SelectProvider(type = BaseMapperProvider.class, method = "findPageList")
    List<T> findPageList(Map<String, Object> param);


}
