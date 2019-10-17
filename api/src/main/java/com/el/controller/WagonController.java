package com.el.controller;

import com.el.WagonService;
import com.el.annotation.SysLog;
import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.to.WagonInfoTO;
import com.el.common.to.request.WagonRequestTO;
import com.el.common.utils.RUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 货车相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@RestController
@RequestMapping(value = "/api")
public class WagonController extends BaseApiController {

    @Autowired
    private WagonService wagonService;

    @Autowired
    private Environment env;

    /**
     * 增加货车
     *
     * @return
     */
    @SysLog("新增货车")
    @PutMapping("/wagon")
    public ResTO addWagon(@RequestBody WagonInfoTO to) {
        return wagonService.addWagon(to);
    }

    /**
     * 修改货车
     *
     * @return
     */
    @SysLog("修改货车")
    @PostMapping("/wagon")
    public ResTO editWagon(@RequestBody WagonInfoTO to) {
        return wagonService.updateWagon(to);
    }

    /**
     * 删除货车
     *
     * @return
     */
    @SysLog("删除货车")
    @DeleteMapping("/wagon")
    public ResTO delWagon(@RequestParam String id) {
        return wagonService.delWagon(id);
    }

    /**
     * 加载所有货车
     *
     * @return
     */
    @GetMapping("/wagons")
    public ResTO loadWagons(WagonRequestTO wagonRequestTO) throws Exception {
        return wagonService.loadWagons(wagonRequestTO);
    }

    /**
     * 不分页加载所有货车
     *
     * @return
     */
    @GetMapping("/all_wagons")
    public ResTO getAllWagons() {
        return wagonService.getAllWagons();
    }

    /**
     * @param file 批量导入文件
     * @return com.el.common.source.ResTO
     * @Description 批量导入车辆和司机
     **/
    @PostMapping(value = "/wagons/import")
    public ResTO batchImport(MultipartFile file) {

        if (file.isEmpty()) {
            return RUtil.errorByCode(REnum.FILES_CANNOT_BE_EMPTY.getCode());
        }
        String name = file.getOriginalFilename();
        // 文件格式不正确
        if (name.length() < 6 || !name.substring(name.length() - 5).equals(".xlsx")) {
            return RUtil.errorByCode(REnum.INCORRECT_FILE_FORMAT.getCode());
        }
        return wagonService.batchImport(file);
    }

}