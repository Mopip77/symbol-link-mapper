package tech.mopip77.symbollinkmapper.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SymbolLinkReferenceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SymbolLinkReferenceExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSourcePathIsNull() {
            addCriterion("source_path is null");
            return (Criteria) this;
        }

        public Criteria andSourcePathIsNotNull() {
            addCriterion("source_path is not null");
            return (Criteria) this;
        }

        public Criteria andSourcePathEqualTo(String value) {
            addCriterion("source_path =", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathNotEqualTo(String value) {
            addCriterion("source_path <>", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathGreaterThan(String value) {
            addCriterion("source_path >", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathGreaterThanOrEqualTo(String value) {
            addCriterion("source_path >=", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathLessThan(String value) {
            addCriterion("source_path <", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathLessThanOrEqualTo(String value) {
            addCriterion("source_path <=", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathLike(String value) {
            addCriterion("source_path like", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathNotLike(String value) {
            addCriterion("source_path not like", value, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathIn(List<String> values) {
            addCriterion("source_path in", values, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathNotIn(List<String> values) {
            addCriterion("source_path not in", values, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathBetween(String value1, String value2) {
            addCriterion("source_path between", value1, value2, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andSourcePathNotBetween(String value1, String value2) {
            addCriterion("source_path not between", value1, value2, "sourcePath");
            return (Criteria) this;
        }

        public Criteria andDestPathIsNull() {
            addCriterion("dest_path is null");
            return (Criteria) this;
        }

        public Criteria andDestPathIsNotNull() {
            addCriterion("dest_path is not null");
            return (Criteria) this;
        }

        public Criteria andDestPathEqualTo(String value) {
            addCriterion("dest_path =", value, "destPath");
            return (Criteria) this;
        }

        public Criteria andDestPathNotEqualTo(String value) {
            addCriterion("dest_path <>", value, "destPath");
            return (Criteria) this;
        }

        public Criteria andDestPathGreaterThan(String value) {
            addCriterion("dest_path >", value, "destPath");
            return (Criteria) this;
        }

        public Criteria andDestPathGreaterThanOrEqualTo(String value) {
            addCriterion("dest_path >=", value, "destPath");
            return (Criteria) this;
        }

        public Criteria andDestPathLessThan(String value) {
            addCriterion("dest_path <", value, "destPath");
            return (Criteria) this;
        }

        public Criteria andDestPathLessThanOrEqualTo(String value) {
            addCriterion("dest_path <=", value, "destPath");
            return (Criteria) this;
        }

        public Criteria andDestPathLike(String value) {
            addCriterion("dest_path like", value, "destPath");
            return (Criteria) this;
        }

        public Criteria andDestPathNotLike(String value) {
            addCriterion("dest_path not like", value, "destPath");
            return (Criteria) this;
        }

        public Criteria andDestPathIn(List<String> values) {
            addCriterion("dest_path in", values, "destPath");
            return (Criteria) this;
        }

        public Criteria andDestPathNotIn(List<String> values) {
            addCriterion("dest_path not in", values, "destPath");
            return (Criteria) this;
        }

        public Criteria andDestPathBetween(String value1, String value2) {
            addCriterion("dest_path between", value1, value2, "destPath");
            return (Criteria) this;
        }

        public Criteria andDestPathNotBetween(String value1, String value2) {
            addCriterion("dest_path not between", value1, value2, "destPath");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andIsFolderIsNull() {
            addCriterion("is_folder is null");
            return (Criteria) this;
        }

        public Criteria andIsFolderIsNotNull() {
            addCriterion("is_folder is not null");
            return (Criteria) this;
        }

        public Criteria andIsFolderEqualTo(Boolean value) {
            addCriterion("is_folder =", value, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderNotEqualTo(Boolean value) {
            addCriterion("is_folder <>", value, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderGreaterThan(Boolean value) {
            addCriterion("is_folder >", value, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_folder >=", value, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderLessThan(Boolean value) {
            addCriterion("is_folder <", value, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderLessThanOrEqualTo(Boolean value) {
            addCriterion("is_folder <=", value, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderIn(List<Boolean> values) {
            addCriterion("is_folder in", values, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderNotIn(List<Boolean> values) {
            addCriterion("is_folder not in", values, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderBetween(Boolean value1, Boolean value2) {
            addCriterion("is_folder between", value1, value2, "isFolder");
            return (Criteria) this;
        }

        public Criteria andIsFolderNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_folder not between", value1, value2, "isFolder");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
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