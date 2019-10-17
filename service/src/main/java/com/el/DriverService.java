package com.el;

import com.el.common.source.ResTO;
import com.el.common.to.DriverInfoTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 司机相关Service
 * @author: MaoYe
 * @create: 2019/09/11
 */
public interface DriverService {
    ResTO addDriver(DriverInfoTO to);

    ResTO updateDriver(DriverInfoTO to);

    ResTO delDriver(String driverId);

    ResTO loadDrivers(String name, String phoneNo, Pageable pageable);

    /**
     * @param deleted 是否删除
     * @return com.el.common.source.ResTO
     * @Description 获取所有可用的司机
     **/
    ResTO findAllByDeleted(Boolean deleted);

    /**
     * @param file
     * @return com.el.common.source.ResTO
     * @Description 批量导入司机
     **/
    ResTO batchImport(MultipartFile file);

}