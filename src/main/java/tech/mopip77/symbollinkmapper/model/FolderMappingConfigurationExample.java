package tech.mopip77.symbollinkmapper.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FolderMappingConfigurationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FolderMappingConfigurationExample() {
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

        public Criteria andExcludedRegxIsNull() {
            addCriterion("excluded_regx is null");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxIsNotNull() {
            addCriterion("excluded_regx is not null");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxEqualTo(String value) {
            addCriterion("excluded_regx =", value, "excludedRegx");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxNotEqualTo(String value) {
            addCriterion("excluded_regx <>", value, "excludedRegx");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxGreaterThan(String value) {
            addCriterion("excluded_regx >", value, "excludedRegx");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxGreaterThanOrEqualTo(String value) {
            addCriterion("excluded_regx >=", value, "excludedRegx");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxLessThan(String value) {
            addCriterion("excluded_regx <", value, "excludedRegx");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxLessThanOrEqualTo(String value) {
            addCriterion("excluded_regx <=", value, "excludedRegx");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxLike(String value) {
            addCriterion("excluded_regx like", value, "excludedRegx");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxNotLike(String value) {
            addCriterion("excluded_regx not like", value, "excludedRegx");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxIn(List<String> values) {
            addCriterion("excluded_regx in", values, "excludedRegx");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxNotIn(List<String> values) {
            addCriterion("excluded_regx not in", values, "excludedRegx");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxBetween(String value1, String value2) {
            addCriterion("excluded_regx between", value1, value2, "excludedRegx");
            return (Criteria) this;
        }

        public Criteria andExcludedRegxNotBetween(String value1, String value2) {
            addCriterion("excluded_regx not between", value1, value2, "excludedRegx");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingIsNull() {
            addCriterion("auto_re_mapping is null");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingIsNotNull() {
            addCriterion("auto_re_mapping is not null");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingEqualTo(Boolean value) {
            addCriterion("auto_re_mapping =", value, "autoReMapping");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingNotEqualTo(Boolean value) {
            addCriterion("auto_re_mapping <>", value, "autoReMapping");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingGreaterThan(Boolean value) {
            addCriterion("auto_re_mapping >", value, "autoReMapping");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingGreaterThanOrEqualTo(Boolean value) {
            addCriterion("auto_re_mapping >=", value, "autoReMapping");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingLessThan(Boolean value) {
            addCriterion("auto_re_mapping <", value, "autoReMapping");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingLessThanOrEqualTo(Boolean value) {
            addCriterion("auto_re_mapping <=", value, "autoReMapping");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingIn(List<Boolean> values) {
            addCriterion("auto_re_mapping in", values, "autoReMapping");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingNotIn(List<Boolean> values) {
            addCriterion("auto_re_mapping not in", values, "autoReMapping");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingBetween(Boolean value1, Boolean value2) {
            addCriterion("auto_re_mapping between", value1, value2, "autoReMapping");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingNotBetween(Boolean value1, Boolean value2) {
            addCriterion("auto_re_mapping not between", value1, value2, "autoReMapping");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingPeriodIsNull() {
            addCriterion("auto_re_mapping_period is null");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingPeriodIsNotNull() {
            addCriterion("auto_re_mapping_period is not null");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingPeriodEqualTo(Integer value) {
            addCriterion("auto_re_mapping_period =", value, "autoReMappingPeriod");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingPeriodNotEqualTo(Integer value) {
            addCriterion("auto_re_mapping_period <>", value, "autoReMappingPeriod");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingPeriodGreaterThan(Integer value) {
            addCriterion("auto_re_mapping_period >", value, "autoReMappingPeriod");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("auto_re_mapping_period >=", value, "autoReMappingPeriod");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingPeriodLessThan(Integer value) {
            addCriterion("auto_re_mapping_period <", value, "autoReMappingPeriod");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("auto_re_mapping_period <=", value, "autoReMappingPeriod");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingPeriodIn(List<Integer> values) {
            addCriterion("auto_re_mapping_period in", values, "autoReMappingPeriod");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingPeriodNotIn(List<Integer> values) {
            addCriterion("auto_re_mapping_period not in", values, "autoReMappingPeriod");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingPeriodBetween(Integer value1, Integer value2) {
            addCriterion("auto_re_mapping_period between", value1, value2, "autoReMappingPeriod");
            return (Criteria) this;
        }

        public Criteria andAutoReMappingPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("auto_re_mapping_period not between", value1, value2, "autoReMappingPeriod");
            return (Criteria) this;
        }

        public Criteria andGmtLastMappingIsNull() {
            addCriterion("gmt_last_mapping is null");
            return (Criteria) this;
        }

        public Criteria andGmtLastMappingIsNotNull() {
            addCriterion("gmt_last_mapping is not null");
            return (Criteria) this;
        }

        public Criteria andGmtLastMappingEqualTo(Date value) {
            addCriterion("gmt_last_mapping =", value, "gmtLastMapping");
            return (Criteria) this;
        }

        public Criteria andGmtLastMappingNotEqualTo(Date value) {
            addCriterion("gmt_last_mapping <>", value, "gmtLastMapping");
            return (Criteria) this;
        }

        public Criteria andGmtLastMappingGreaterThan(Date value) {
            addCriterion("gmt_last_mapping >", value, "gmtLastMapping");
            return (Criteria) this;
        }

        public Criteria andGmtLastMappingGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_last_mapping >=", value, "gmtLastMapping");
            return (Criteria) this;
        }

        public Criteria andGmtLastMappingLessThan(Date value) {
            addCriterion("gmt_last_mapping <", value, "gmtLastMapping");
            return (Criteria) this;
        }

        public Criteria andGmtLastMappingLessThanOrEqualTo(Date value) {
            addCriterion("gmt_last_mapping <=", value, "gmtLastMapping");
            return (Criteria) this;
        }

        public Criteria andGmtLastMappingIn(List<Date> values) {
            addCriterion("gmt_last_mapping in", values, "gmtLastMapping");
            return (Criteria) this;
        }

        public Criteria andGmtLastMappingNotIn(List<Date> values) {
            addCriterion("gmt_last_mapping not in", values, "gmtLastMapping");
            return (Criteria) this;
        }

        public Criteria andGmtLastMappingBetween(Date value1, Date value2) {
            addCriterion("gmt_last_mapping between", value1, value2, "gmtLastMapping");
            return (Criteria) this;
        }

        public Criteria andGmtLastMappingNotBetween(Date value1, Date value2) {
            addCriterion("gmt_last_mapping not between", value1, value2, "gmtLastMapping");
            return (Criteria) this;
        }

        public Criteria andSymbolLinkTypeIsNull() {
            addCriterion("symbol_link_type is null");
            return (Criteria) this;
        }

        public Criteria andSymbolLinkTypeIsNotNull() {
            addCriterion("symbol_link_type is not null");
            return (Criteria) this;
        }

        public Criteria andSymbolLinkTypeEqualTo(Integer value) {
            addCriterion("symbol_link_type =", value, "symbolLinkType");
            return (Criteria) this;
        }

        public Criteria andSymbolLinkTypeNotEqualTo(Integer value) {
            addCriterion("symbol_link_type <>", value, "symbolLinkType");
            return (Criteria) this;
        }

        public Criteria andSymbolLinkTypeGreaterThan(Integer value) {
            addCriterion("symbol_link_type >", value, "symbolLinkType");
            return (Criteria) this;
        }

        public Criteria andSymbolLinkTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("symbol_link_type >=", value, "symbolLinkType");
            return (Criteria) this;
        }

        public Criteria andSymbolLinkTypeLessThan(Integer value) {
            addCriterion("symbol_link_type <", value, "symbolLinkType");
            return (Criteria) this;
        }

        public Criteria andSymbolLinkTypeLessThanOrEqualTo(Integer value) {
            addCriterion("symbol_link_type <=", value, "symbolLinkType");
            return (Criteria) this;
        }

        public Criteria andSymbolLinkTypeIn(List<Integer> values) {
            addCriterion("symbol_link_type in", values, "symbolLinkType");
            return (Criteria) this;
        }

        public Criteria andSymbolLinkTypeNotIn(List<Integer> values) {
            addCriterion("symbol_link_type not in", values, "symbolLinkType");
            return (Criteria) this;
        }

        public Criteria andSymbolLinkTypeBetween(Integer value1, Integer value2) {
            addCriterion("symbol_link_type between", value1, value2, "symbolLinkType");
            return (Criteria) this;
        }

        public Criteria andSymbolLinkTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("symbol_link_type not between", value1, value2, "symbolLinkType");
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