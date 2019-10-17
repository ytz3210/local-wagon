package com.el.controller;

import com.el.SiteService;
import com.el.annotation.SysLog;
import com.el.common.source.ResTO;
import com.el.common.to.SiteInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 围栏相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@RestController
@RequestMapping(value = "/api")
public class SiteController extends BaseApiController {

    @Autowired
    SiteService siteService;

    /**
     * 增加围栏
     * @return
     */
    @SysLog("新增围栏")
    @PutMapping("/site")
    public ResTO addSite(@RequestBody SiteInfoTO to) throws Exception {
        return siteService.addSite(to);
    }

    /**
     * 修改围栏
     * @return
     */
    @SysLog("修改围栏")
    @PostMapping("/site")
    public ResTO editSite(@RequestBody SiteInfoTO to) throws Exception {
        return siteService.updateSite(to);
    }

    /**
     * 删除围栏
     * @return
     */
    @SysLog("删除围栏")
    @DeleteMapping("/site")
    public ResTO delSite(@RequestParam String id) {
        return siteService.delSite(id);
    }

    /**
     * 加载所有围栏
     * @return
     */
    @SysLog("加载所有围栏")
    @GetMapping("/sites")
    public ResTO loadSites(@RequestParam(required = false) String name,
                           @RequestParam Integer pageNo,
                           @RequestParam Integer pageSize) {
        return siteService.loadSites(name, PageRequest.of(pageNo - 1, pageSize));
    }

    /**
     * 加载所有有效围栏
     * @return
     **/
    @GetMapping("/all_sites")
    public ResTO getAllSites() {
        return siteService.getAllSites(false, true);
    }
}