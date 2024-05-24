package cn.itmtx.ddd.ezlink.infrastructure.token.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccessTokenExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AccessTokenExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andAppIdIsNull() {
            addCriterion("app_id is null");
            return (Criteria) this;
        }

        public Criteria andAppIdIsNotNull() {
            addCriterion("app_id is not null");
            return (Criteria) this;
        }

        public Criteria andAppIdEqualTo(String value) {
            addCriterion("app_id =", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotEqualTo(String value) {
            addCriterion("app_id <>", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThan(String value) {
            addCriterion("app_id >", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdGreaterThanOrEqualTo(String value) {
            addCriterion("app_id >=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThan(String value) {
            addCriterion("app_id <", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLessThanOrEqualTo(String value) {
            addCriterion("app_id <=", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdLike(String value) {
            addCriterion("app_id like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotLike(String value) {
            addCriterion("app_id not like", value, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdIn(List<String> values) {
            addCriterion("app_id in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotIn(List<String> values) {
            addCriterion("app_id not in", values, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdBetween(String value1, String value2) {
            addCriterion("app_id between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppIdNotBetween(String value1, String value2) {
            addCriterion("app_id not between", value1, value2, "appId");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNull() {
            addCriterion("app_secret is null");
            return (Criteria) this;
        }

        public Criteria andAppSecretIsNotNull() {
            addCriterion("app_secret is not null");
            return (Criteria) this;
        }

        public Criteria andAppSecretEqualTo(String value) {
            addCriterion("app_secret =", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotEqualTo(String value) {
            addCriterion("app_secret <>", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThan(String value) {
            addCriterion("app_secret >", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretGreaterThanOrEqualTo(String value) {
            addCriterion("app_secret >=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThan(String value) {
            addCriterion("app_secret <", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLessThanOrEqualTo(String value) {
            addCriterion("app_secret <=", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretLike(String value) {
            addCriterion("app_secret like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotLike(String value) {
            addCriterion("app_secret not like", value, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretIn(List<String> values) {
            addCriterion("app_secret in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotIn(List<String> values) {
            addCriterion("app_secret not in", values, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretBetween(String value1, String value2) {
            addCriterion("app_secret between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAppSecretNotBetween(String value1, String value2) {
            addCriterion("app_secret not between", value1, value2, "appSecret");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNull() {
            addCriterion("access_token is null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNotNull() {
            addCriterion("access_token is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenEqualTo(String value) {
            addCriterion("access_token =", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotEqualTo(String value) {
            addCriterion("access_token <>", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThan(String value) {
            addCriterion("access_token >", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThanOrEqualTo(String value) {
            addCriterion("access_token >=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThan(String value) {
            addCriterion("access_token <", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThanOrEqualTo(String value) {
            addCriterion("access_token <=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLike(String value) {
            addCriterion("access_token like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotLike(String value) {
            addCriterion("access_token not like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIn(List<String> values) {
            addCriterion("access_token in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotIn(List<String> values) {
            addCriterion("access_token not in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenBetween(String value1, String value2) {
            addCriterion("access_token between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotBetween(String value1, String value2) {
            addCriterion("access_token not between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenExpireTimestampIsNull() {
            addCriterion("access_token_expire_timestamp is null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenExpireTimestampIsNotNull() {
            addCriterion("access_token_expire_timestamp is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenExpireTimestampEqualTo(Date value) {
            addCriterion("access_token_expire_timestamp =", value, "accessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andAccessTokenExpireTimestampNotEqualTo(Date value) {
            addCriterion("access_token_expire_timestamp <>", value, "accessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andAccessTokenExpireTimestampGreaterThan(Date value) {
            addCriterion("access_token_expire_timestamp >", value, "accessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andAccessTokenExpireTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("access_token_expire_timestamp >=", value, "accessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andAccessTokenExpireTimestampLessThan(Date value) {
            addCriterion("access_token_expire_timestamp <", value, "accessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andAccessTokenExpireTimestampLessThanOrEqualTo(Date value) {
            addCriterion("access_token_expire_timestamp <=", value, "accessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andAccessTokenExpireTimestampIn(List<Date> values) {
            addCriterion("access_token_expire_timestamp in", values, "accessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andAccessTokenExpireTimestampNotIn(List<Date> values) {
            addCriterion("access_token_expire_timestamp not in", values, "accessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andAccessTokenExpireTimestampBetween(Date value1, Date value2) {
            addCriterion("access_token_expire_timestamp between", value1, value2, "accessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andAccessTokenExpireTimestampNotBetween(Date value1, Date value2) {
            addCriterion("access_token_expire_timestamp not between", value1, value2, "accessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenIsNull() {
            addCriterion("old_access_token is null");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenIsNotNull() {
            addCriterion("old_access_token is not null");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenEqualTo(String value) {
            addCriterion("old_access_token =", value, "oldAccessToken");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenNotEqualTo(String value) {
            addCriterion("old_access_token <>", value, "oldAccessToken");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenGreaterThan(String value) {
            addCriterion("old_access_token >", value, "oldAccessToken");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenGreaterThanOrEqualTo(String value) {
            addCriterion("old_access_token >=", value, "oldAccessToken");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenLessThan(String value) {
            addCriterion("old_access_token <", value, "oldAccessToken");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenLessThanOrEqualTo(String value) {
            addCriterion("old_access_token <=", value, "oldAccessToken");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenLike(String value) {
            addCriterion("old_access_token like", value, "oldAccessToken");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenNotLike(String value) {
            addCriterion("old_access_token not like", value, "oldAccessToken");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenIn(List<String> values) {
            addCriterion("old_access_token in", values, "oldAccessToken");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenNotIn(List<String> values) {
            addCriterion("old_access_token not in", values, "oldAccessToken");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenBetween(String value1, String value2) {
            addCriterion("old_access_token between", value1, value2, "oldAccessToken");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenNotBetween(String value1, String value2) {
            addCriterion("old_access_token not between", value1, value2, "oldAccessToken");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenExpireTimestampIsNull() {
            addCriterion("old_access_token_expire_timestamp is null");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenExpireTimestampIsNotNull() {
            addCriterion("old_access_token_expire_timestamp is not null");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenExpireTimestampEqualTo(Date value) {
            addCriterion("old_access_token_expire_timestamp =", value, "oldAccessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenExpireTimestampNotEqualTo(Date value) {
            addCriterion("old_access_token_expire_timestamp <>", value, "oldAccessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenExpireTimestampGreaterThan(Date value) {
            addCriterion("old_access_token_expire_timestamp >", value, "oldAccessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenExpireTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("old_access_token_expire_timestamp >=", value, "oldAccessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenExpireTimestampLessThan(Date value) {
            addCriterion("old_access_token_expire_timestamp <", value, "oldAccessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenExpireTimestampLessThanOrEqualTo(Date value) {
            addCriterion("old_access_token_expire_timestamp <=", value, "oldAccessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenExpireTimestampIn(List<Date> values) {
            addCriterion("old_access_token_expire_timestamp in", values, "oldAccessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenExpireTimestampNotIn(List<Date> values) {
            addCriterion("old_access_token_expire_timestamp not in", values, "oldAccessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenExpireTimestampBetween(Date value1, Date value2) {
            addCriterion("old_access_token_expire_timestamp between", value1, value2, "oldAccessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andOldAccessTokenExpireTimestampNotBetween(Date value1, Date value2) {
            addCriterion("old_access_token_expire_timestamp not between", value1, value2, "oldAccessTokenExpireTimestamp");
            return (Criteria) this;
        }

        public Criteria andAvailableCountIsNull() {
            addCriterion("available_count is null");
            return (Criteria) this;
        }

        public Criteria andAvailableCountIsNotNull() {
            addCriterion("available_count is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableCountEqualTo(Integer value) {
            addCriterion("available_count =", value, "availableCount");
            return (Criteria) this;
        }

        public Criteria andAvailableCountNotEqualTo(Integer value) {
            addCriterion("available_count <>", value, "availableCount");
            return (Criteria) this;
        }

        public Criteria andAvailableCountGreaterThan(Integer value) {
            addCriterion("available_count >", value, "availableCount");
            return (Criteria) this;
        }

        public Criteria andAvailableCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("available_count >=", value, "availableCount");
            return (Criteria) this;
        }

        public Criteria andAvailableCountLessThan(Integer value) {
            addCriterion("available_count <", value, "availableCount");
            return (Criteria) this;
        }

        public Criteria andAvailableCountLessThanOrEqualTo(Integer value) {
            addCriterion("available_count <=", value, "availableCount");
            return (Criteria) this;
        }

        public Criteria andAvailableCountIn(List<Integer> values) {
            addCriterion("available_count in", values, "availableCount");
            return (Criteria) this;
        }

        public Criteria andAvailableCountNotIn(List<Integer> values) {
            addCriterion("available_count not in", values, "availableCount");
            return (Criteria) this;
        }

        public Criteria andAvailableCountBetween(Integer value1, Integer value2) {
            addCriterion("available_count between", value1, value2, "availableCount");
            return (Criteria) this;
        }

        public Criteria andAvailableCountNotBetween(Integer value1, Integer value2) {
            addCriterion("available_count not between", value1, value2, "availableCount");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeIsNull() {
            addCriterion("datachange_createtime is null");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeIsNotNull() {
            addCriterion("datachange_createtime is not null");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeEqualTo(Date value) {
            addCriterion("datachange_createtime =", value, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeNotEqualTo(Date value) {
            addCriterion("datachange_createtime <>", value, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeGreaterThan(Date value) {
            addCriterion("datachange_createtime >", value, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("datachange_createtime >=", value, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeLessThan(Date value) {
            addCriterion("datachange_createtime <", value, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("datachange_createtime <=", value, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeIn(List<Date> values) {
            addCriterion("datachange_createtime in", values, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeNotIn(List<Date> values) {
            addCriterion("datachange_createtime not in", values, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeBetween(Date value1, Date value2) {
            addCriterion("datachange_createtime between", value1, value2, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("datachange_createtime not between", value1, value2, "datachangeCreatetime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeIsNull() {
            addCriterion("datachange_lasttime is null");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeIsNotNull() {
            addCriterion("datachange_lasttime is not null");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeEqualTo(Date value) {
            addCriterion("datachange_lasttime =", value, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeNotEqualTo(Date value) {
            addCriterion("datachange_lasttime <>", value, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeGreaterThan(Date value) {
            addCriterion("datachange_lasttime >", value, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("datachange_lasttime >=", value, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeLessThan(Date value) {
            addCriterion("datachange_lasttime <", value, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeLessThanOrEqualTo(Date value) {
            addCriterion("datachange_lasttime <=", value, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeIn(List<Date> values) {
            addCriterion("datachange_lasttime in", values, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeNotIn(List<Date> values) {
            addCriterion("datachange_lasttime not in", values, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeBetween(Date value1, Date value2) {
            addCriterion("datachange_lasttime between", value1, value2, "datachangeLasttime");
            return (Criteria) this;
        }

        public Criteria andDatachangeLasttimeNotBetween(Date value1, Date value2) {
            addCriterion("datachange_lasttime not between", value1, value2, "datachangeLasttime");
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