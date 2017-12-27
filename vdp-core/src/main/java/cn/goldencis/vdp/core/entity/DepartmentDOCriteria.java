package cn.goldencis.vdp.core.entity;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDOCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DepartmentDOCriteria() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkIsNull() {
            addCriterion("department_remark is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkIsNotNull() {
            addCriterion("department_remark is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkEqualTo(String value) {
            addCriterion("department_remark =", value, "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkNotEqualTo(String value) {
            addCriterion("department_remark <>", value, "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkGreaterThan(String value) {
            addCriterion("department_remark >", value, "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("department_remark >=", value, "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkLessThan(String value) {
            addCriterion("department_remark <", value, "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkLessThanOrEqualTo(String value) {
            addCriterion("department_remark <=", value, "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkLike(String value) {
            addCriterion("department_remark like", value, "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkNotLike(String value) {
            addCriterion("department_remark not like", value, "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkIn(List<String> values) {
            addCriterion("department_remark in", values, "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkNotIn(List<String> values) {
            addCriterion("department_remark not in", values, "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkBetween(String value1, String value2) {
            addCriterion("department_remark between", value1, value2, "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkNotBetween(String value1, String value2) {
            addCriterion("department_remark not between", value1, value2, "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNull() {
            addCriterion("owner is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIsNotNull() {
            addCriterion("owner is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerEqualTo(String value) {
            addCriterion("owner =", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotEqualTo(String value) {
            addCriterion("owner <>", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThan(String value) {
            addCriterion("owner >", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerGreaterThanOrEqualTo(String value) {
            addCriterion("owner >=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThan(String value) {
            addCriterion("owner <", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLessThanOrEqualTo(String value) {
            addCriterion("owner <=", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerLike(String value) {
            addCriterion("owner like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotLike(String value) {
            addCriterion("owner not like", value, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerIn(List<String> values) {
            addCriterion("owner in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotIn(List<String> values) {
            addCriterion("owner not in", values, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerBetween(String value1, String value2) {
            addCriterion("owner between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andOwnerNotBetween(String value1, String value2) {
            addCriterion("owner not between", value1, value2, "owner");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelIsNull() {
            addCriterion("department_tel is null");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelIsNotNull() {
            addCriterion("department_tel is not null");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelEqualTo(String value) {
            addCriterion("department_tel =", value, "departmentTel");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelNotEqualTo(String value) {
            addCriterion("department_tel <>", value, "departmentTel");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelGreaterThan(String value) {
            addCriterion("department_tel >", value, "departmentTel");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelGreaterThanOrEqualTo(String value) {
            addCriterion("department_tel >=", value, "departmentTel");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelLessThan(String value) {
            addCriterion("department_tel <", value, "departmentTel");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelLessThanOrEqualTo(String value) {
            addCriterion("department_tel <=", value, "departmentTel");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelLike(String value) {
            addCriterion("department_tel like", value, "departmentTel");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelNotLike(String value) {
            addCriterion("department_tel not like", value, "departmentTel");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelIn(List<String> values) {
            addCriterion("department_tel in", values, "departmentTel");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelNotIn(List<String> values) {
            addCriterion("department_tel not in", values, "departmentTel");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelBetween(String value1, String value2) {
            addCriterion("department_tel between", value1, value2, "departmentTel");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelNotBetween(String value1, String value2) {
            addCriterion("department_tel not between", value1, value2, "departmentTel");
            return (Criteria) this;
        }

        public Criteria andIpPartIsNull() {
            addCriterion("ip_part is null");
            return (Criteria) this;
        }

        public Criteria andIpPartIsNotNull() {
            addCriterion("ip_part is not null");
            return (Criteria) this;
        }

        public Criteria andIpPartEqualTo(String value) {
            addCriterion("ip_part =", value, "ipPart");
            return (Criteria) this;
        }

        public Criteria andIpPartNotEqualTo(String value) {
            addCriterion("ip_part <>", value, "ipPart");
            return (Criteria) this;
        }

        public Criteria andIpPartGreaterThan(String value) {
            addCriterion("ip_part >", value, "ipPart");
            return (Criteria) this;
        }

        public Criteria andIpPartGreaterThanOrEqualTo(String value) {
            addCriterion("ip_part >=", value, "ipPart");
            return (Criteria) this;
        }

        public Criteria andIpPartLessThan(String value) {
            addCriterion("ip_part <", value, "ipPart");
            return (Criteria) this;
        }

        public Criteria andIpPartLessThanOrEqualTo(String value) {
            addCriterion("ip_part <=", value, "ipPart");
            return (Criteria) this;
        }

        public Criteria andIpPartLike(String value) {
            addCriterion("ip_part like", value, "ipPart");
            return (Criteria) this;
        }

        public Criteria andIpPartNotLike(String value) {
            addCriterion("ip_part not like", value, "ipPart");
            return (Criteria) this;
        }

        public Criteria andIpPartIn(List<String> values) {
            addCriterion("ip_part in", values, "ipPart");
            return (Criteria) this;
        }

        public Criteria andIpPartNotIn(List<String> values) {
            addCriterion("ip_part not in", values, "ipPart");
            return (Criteria) this;
        }

        public Criteria andIpPartBetween(String value1, String value2) {
            addCriterion("ip_part between", value1, value2, "ipPart");
            return (Criteria) this;
        }

        public Criteria andIpPartNotBetween(String value1, String value2) {
            addCriterion("ip_part not between", value1, value2, "ipPart");
            return (Criteria) this;
        }

        public Criteria andTreePathIsNull() {
            addCriterion("tree_path is null");
            return (Criteria) this;
        }

        public Criteria andTreePathIsNotNull() {
            addCriterion("tree_path is not null");
            return (Criteria) this;
        }

        public Criteria andTreePathEqualTo(String value) {
            addCriterion("tree_path =", value, "treePath");
            return (Criteria) this;
        }

        public Criteria andTreePathNotEqualTo(String value) {
            addCriterion("tree_path <>", value, "treePath");
            return (Criteria) this;
        }

        public Criteria andTreePathGreaterThan(String value) {
            addCriterion("tree_path >", value, "treePath");
            return (Criteria) this;
        }

        public Criteria andTreePathGreaterThanOrEqualTo(String value) {
            addCriterion("tree_path >=", value, "treePath");
            return (Criteria) this;
        }

        public Criteria andTreePathLessThan(String value) {
            addCriterion("tree_path <", value, "treePath");
            return (Criteria) this;
        }

        public Criteria andTreePathLessThanOrEqualTo(String value) {
            addCriterion("tree_path <=", value, "treePath");
            return (Criteria) this;
        }

        public Criteria andTreePathLike(String value) {
            addCriterion("tree_path like", value, "treePath");
            return (Criteria) this;
        }

        public Criteria andTreePathNotLike(String value) {
            addCriterion("tree_path not like", value, "treePath");
            return (Criteria) this;
        }

        public Criteria andTreePathIn(List<String> values) {
            addCriterion("tree_path in", values, "treePath");
            return (Criteria) this;
        }

        public Criteria andTreePathNotIn(List<String> values) {
            addCriterion("tree_path not in", values, "treePath");
            return (Criteria) this;
        }

        public Criteria andTreePathBetween(String value1, String value2) {
            addCriterion("tree_path between", value1, value2, "treePath");
            return (Criteria) this;
        }

        public Criteria andTreePathNotBetween(String value1, String value2) {
            addCriterion("tree_path not between", value1, value2, "treePath");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andDepartmentRemarkLikeInsensitive(String value) {
            addCriterion("upper(department_remark) like", value.toUpperCase(), "departmentRemark");
            return (Criteria) this;
        }

        public Criteria andOwnerLikeInsensitive(String value) {
            addCriterion("upper(owner) like", value.toUpperCase(), "owner");
            return (Criteria) this;
        }

        public Criteria andDepartmentTelLikeInsensitive(String value) {
            addCriterion("upper(department_tel) like", value.toUpperCase(), "departmentTel");
            return (Criteria) this;
        }

        public Criteria andIpPartLikeInsensitive(String value) {
            addCriterion("upper(ip_part) like", value.toUpperCase(), "ipPart");
            return (Criteria) this;
        }

        public Criteria andTreePathLikeInsensitive(String value) {
            addCriterion("upper(tree_path) like", value.toUpperCase(), "treePath");
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