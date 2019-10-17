package com.el;

import com.el.common.source.ResTO;
import com.el.common.to.WagonInfoTO;
import com.el.common.to.request.WagonRequestTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 货车相关Service
 * @author: MaoYe
 * @create: 2019/09/11
 */
public interface WagonService {
    ResTO addWagon(WagonInfoTO to);

    ResTO updateWagon(WagonInfoTO to);

    ResTO delWagon(String wagonId);

    ResTO loadWagons(WagonRequestTO wagonRequestTO) throws Exception;

    ResTO getAllWagons();

    /**
     * @param file
     * @return com.el.common.source.ResTO
     * @Description 批量导入车辆信息
     **/
    ResTO batchImport(MultipartFile file);

}