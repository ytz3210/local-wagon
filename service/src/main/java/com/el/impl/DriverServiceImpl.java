package com.el.impl;

import com.el.DriverService;
import com.el.common.source.Constant;
import com.el.common.source.REnum;
import com.el.common.source.ResTO;
import com.el.common.to.DriverInfoTO;
import com.el.common.utils.CommonUtils;
import com.el.common.utils.ExcelUtils;
import com.el.common.utils.RUtil;
import com.el.dao.DriverDao;
import com.el.entity.Driver;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 司机相关业务
 * @author: MaoYe
 * @create: 2019/09/11
 */
@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverDao driverDao;

    @Override
    public ResTO addDriver(DriverInfoTO to) {
        Driver driver = new Driver();
        to.setIdentityCard(to.getIdentityCard().toUpperCase());
        if (driverDao.countByIdentityCard(to.getIdentityCard()) > 0) {
            return RUtil.error(REnum.DRIVER_ALREADY_EXISTS);
        }
        BeanUtils.copyProperties(to, driver);
        driver.setStatus("1");
        driverDao.save(driver);
        return RUtil.success();
    }

    @Override
    public ResTO updateDriver(DriverInfoTO to) {
        Driver driver = driverDao.findByIdAndDeleted(to.getId(), false);
        if (driver == null) {
            return RUtil.error(REnum.DRIVER_NOT_EXISTS);
        }
        to.setIdentityCard(to.getIdentityCard().toUpperCase());
        if (!driver.getIdentityCard().equals(to.getIdentityCard()) && driverDao.countByIdentityCard(to.getIdentityCard()) > 0) {
            return RUtil.error(REnum.DRIVER_ALREADY_EXISTS);
        }
        BeanUtils.copyProperties(to, driver);
        return RUtil.success();
    }

    @Override
    public ResTO delDriver(String driverId) {
        if (!driverDao.existsById(driverId)) {
            return RUtil.error(REnum.DRIVER_NOT_EXISTS);
        }

        if (driverDao.deleteDriver(driverId) > 0) {
            return RUtil.success();
        }
        return RUtil.error(REnum.DELETE_FAILED);
    }

    @Override
    public ResTO loadDrivers(String name, String phoneNo, Pageable pageable) {
        return RUtil.successByPage(driverDao.find(name, phoneNo, pageable));
    }

    @Override
    public ResTO findAllByDeleted(Boolean deleted) {
        return RUtil.success(driverDao.findAllByDeleted(deleted));
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
            if (colNum > 7) {
                return RUtil.error(REnum.ERROR_DRIVER_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(0)) || !"司机姓名".equals(String.valueOf(title.getCell(0)))) {
                return RUtil.error(REnum.ERROR_DRIVER_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(1)) || !"微信号码".equals(String.valueOf(title.getCell(1)))) {
                return RUtil.error(REnum.ERROR_DRIVER_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(2)) || !"手机号码".equals(String.valueOf(title.getCell(2)))) {
                return RUtil.error(REnum.ERROR_DRIVER_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(3)) || !"性别".equals(String.valueOf(title.getCell(3)))) {
                return RUtil.error(REnum.ERROR_DRIVER_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(4)) || !"身份证号码".equals(String.valueOf(title.getCell(4)))) {
                return RUtil.error(REnum.ERROR_DRIVER_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(5)) || !"联系地址".equals(String.valueOf(title.getCell(5)))) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }
            if (StringUtils.isEmpty(title.getCell(6)) || !"临时司机".equals(String.valueOf(title.getCell(6)))) {
                return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
            }
        } catch (Exception e) {
            return RUtil.error(REnum.ERROR_WAGON_INFO_TEMPLATE);
        }

        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i < rowNum; i++) {
            row = sheet.getRow(i);
            String name = String.valueOf(row.getCell(0));
            if (StringUtils.isEmpty(name)) {
                continue;
            }
            String weChatNumber = String.valueOf(row.getCell(1));
            if (StringUtils.isEmpty(weChatNumber)) {
                continue;
            }
            String phoneNo = String.valueOf(row.getCell(2));
            if (StringUtils.isEmpty(phoneNo)) {
                continue;
            }
            if (!phoneNo.matches(Constant.PHONE_NO_RULES)) {
                continue;
            }
            String sex = StringUtils.hasText(String.valueOf(row.getCell(3)))
                    && "男".equals(String.valueOf(row.getCell(3))) ? "1" : "2";
            String identityCard = String.valueOf(row.getCell(4));
            if (StringUtils.isEmpty(identityCard)) {
                continue;
            }
            identityCard = identityCard.trim().toUpperCase();
            if (!identityCard.matches(Constant.IDENTITY_CARD_RULES)) {
                continue;
            }
            if (driverDao.countByIdentityCard(identityCard) > 0) {
                continue;
            }
            String address = String.valueOf(row.getCell(5));
            boolean temporary = StringUtils.hasText(String.valueOf(row.getCell(6)))
                    && "是".equals(String.valueOf(row.getCell(6))) ? true : false;
            Driver driver = new Driver();
            driver.setName(name);
            driver.setWeChatNumber(weChatNumber);
            driver.setPhoneNo(phoneNo);
            driver.setSex(sex);
            driver.setIdentityCard(identityCard);
            driver.setDateBirth(CommonUtils.getBirth(identityCard));
            driver.setAddress(address);
            driver.setTemporary(temporary);
            driver.setStatus("1");
            driverDao.save(driver);
        }

        return RUtil.success();
    }

}