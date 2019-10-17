package com.el.common.source;

/**
 * @author ZhangJun
 * @Description: Http返回信息枚举
 * @date 2019年7月15日
 */
public enum REnum {

    /**
     * 操作成功
     */
    SUCCESS(0, ""),

    /**
     * 参数不能为空
     */
    ERROR_PARAM(10001, "参数不能为空！"),

    /**
     * 访问路径不存在
     */
    ERROR_PATH(10002, "访问路径不存在！"),

    /**
     * token为空
     */
    ERROR_TOKEN(10003, "获取Token错误！"),

    /**
     * token为空
     */
    NO_TOKEN(10004, "token为空！"),

    /**
     * token无效
     */
    TOKEN_INVALID(10005, "token无效"),

    /**
     * token已过期
     */
    TOKEN_HAS_EXPIRED(10006, "token已过期！"),

    /**
     * 数据库中已存在该记录
     */
    DATA_ALREADY_EXISTS(10007, "数据库中已存在该记录！"),

    /**
     * 数据库中已存在该记录
     */
    INSUFFICIENT_AUTHORITY(10008, "权限不足！"),


    /**
     * 用户名为空
     */
    NO_USER_NAME(20000, "用户名不能为空！"),

    /**
     * 密码为空
     */
    NO_PASSWORD(20001, "密码不能为空！"),

    /**
     * 用户名不存在
     */
    NO_USER(20002, "用户名不存在！"),

    /**
     * 用户名或密码错误
     */
    ERROR_USERNAME_OR_PASSWORD(20003, "用户名或密码错误！"),

    /**
     * 用户已禁用
     */
    USER_DISABLED(20004, "用户已禁用！"),

    /**
     * 登录失败
     */
    LOGIN_FAILED(20005, "登录失败请重试！"),

    /**
     * 登录异常
     */
    ERROR_LOGIN(20005, "登录异常！"),

    /**
     * 司机不存在
     */
    DRIVER_NOT_EXISTS(30000, "司机不存在！"),

    /**
     * 货车不存在
     */
    WAGON_NOT_EXISTS(30001, "货车不存在！"),

    /**
     * 车队不存在
     */
    WAGON_TEAM_NOT_EXISTS(30002, "车队不存在！"),

    /**
     * 围栏不存在
     */
    PLACE_NOT_EXISTS(30003, "围栏不存在！"),

    /**
     * 路线不存在
     */
    ROUTE_PLAN_NOT_EXISTS(30004, "路线不存在！"),

    /**
     * 车牌已存在
     */
    PLATE_NO_EXISTS(30005, "车辆已存在！"),

    /**
     * 警告事件id不存在
     */
    NO_WARNING_EVENT_ID(30006, "警告事件不存在！"),

    /**
     * 运单不存在
     */
    WAYBILL_NOT_EXISTS(30007, "运单不存在！"),

    /**
     * 车牌号为空
     */
    PLATE_NO_EMPTY(30008, "车牌号码不能为空！"),

    /**
     * 司机已存在
     */
    DRIVER_ALREADY_EXISTS(30009, "司机已存在！"),

    /**
     * 车队名称不能为空
     */
    N0_WAGON_TEAM_NAME(30010, "车队名称不能为空！"),

    /**
     * 车队信息已存在
     */
    WAGON_TEAM_ALREADY_EXISTS(30011, "车队信息已存在！"),

    /**
     * 路线不完整
     */
    ROUTE_PLAN_SET_ERR(300012, "请设置完整的路线！"),

    /**
     * 路线状态不正确
     */
    ROUTE_PLAN_STATUS_ERR(300013, "路线状态不正确！"),

    /**
     * 错误的文件格式
     */
    INCORRECT_FILE_FORMAT(40001, "错误的文件格式！"),

    /**
     * 文件不能为空
     */
    FILES_CANNOT_BE_EMPTY(40002, "文件不能为空！"),

    /**
     * 内容不能为空
     */
    CONTENT_CANNOT_BE_EMPTY(40003, "内容不能为空！"),

    /**
     * 错误的车辆信息模板
     */
    ERROR_WAGON_INFO_TEMPLATE(40004, "错误的车辆信息模板！"),

    /**
     * 错误的司机信息模板
     */
    ERROR_DRIVER_INFO_TEMPLATE(40005, "错误的司机信息模板！"),

    /**
     * 获取距离失败
     */
    ERROR_CALCULATED_DISTANCE(40006, "获取距离失败！"),

    /**
     * 未指定车队
     */
    NOT_APPOINT_WAGON_TEAM(40007, "未指定车队！"),

    /**
     * 未绑定车辆
     */
    NOT_BAND_WAGON(40008, "未绑定车辆！"),

    /**
     * 业务失败
     */
    OPERATION_FAILED(90000, "业务失败！"),

    /**
     * 更新失败
     */
    UPDATE_FAILED(90001, "更新失败！"),

    /**
     * 更新失败
     */
    DELETE_FAILED(90002, "删除失败！"),

    /**
     * 其他异常
     */
    OTHER_EXCEPTION(91000, "其他异常！"),

    /**
     * 数据格式错误
     */
    DATA_FORMAT_ERR(91001, "数据格式错误！"),

    /**
     * 数据格式错误
     */
    DATABASE_ERR(99001, "数据库完整性异常，通常由字段值重复导致！");


    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    REnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
