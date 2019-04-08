package com.sicau.entity.pojo.po;

import java.util.ArrayList;
import java.util.List;

public class DelayExamplePO {
    //升序或者降序
    protected String orderByClause;
    //去掉重复：true即为选择不重复记录
    protected boolean distinct;
    //自定义的部分查询条件
    protected List<Criteria> customCriteria;

    public DelayExamplePO() { customCriteria = new ArrayList<Criteria>(); }

    public void setOrderByClause(String orderByClause) { this.orderByClause = orderByClause; }

    public String getOrderByClause() { return orderByClause; }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getCustomCriteria() { return customCriteria; }

    public void or(Criteria criteria) {
        customCriteria.add(criteria);
    }

    /**
     * 创造查询条件
     * @return
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (customCriteria.size() == 0) {
            customCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }



    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        /**
         * 判断方法：判断DelayId不等于value时可用
         * @param value
         * @return
         */
        public Criteria andDelayIdNotEqualTo(String value) {
            addCriterion("delay_id <>", value, "delayId");
            return (Criteria) this;
        }

    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() { return condition; }

        public Object getValue() { return value; }

        public Object getSecondValue() { return secondValue; }

        public boolean isNoValue() { return noValue; }

        public boolean isSingleValue() { return singleValue; }

        public boolean isBetweenValue() { return betweenValue; }

        public boolean isListValue() { return listValue; }

        public String getTypeHandler() { return typeHandler; }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}