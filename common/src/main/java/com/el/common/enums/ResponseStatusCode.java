package com.el.common.enums;

/**
 * 中交兴路响应码
 */
public enum ResponseStatusCode {
    /**
     * 接口执行成功
     */
    SUCCESS("1001", "接口执行成功"),

    /**
     * 参数不正确（参数为空、查询时间范围不正确、参数数量不正确）
     */
    PARAMS_ERROR("1002", "参数不正确"),

    /**
     * 车辆调用数量已达上限
     */
    WAGON_UPPER_LIMIT("1003", "车辆调用数量已达上限"),

    /**
     * 接口调用次数已达上限
     */
    INTERFACE_UPPER_LIMIT("1004", "接口调用次数已达上限"),

    /**
     * 该 API 账号未授权指定所属行政区划数据范围
     */
    ACCOUNT_UNAUTHORIZED("1005", "该 API 账号未授权指定所属行政区划数据范围"),

    /**
     * 无结果
     */
    NO_RESULT("1006", "无结果"),

    /**
     * 用户名或密码不正确
     */
    INCORRECT_ACCOUNT_PASSWORD("1010", "用户名或密码不正确"),

    /**
     * IP 不在白名单列表
     */
    IP_NOT_ON_WHITE_LIST("1011", "IP 不在白名单列表"),

    /**
     * 账号已禁用
     */
    ACCOUNT_HAS_BEEN_DISABLE("1012", "账号已禁用"),

    /**
     * 账号已过有效期
     */
    ACCOUNT_EXPIRED("1013", "账号已过有效期"),

    /**
     * 无接口权限
     */
    NO_INTERFACE_PERMISSION ("1014", "无接口权限"),

    /**
     * 用户认证系统已升级，请使用令牌访问
     */
    SYSTEM_UPDATE("1015", "用户认证系统已升级，请使用令牌访问"),

    /**
     * 令牌失效
     */
    TOKEN_INVALID_EXPIRED("1016", "令牌失效"),

    /**
     * 账号欠费
     */
    ACCOUNT_OVERDUE("1017", "账号欠费"),

    /**
     * 授权的接口已禁用
     */
    INTERFACE_EXPIRED("1018", "授权的接口已禁用"),

    /**
     * 授权的接口已过期
     */
    INTERFACE_DISABLE("1019", "授权的接口已过期"),

    /**
     * 该车调用次数已达上限
     */
    THE_CAR_UPPER_LIMIT("1020", "该车调用次数已达上限"),

    /**
     * client_id 不正确
     */
    CLIENT_ID_ERROR("1021", "client_id 不正确"),

    /**
     * 签名验证失败
     */
    SIGN_ERROR("1031", "签名验证失败"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR("9001", "系统异常");

    private String code;
    private String desc;

    ResponseStatusCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDescBy(String code) {
        for (ResponseStatusCode statusCode : ResponseStatusCode.values()) {
            if (statusCode.getCode().equals(code)) {
                return statusCode.getDesc();
            }
        }
        return "";
    }
}