package com.el.impl;

import com.el.WagonTeamService;
import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.to.WagonTeamTO;
import com.el.common.utils.RUtil;
import com.el.dao.WagonTeamDao;
import com.el.entity.WagonTeam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @description: 车队相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@Service
public class WagonTeamServiceImpl implements WagonTeamService {

    @Autowired
    WagonTeamDao wagonTeamDao;

    @Override
    public ResTO addMotorcade(WagonTeamTO to) {

        if(StringUtils.isEmpty(to.getName())){
            return RUtil.error(REnum.N0_WAGON_TEAM_NAME);
        }
        if(wagonTeamDao.countByName(to.getName()) > 0){
            return RUtil.error(REnum.WAGON_TEAM_ALREADY_EXISTS);
        }
        WagonTeam wagonTeam = new WagonTeam();
        BeanUtils.copyProperties(to, wagonTeam);
        wagonTeamDao.save(wagonTeam);
        return RUtil.success();
    }

    @Override
    public ResTO updateMotorcade(WagonTeamTO to) {
        WagonTeam wagonTeam = wagonTeamDao.findByIdAndDeleted(to.getId(), false);
        if (wagonTeam == null) {
            return RUtil.error(REnum.WAGON_TEAM_NOT_EXISTS);
        }
        if(!wagonTeam.getName().equals(to.getName()) && wagonTeamDao.countByName(to.getName()) > 0){
            return RUtil.error(REnum.WAGON_TEAM_ALREADY_EXISTS);
        }
        BeanUtils.copyProperties(to, wagonTeam);
        return RUtil.success();
    }

    @Override
    public ResTO delMotorcade(String MotorcadeId) {
        if (!wagonTeamDao.existsById(MotorcadeId)) {
            return RUtil.error(REnum.WAGON_TEAM_NOT_EXISTS);
        }

        if (wagonTeamDao.deleteMotorcade(MotorcadeId) > 0) {
            return RUtil.success();
        }
        return RUtil.error(REnum.DELETE_FAILED);
    }

    @Override
    public ResTO loadMotorcades(String name, String contactPhone, Pageable pageable) {
        return RUtil.successByPage(wagonTeamDao.find(name, contactPhone, pageable));
    }

    @Override
    public ResTO findAllByDeleted(Boolean deleted) {
        return RUtil.success(wagonTeamDao.findAllByDeleted(deleted));
    }
}