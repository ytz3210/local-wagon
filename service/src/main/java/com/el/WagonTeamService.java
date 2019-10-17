package com.el;

import com.el.common.source.ResTO;
import com.el.common.to.WagonTeamTO;
import org.springframework.data.domain.Pageable;

/**
 * @description: 车队相关Service
 * @author: MaoYe
 * @create: 2019/09/11
 */
public interface WagonTeamService {
    ResTO addMotorcade(WagonTeamTO to);

    ResTO updateMotorcade(WagonTeamTO to);

    ResTO delMotorcade(String MotorcadeId);

    ResTO loadMotorcades(String name, String contactPhone, Pageable pageable);

    /**
     * @param deleted 是否删除
     * @return com.el.common.source.ResTO
     * @Description 获取所有的车队信息
     **/
    ResTO findAllByDeleted(Boolean deleted);
}