package com.github.likeabook.webserver.query;


import com.github.likeabook.webserver.util.EntityUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 将条件转换为sql
 * Created by 刘少年 on 2017/8/18.
 */
public class SqlUtils {


    public static void main(String [] args) {

        List<String> stringList = new ArrayList<>();

        stringList.add("a, b");
        stringList.add("c");
        String str = StringUtils.join(stringList.toArray(), ", ");
        System.out.println(str);

        System.out.println("t.userId".replaceAll(".", "__"));

    }


    /**
     * 拼接select和from部分
     * @param entityClass
     * @param query
     * @return
     */
    public static String getSelectAndFrom(Class entityClass, Query query){
        String columns = "t.*";
        if (query != null && CollectionUtils.isNotEmpty(query.selectColumnList)){
            columns = StringUtils.join(query.selectColumnList.toArray(), ", ");
        }
        return "select " + columns + " from " + EntityUtils.getTableName(entityClass) + " t ";
    }

    public static String getSelectCountAndFrom(Class entityClass){
        String idField = EntityUtils.getIdField(entityClass).getName();
        return "select count(" + idField + ") from " + EntityUtils.getTableName(entityClass) + " t ";
    }


    public static String getJoinAndOnCondition(Query query){
        if (query == null) {
            return "";
        }
        StringBuffer resultBuffer = new StringBuffer();
        query.joinList.forEach(join -> {
            resultBuffer.append(join.joinType.getValue() + join.tableNameAndOnConditions + " ");
        });
        return resultBuffer.toString();
    }



    /**
     * 拼接where条件
     * @param entityCondition 对象查询条件，属性不为空拼接到条件中
     * @param query
     * @return
     */
    public static String getWhere(Object entityCondition, Query query){
        StringBuffer resultBuffer = new StringBuffer(" where 1=1 ");
        if (entityCondition != null) {
            String where = "";
            List<Field> fieldList = EntityUtils.getTableFieldList(entityCondition.getClass());
            for (Field field : fieldList) {
                Object value = EntityUtils.getValue(entityCondition, field);
                if (value != null) {
                    where += " and t." + field.getName() + " = #{" + ParamUtils.ENTITY_CONDITION + "." + field.getName() + "}";
                }
            }
            resultBuffer.append(where);
        }
        if (query != null) {
            // query.whereList
            if (CollectionUtils.isNotEmpty(query.whereList)) {
                String where = StringUtils.join(query.whereList.toArray(), ") and (");
                resultBuffer.append(" and (").append(where).append(")");
            }
            // query.inCondition
            query.inList.forEach(inCondition -> {
                if (CollectionUtils.isEmpty(inCondition.param)) {
                    resultBuffer.append(" and (1=0) ");
                } else {
                    // #{_inCondition_.t__userId[0]}, #{_inCondition_.t__userId[1]}
                    String column = inCondition.column.replaceAll("\\.", "__");
                    List<String> inParamList = new ArrayList<>();
                    for (int i=0; i<inCondition.param.size(); i++) {
                        inParamList.add("#{" + ParamUtils.IN_CONDITION + "." + column + "[" + i + "]}");
                    }
                    String inSql = " and " + inCondition.column + " in (" + StringUtils.join(inParamList.toArray(), ", ") + ") ";
                    resultBuffer.append(inSql);
                }
            });

        }
        return resultBuffer.toString();
    }

    public static String getOrderBy(Query query){
        if (query != null && CollectionUtils.isNotEmpty(query.orderByList)) {
            return " ORDER BY " + StringUtils.join(query.orderByList.toArray(), ", ") + " ";
        } else {
            return "";
        }
    }

    public static String getGroupBy(Query query){
        if (query != null && StringUtils.isNotBlank(query.groupBy)) {
            return " GROUP BY " + query.groupBy + " ";
        } else {
            return "";
        }
    }

    public static String getLimit(Query query){
        return " limit "+ (query.pageNo - 1) * query.pageSize +", " + query.pageSize + " ";
    }



}
