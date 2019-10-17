package com.el.controller;

import com.el.WagonTeamService;
import com.el.annotation.SysLog;
import com.el.common.source.ResTO;
import com.el.common.to.WagonTeamTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 车队相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@RestController
@RequestMapping(value = "/api")
public class WagonTeamController extends BaseApiController {

    @Autowired
    WagonTeamService wagonTeamService;

    /**
     * 增加车队
     *
     * @return
     */
    @SysLog("新增车队")
    @PutMapping("/wagon_team")
    public ResTO addMotorcade(@RequestBody WagonTeamTO to) {
        return wagonTeamService.addMotorcade(to);
    }

    /**
     * 修改车队
     *
     * @return
     */
    @SysLog("修改车队")
    @PostMapping("/wagon_team")
    public ResTO editMotorcade(@RequestBody WagonTeamTO to) {
        return wagonTeamService.updateMotorcade(to);
    }

    /**
     * 删除车队
     *
     * @return
     */
    @SysLog("删除车队")
    @DeleteMapping("/wagon_team")
    public ResTO delMotorcade(@RequestParam String id) {
        return wagonTeamService.delMotorcade(id);
    }

    /**
     * 加载所有车队
     *
     * @return
     */
    @SysLog("加载所有车队")
    @GetMapping("/wagon_teams")
    public ResTO loadMotorcades(@RequestParam(required = false) String name,
                                @RequestParam(required = false) String contactPhone,
                                @RequestParam Integer pageNo,
                                @RequestParam Integer pageSize) {
        return wagonTeamService.loadMotorcades(name, contactPhone, PageRequest.of(pageNo - 1, pageSize));
    }

    /**
     * @return java.lang.String
     * @Description 获取所有车队
     **/
    @GetMapping("/all_wagon_teams")
    public ResTO getWagonTeam() {
        return wagonTeamService.findAllByDeleted(false);
    }
}