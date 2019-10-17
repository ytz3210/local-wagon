package com.el.controller;

import com.el.DriverService;
import com.el.annotation.SysLog;
import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.to.DriverInfoTO;
import com.el.common.utils.RUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 司机相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@RestController
@RequestMapping(value = "/api")
public class DriverController extends BaseApiController {

    @Autowired
    DriverService driverService;

    /**
     * 增加司机
     *
     * @return
     */
    @SysLog("新增司机")
    @PutMapping("/driver")
    public ResTO addDriver(@RequestBody DriverInfoTO to) {
        return driverService.addDriver(to);
    }

    /**
     * 修改司机
     *
     * @return
     */
    @SysLog("修改司机")
    @PostMapping("/driver")
    public ResTO editDriver(@RequestBody DriverInfoTO to) {
        return driverService.updateDriver(to);
    }

    /**
     * 删除司机
     *
     * @return
     */
    @SysLog("删除司机")
    @DeleteMapping("/driver")
    public ResTO delDriver(@RequestParam String id) {
        return driverService.delDriver(id);
    }

    /**
     * 加载所有司机
     *
     * @return
     */
    @GetMapping("/drivers")
    public ResTO loadDrivers(@RequestParam(required = false) String name,
                             @RequestParam(required = false) String phoneNo,
                             @RequestParam Integer pageNo,
                             @RequestParam Integer pageSize) {
        return driverService.loadDrivers(name, phoneNo, PageRequest.of(pageNo - 1, pageSize));
    }

    /**
     * @return java.lang.String
     * @Description 获取所有的司机
     **/
    @GetMapping("/all_drivers")
    public ResTO getDrivers() {
        return driverService.findAllByDeleted(false);
    }


    @PostMapping(value = "/drivers/import")
    public ResTO batchImport(MultipartFile file) {

        if (file.isEmpty()) {
            return RUtil.errorByCode(REnum.FILES_CANNOT_BE_EMPTY.getCode());
        }
        String name = file.getOriginalFilename();
        // 文件格式不正确
        if (name.length() < 6 || !name.substring(name.length() - 5).equals(".xlsx")) {
            return RUtil.errorByCode(REnum.INCORRECT_FILE_FORMAT.getCode());
        }
        return driverService.batchImport(file);
    }
}