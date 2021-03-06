package com.kwbbin.bean;

import java.util.ArrayList;
import java.util.List;

public class DraftTagsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DraftTagsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andDraftIdIsNull() {
            addCriterion("draft_id is null");
            return (Criteria) this;
        }

        public Criteria andDraftIdIsNotNull() {
            addCriterion("draft_id is not null");
            return (Criteria) this;
        }

        public Criteria andDraftIdEqualTo(Long value) {
            addCriterion("draft_id =", value, "draftId");
            return (Criteria) this;
        }

        public Criteria andDraftIdNotEqualTo(Long value) {
            addCriterion("draft_id <>", value, "draftId");
            return (Criteria) this;
        }

        public Criteria andDraftIdGreaterThan(Long value) {
            addCriterion("draft_id >", value, "draftId");
            return (Criteria) this;
        }

        public Criteria andDraftIdGreaterThanOrEqualTo(Long value) {
            addCriterion("draft_id >=", value, "draftId");
            return (Criteria) this;
        }

        public Criteria andDraftIdLessThan(Long value) {
            addCriterion("draft_id <", value, "draftId");
            return (Criteria) this;
        }

        public Criteria andDraftIdLessThanOrEqualTo(Long value) {
            addCriterion("draft_id <=", value, "draftId");
            return (Criteria) this;
        }

        public Criteria andDraftIdIn(List<Long> values) {
            addCriterion("draft_id in", values, "draftId");
            return (Criteria) this;
        }

        public Criteria andDraftIdNotIn(List<Long> values) {
            addCriterion("draft_id not in", values, "draftId");
            return (Criteria) this;
        }

        public Criteria andDraftIdBetween(Long value1, Long value2) {
            addCriterion("draft_id between", value1, value2, "draftId");
            return (Criteria) this;
        }

        public Criteria andDraftIdNotBetween(Long value1, Long value2) {
            addCriterion("draft_id not between", value1, value2, "draftId");
            return (Criteria) this;
        }

        public Criteria andTagsIdIsNull() {
            addCriterion("tags_id is null");
            return (Criteria) this;
        }

        public Criteria andTagsIdIsNotNull() {
            addCriterion("tags_id is not null");
            return (Criteria) this;
        }

        public Criteria andTagsIdEqualTo(Integer value) {
            addCriterion("tags_id =", value, "tagsId");
            return (Criteria) this;
        }

        public Criteria andTagsIdNotEqualTo(Integer value) {
            addCriterion("tags_id <>", value, "tagsId");
            return (Criteria) this;
        }

        public Criteria andTagsIdGreaterThan(Integer value) {
            addCriterion("tags_id >", value, "tagsId");
            return (Criteria) this;
        }

        public Criteria andTagsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tags_id >=", value, "tagsId");
            return (Criteria) this;
        }

        public Criteria andTagsIdLessThan(Integer value) {
            addCriterion("tags_id <", value, "tagsId");
            return (Criteria) this;
        }

        public Criteria andTagsIdLessThanOrEqualTo(Integer value) {
            addCriterion("tags_id <=", value, "tagsId");
            return (Criteria) this;
        }

        public Criteria andTagsIdIn(List<Integer> values) {
            addCriterion("tags_id in", values, "tagsId");
            return (Criteria) this;
        }

        public Criteria andTagsIdNotIn(List<Integer> values) {
            addCriterion("tags_id not in", values, "tagsId");
            return (Criteria) this;
        }

        public Criteria andTagsIdBetween(Integer value1, Integer value2) {
            addCriterion("tags_id between", value1, value2, "tagsId");
            return (Criteria) this;
        }

        public Criteria andTagsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tags_id not between", value1, value2, "tagsId");
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

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

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