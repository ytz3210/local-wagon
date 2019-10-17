package com.el.impl;

import com.el.WagonService;
import com.el.common.source.Constant;
import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.to.WagonInfoTO;
import com.el.common.to.request.WagonRequestTO;
import com.el.common.utils.CommonUtils;
import com.el.common.utils.ExcelUtils;
import com.el.common.utils.RUtil;
import com.el.dao.DriverDao;
import com.el.dao.WagonDao;
import com.el.dao.WagonTeamDao;
import com.el.entity.Driver;
import com.el.entity.Wagon;
import com.el.entity.WagonTeam;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @description: 货车相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@Service
public class WagonServiceImpl implements WagonService {

    @Autowired
    private WagonDao wagonDao;

    @Autowired
    private WagonTeamDao wagonTeamDao;

    @Autowired
    private DriverDao driverDao;

    @Override
    public ResTO addWagon(WagonInfoTO to) {
        Wagon wagon = new Wagon();
        BeanUtils.copyProperties(to, wagon);
        if (StringUtils.isEmpty(wagon.getPlateNo())) {
            return RUtil.errorByCode(REnum.PLATE_NO_EMPTY.getCode());
        }
        wagon.setPlateNo(wagon.getPlateNo().toUpperCase());
        if (wagonDao.countByPlateNo(wagon.getPlateNo()) > 0) {
            return RUtil.errorByCode(REnum.PLATE_NO_EXISTS.getCode());
        }
        wagonDao.save(wagon);
        return RUtil.success();
    }

    @Override
    public ResTO updateWagon(WagonInfoTO to) {
        Wagon wagon = wagonDao.findByIdAndDeleted(to.getId(), false);
        if (wagon == null) {
            return RUtil.error(REnum.WAGON_NOT_EXISTS);
        }
        if (StringUtils.isEmpty(to.getPlateNo())) {
            return RUtil.errorByCode(REnum.PLATE_NO_EMPTY.getCode());
        }
        to.setPlateNo(to.getPlateNo().toUpperCase());
        if (!wagon.getPlateNo().equals(to.getPlateNo()) && wagonDao.countByPlateNo(to.getPlateNo()) > 0) {
            return RUtil.errorByCode(REnum.PLATE_NO_EXISTS.getCode());
        }
        BeanUtils.copyProperties(to, wagon);
        if (to.getTemporary()) {
            wagon.setWagonTeamId(null);
        }
        return RUtil.success();
    }

    @Override
    public ResTO delWagon(String wagonId) {
        if (!wagonDao.existsById(wagonId)) {
            return RUtil.error(REnum.WAGON_NOT_EXISTS);
        }

        if (wagonDao.deleteWagon(wagonId) > 0) {
            return RUtil.success();
        }
        return RUtil.error(REnum.DELETE_FAILED);
    }

    @Override
    public ResTO loadWagons(WagonRequestTO wagonInfoTO) {
        Page<Map> wagonMap = wagonDao.find(wagonInfoTO, PageRequest.of(wagonInfoTO.getPageNo() - 1, wagonInfoTO.getPageSize()));
        return RUtil.successByPage(wagonMap);
    }

    @Override
    public ResTO getAllWagons() {
        return RUtil.success(wagonDao.findAllByDeleted(false));
    }

    @Override
    public ResTO batchImport(MultipartFile file) {

        // 上传文件名
        Workbook wb = ExcelUtils.getWb(file);
        if (wb == null) {
            return RUtil.error(REnum.CONTENT_CANNOT_BE_EMPTY);
        }
        Sheet sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum() + 1;
        Row row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 判断标题是否与模板一致
        Row title = sheet.getRow(0);
        try {
            if (colNum > 10) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(0)) || !"车牌号码".equals(String.valueOf(title.getCell(0)))) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(1)) || !"临时车辆".equals(String.valueOf(title.getCell(1)))) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(2)) || !"司机姓名".equals(String.valueOf(title.getCell(2)))) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(3)) || !"微信号码".equals(String.valueOf(title.getCell(3)))) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(4)) || !"手机号码".equals(String.valueOf(title.getCell(4)))) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(5)) || !"性别".equals(String.valueOf(title.getCell(5)))) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(6)) || !"身份证号码".equals(String.valueOf(title.getCell(6)))) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(7)) || !"联系地址".equals(String.valueOf(title.getCell(7)))) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(8)) || !"临时司机".equals(String.valueOf(title.getCell(8)))) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(9)) || !"所属车队".equals(String.valueOf(title.getCell(9)))) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }

        } catch (Exception e) {
            return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
        }

        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i < rowNum; i++) {
            row = sheet.getRow(i);

            String plateNo = String.valueOf(row.getCell(0));
            if (StringUtils.isEmpty(plateNo)) {
                continue;
            }
            plateNo = plateNo.trim().toUpperCase();
            if(!plateNo.matches(Constant.PLATE_NO_RULES)){
                continue;
            }
            if (wagonDao.countByPlateNo(plateNo) > 0) {
                continue;
            }
            boolean temporaryWagon = StringUtils.hasText(String.valueOf(row.getCell(1)))
                    && "是".equals(String.valueOf(row.getCell(1))) ? true : false;
            String driverName = String.valueOf(row.getCell(2));
            if (StringUtils.isEmpty(driverName)) {
                continue;
            }
            String weChatNumber = String.valueOf(row.getCell(3));
            if (StringUtils.isEmpty(weChatNumber)) {
                continue;
            }
            String phoneNo = String.valueOf(row.getCell(4));
            if (StringUtils.isEmpty(phoneNo)) {
                continue;
            }
            if(!phoneNo.matches(Constant.PHONE_NO_RULES)){
                continue;
            }
            String sex = StringUtils.hasText(String.valueOf(row.getCell(5)))
                    && "男".equals(String.valueOf(row.getCell(5))) ? "1" : "2";

            String identityCard = String.valueOf(row.getCell(6));
            if (StringUtils.isEmpty(identityCard)) {
                continue;
            }
            identityCard = identityCard.trim().toUpperCase();
            if (!identityCard.matches(Constant.IDENTITY_CARD_RULES)) {
                continue;
            }
            String address = String.valueOf(row.getCell(7));
            boolean temporaryDriver = StringUtils.hasText(String.valueOf(row.getCell(8)))
                    && "是".equals(String.valueOf(row.getCell(8))) ? true : false;
            String wagonTeamName = String.valueOf(row.getCell(9));
            if (StringUtils.isEmpty(wagonTeamName) || wagonTeamDao.countByName(wagonTeamName) <= 0) {
                continue;
            }

            Driver driver = driverDao.findByIdeAndIdentityCard(identityCard);
            if (driver == null) {
                driver = new Driver();
                driver.setName(driverName);
                driver.setWeChatNumber(weChatNumber);
                driver.setPhoneNo(phoneNo);
                driver.setSex(sex);
                driver.setIdentityCard(identityCard);
                driver.setDateBirth(CommonUtils.getBirth(identityCard));
                driver.setAddress(address);
                driver.setTemporary(temporaryDriver);
                driver.setStatus("1");
                driverDao.save(driver);
            }
            WagonTeam wagonTeam = wagonTeamDao.findByName(wagonTeamName);
            if (wagonTeam == null) {
                continue;
            }
            Wagon wagon = new Wagon();
            wagon.setPlateNo(plateNo);
            wagon.setTemporary(temporaryWagon);
            if (!temporaryWagon) {
                wagon.setWagonTeamId(wagonTeam.getId());
            }
            wagon.setDriverId(driver.getId());
            wagonDao.save(wagon);
        }
        return RUtil.success();
    }

}