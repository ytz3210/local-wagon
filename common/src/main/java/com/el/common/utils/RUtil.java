package com.el.common.utils;

import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.to.PageTO;
import org.springframework.data.domain.Page;

/**
 * @author ZhangJun
 * @Description: 对象模型工具类
 * @date 2019年7月16日
 */
@SuppressWarnings("Duplicates")
public class RUtil {

    /**
     * @return cn.everfort.common.entity.Result
     * @Author ZhangJun
     * @Description 不带数据的成功返回
     * @Date 2019-07-16 13:50
     **/
    public static ResTO success() {
        return success(null);
    }

    /**
     * @param object
     * @return cn.everfort.common.entity.Result
     * @Author ZhangJun
     * @Description 带数据的成功返回
     * @Date 2019-07-16 13:50
     **/
    public static ResTO success(Object object) {
        ResTO resTO = new ResTO();
        resTO.setCode(REnum.SUCCESS.getCode());
        resTO.setMessage(null);
        resTO.setData(object);
        return resTO;
    }

    /**
     * @param object
     * @return cn.everfort.common.entity.Result
     * @Author ZhangJun
     * @Description 分页的成功返回
     * @Date 2019-07-16 13:50
     **/
    public static ResTO successByPage(Object object) {
        ResTO resTO = new ResTO();
        resTO.setCode(REnum.SUCCESS.getCode());
        resTO.setMessage(null);

        Page p = (Page) object;
        PageTO pageTO = new PageTO();
        pageTO.setData(p.getContent());
        pageTO.setPageNo(p.getNumber());
        pageTO.setPageSize(p.getSize());
        pageTO.setTotalCount(p.getTotalElements());
        pageTO.setTotalPage(p.getTotalPages());
        resTO.setData(pageTO);
        return resTO;
    }

    /**
     * @param rEnum
     * @return com.el.wc.payment.funds.entity.Result
     * @Author ZhangJun
     * @Description 错误提示
     * @Date 2019/8/26 5:41 下午
     **/
    public static ResTO error(REnum rEnum) {
        ResTO resTO = new ResTO();
        resTO.setCode(rEnum.getCode());
        resTO.setMessage(rEnum.getMessage());
        resTO.setData(null);
        return resTO;
    }

    /**
     * @return Result
     * @Description: 错误类型
     * @Param type:错误类型
     * @Author ZhangJun
     * @Date 2019年7月16日 14:19
     **/
    public static ResTO errorByType(String type) {

        REnum rEnum = iterationFindByName(type);
        ResTO resTO = new ResTO();
        if (rEnum == null) {
            rEnum = REnum.valueOf("OTHER_EXCEPTION");
        }
        resTO.setCode(rEnum.getCode());
        resTO.setMessage(rEnum.getMessage());
        return resTO;
    }

    /**
     * @return Result
     * @Description: 错误类型
     * @Param type:错误类型
     * @Author ZhangJun
     * @Date 2019年7月16日 14:19
     **/
    public static ResTO errorByCode(Integer code) {

        REnum rEnum = iterationFindByCode(code);
        ResTO resTO = new ResTO();
        if (rEnum == null) {
            rEnum = REnum.valueOf("OTHER_EXCEPTION");
        }
        resTO.setCode(rEnum.getCode());
        resTO.setMessage(rEnum.getMessage());
        return resTO;
    }

    /**
     * @return
     * @Description: 根据名称迭代枚举类型
     * @Param
     * @Author ZhangJun
     * @Date 2019/07/16 14:20
     **/
    public static REnum iterationFindByName(String name) {
        for (REnum rEnum : REnum.values()) {
            if (name.equals(rEnum.name())) {
                return rEnum;
            }
        }
        return null;
    }

    /**
     * @return
     * @Description: 根据名称迭代枚举类型
     * @Param
     * @Author ZhangJun
     * @Date 2019/07/16 14:20
     **/
    public static REnum iterationFindByCode(Integer code) {
        for (REnum rEnum : REnum.values()) {
            if (rEnum.getCode().equals(code)) {
                return rEnum;
            }
        }
        return null;
    }

}
