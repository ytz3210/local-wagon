package com.el;

import com.alibaba.druid.util.StringUtils;
import com.el.common.to.response.SinoiovlTO;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@ComponentScan("com.el")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApiApplicationTests {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SinoiovlApiService sinoiovlApiService;

    private static String token = "a5693144-fba2-4b44-8609-d0b62af6302f";

    @Before
    public void init() {
        if (StringUtils.isEmpty(token)) {
            SinoiovlTO sinoiovlTo = sinoiovlApiService.getToken();
            if (sinoiovlTo != null && "1001".equals(sinoiovlTo.getStatus())) {
                token = sinoiovlTo.getResult().toString();
            }
            logger.info(JSONObject.fromObject(sinoiovlTo).toString());
        }
    }

    /**
     * @return void
     * @Description 测试单个车辆最新的地位信息
     **/
    @Test
    public void testLatestAddr() {

        SinoiovlTO sinoiovlTo = sinoiovlApiService.latestPos(token, "陕YH0009", "100");
        logger.info(JSONObject.fromObject(sinoiovlTo).toString());
    }


    /**
     * @return void
     * @Description 测试多个车辆的最新定位信息
     **/
    @Test
    public void testMultiCarLastestAddr() {

        SinoiovlTO sinoiovlTo = sinoiovlApiService.multiCarLastestPos(
                token,
                "陕YH0009_1,陕XB0058_2",
                "1000"
        );
        logger.info(JSONObject.fromObject(sinoiovlTo).toString());
    }

    /**
     * @return void
     * @Description 车辆轨迹查询(车牌号)接口
     **/
    @Test
    public void testVehicleTrajectory() {
        SinoiovlTO sinoiovlTo = sinoiovlApiService.vehicleTrajectory(
                token,
                "陕YH0009",
                "2019-09-17 09:00:00",
                "2019-09-18 09:00:00"
        );
        logger.info(JSONObject.fromObject(sinoiovlTo).toString());
    }


    /**
     * @return void
     * @Description 车辆签到接口
     **/
    @Test
    public void testVehicleCheckIn() {
        SinoiovlTO sinoiovlTo = sinoiovlApiService.vehicleCheckIn(token, "陕YH0009_1,陕XB0058_2");
        logger.info(JSONObject.fromObject(sinoiovlTo).toString());
    }


    /**
     * @return void
     * @Description 驶入自定义区域
     **/
    @Test
    public void testCustomAreaReg() {
        SinoiovlTO sinoiovlTo = sinoiovlApiService.inCustomAreaReg(
                token,
                "测试区域",
                "120.84431,30.89889",
                "3000"
        );
        logger.info(JSONObject.fromObject(sinoiovlTo).toString());
    }

    /**
     * @return void
     * @Description 驶入-多边形自定义区域
     **/
    @Test
    public void testPolygonalAreaRegist() {
        SinoiovlTO sinoiovlTo = sinoiovlApiService.inPolygonalAreaRegist(
                token,
                "多边形区域测试",
                "113.796387,41.19519" +
                        "|118.410645,41.228249" +
                        "|117.773438,40.245992" +
                        "|116.015625,40.212441" +
                        "|114.938965,39.520992" +
                        "|113.796387,41.19519"
        );
        logger.info(JSONObject.fromObject(sinoiovlTo).toString());
    }

    /**
     * @return void
     * @Description 驶入-车辆订阅接口
     **/
    @Test
    public void testVehicleSubscription() {
        SinoiovlTO sinoiovlTo = sinoiovlApiService.inVehicleSubscription(
                token,
                "陕YH0009_1,陕XB0058_2",
                "1796318872715298390"
        );
        logger.info(JSONObject.fromObject(sinoiovlTo).toString());
    }

    /**
     * @return void
     * @Description 驶入-删除车辆订阅通知接口
     **/
    @Test
    public void testDelVehicleSubscription() {
        SinoiovlTO sinoiovlTo = sinoiovlApiService.inDelVehicleSubscription(
                token,
                "陕YH0009_1,陕XB0058_2",
                "1796318872715298390"
        );
        logger.info(JSONObject.fromObject(sinoiovlTo).toString());
    }


    /**
     * @return void
     * @Description 驶入-删除自定义区域
     **/
    @Test
    public void testDelCustomArea() {
        SinoiovlTO sinoiovlTo = sinoiovlApiService.inCDelCustomArea(token, "1796318872715298390");
        logger.info(JSONObject.fromObject(sinoiovlTo).toString());
    }


    /**
     * @return void
     * @Description 驶出-自定义区域
     **/
    @Test
    public void testOutCustomAreaReg() {
        SinoiovlTO sinoiovlTo = sinoiovlApiService.outCustomAreaReg(
                token,
                "测试驶出区域",
                "120.84431,30.89889",
                "3000"
        );
        logger.info(JSONObject.fromObject(sinoiovlTo).toString());
    }

    /**
     * @return void
     * @Description 驶出-多边形自定义区域
     **/
    @Test
    public void testOutPolygonalAreaRegist() {
        SinoiovlTO sinoiovlTo = sinoiovlApiService.outPolygonalAreaRegist(
                token,
                "多边形区域驶出测试",
                "113.796387,41.19519" +
                        "|118.410645,41.228249" +
                        "|117.773438,40.245992" +
                        "|116.015625,40.212441" +
                        "|114.938965,39.520992" +
                        "|113.796387,41.19519"
        );
        logger.info(JSONObject.fromObject(sinoiovlTo).toString());
    }

    /**
     * @return void
     * @Description 驶出-车辆订阅接口
     **/
    @Test
    public void testOutVehicleSubscription() {
        SinoiovlTO sinoiovlTo = sinoiovlApiService.outVehicleSubscription(
                token,
                "陕YH0009_1,陕XB0058_2",
                "7680758876088030974"
        );
        logger.info(JSONObject.fromObject(sinoiovlTo).toString());
    }


    /**
     * @return void
     * @Description 车辆在途运抵预判
     **/
    @Test
    public void testVehicleInPrejudgeUrl() {
        SinoiovlTO sinoiovlTo = sinoiovlApiService.vehicleInPrejudge(
                token,
                "陕YH0009",
                "118.346985",
                "38.856548"
        );
        JSONObject jsonObject = JSONObject.fromObject(JSONObject.fromObject(sinoiovlTo.getResult()).toString());


        logger.info((String) jsonObject.get("residueDis"));
    }


}