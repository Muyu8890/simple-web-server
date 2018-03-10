package com.jeecode.core.query;

import java.util.*;

/**
 * 查询参数类
 * 复杂查询参数类
 * Created by 刘少年 on 2017/8/9.
 */
public class Query {

    protected Integer pageNo;
    protected Integer pageSize;

    final protected List<String> selectColumnList = new ArrayList<>();
    final protected List<String> whereList = new ArrayList<>();
    final protected List<InCondition> inList = new ArrayList<>(); // TODO
    final protected List<String> orderByList = new ArrayList<>();
    protected String groupBy = "";
    final protected List<Join> joinList = new ArrayList<>();
    final protected Map<String, Object> param = new HashMap<>();

    public Query addParam(String paramName, Object paramValue){
        param.put(paramName, paramValue);
        return this;
    }

    public Query addSelectColumn(String selectColumn){
        selectColumnList.add(selectColumn);
        return this;
    }


    /**
     * 添加where条件
     * @param where 条件字符串
     * @return
     */
    public Query addWhere(String where){
        whereList.add(where);
        return this;
    }

    /**
     * 添加in条件
     * @param column 字段名
     * @param param 集合
     * @return
     */
    public Query addIn(String column, Collection param){
        inList.add(new InCondition(column, param));
        return this;
    }

    /**
     * 添加排序
     * @param columns 字段名，可以多个字段","分割
     * @return
     */
    public Query addOrderBy(String columns){
        orderByList.add(columns);
        return this;
    }

    /**
     * 添加分组
     * @param columns 字段名，可以多个字段","分割
     * @return
     */
    public Query addGroupBy(String columns){
        groupBy = columns;
        return this;
    }


    /**
     * 添加表关联
     * @return
     */
    public Query addJoin(JoinType joinType,
                         String tableNameAndOnConditions){
        joinList.add(new Join(joinType, tableNameAndOnConditions));
        return this;
    }

    ///////////////////////////////
    public Query setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public Query setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    ///////////////////////////////
    /**
     * 连接类型
     */
    public enum JoinType{
        JOIN(" JOIN "),
        LEFT_JOIN(" LEFT JOIN "),
        RIGHT_JOIN(" RIGHT JOIN "),
        ;
        String value;
        JoinType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * in查询条件
     */
    protected class InCondition{
        protected String column;
        protected Collection param;

        public InCondition(String column, Collection param) {
            this.column = column;
            this.param = param;
        }
    }

    protected class Join {
        protected JoinType joinType;
        protected String tableNameAndOnConditions;

        private Join(JoinType joinType, String tableNameAndOnConditions) {
            this.joinType = joinType;
            this.tableNameAndOnConditions = tableNameAndOnConditions;
        }
    }

}
