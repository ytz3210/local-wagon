package com.el.impl;

import com.el.RequestService;
import com.el.SinoiovlApiService;
import com.el.common.source.Constant;
import com.el.common.to.request.*;
import com.el.common.to.response.SinoiovlTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author ZhangJun
 * @Description: 智运开发平台api接口
 * @create 2019-09-19 16:55
 */
@SuppressWarnings("DuplicatedCode")
@Service
public class SinoiovlApiServiceImpl implements SinoiovlApiService {

    @Value("${enternal.url}")
    private String url;

    @Value("${enternal.login}")
    private String login;

    @Value("${wagon_tracker.login_name}")
    private String loginName;

    @Value("${wagon_tracker.password}")
    private String password;

    @Value("${wagon_tracker.client_id}")
    private String clientId;

    @Value("${wagon_tracker.secret}")
    private String secret;

    @Value("${enternal.location.latest_addr}")
    private String lastestAddrUrl;

    @Value("${enternal.location.multi_car_lastest_addr}")
    private String multiCarLastestAddrUrl;

    @Value("${enternal.location.vehicle_trajectory}")
    private String vehicleTrajectoryUrl;

    @Value("${enternal.location.vehicle_check_in}")
    private String vehicleCheckInUrl;

    @Value("${enternal.drive_in.custom_area_regist}")
    private String inCustomAreaRegisttUrl;

    @Value("${enternal.drive_in.polygonal_area_regist}")
    private String inPolygonalAreaRegistUrl;

    @Value("${enternal.drive_in.vehicle_subscription}")
    private String inVehicleSubscriptionUrl;

    @Value("${enternal.drive_in.vehicle_evebt_subscription}")
    private String inVehicleEvebtSubscriptionUrl;

    @Value("${enternal.drive_in.delete_vehicle_subscription}")
    private String inDelVehicleSubscriptionUrl;

    @Value("${enternal.drive_in.delete_custom_area}")
    private String inDelCustomAreaUrl;

    @Value("${enternal.drive_out.custom_area_regist}")
    private String outCustomAreaRegisttUrl;

    @Value("${enternal.drive_out.polygonal_area_regist}")
    private String outPolygonalAreaRegistUrl;

    @Value("${enternal.drive_out.vehicle_subscription}")
    private String outVehicleSubscriptionUrl;

    @Value("${enternal.drive_out.vehicle_evebt_subscription}")
    private String outVehicleEvebtSubscriptionUrl;

    @Value("${enternal.drive_out.delete_vehicle_subscription}")
    private String outDelVehicleSubscriptionUrl;

    @Value("${enternal.drive_out.delete_custom_area}")
    private String outDelCustomAreaUrl;

    @Value("${enternal.drive_out.vehicle_in_prejudge}")
    private String vehicleInPrejudgeUrl;

    @Autowired
    private RequestService requestService;


    @Override
    public SinoiovlTO getToken() {

        LoginRequestTO loginRequestTO = new LoginRequestTO();
        loginRequestTO.setUser(loginName);
        loginRequestTO.setPwd(password);
        loginRequestTO.setSrt(secret);
        loginRequestTO.setCid(clientId);
        return requestService.doPostHttps(loginRequestTO, url + login);
    }

    @Override
    public SinoiovlTO latestPos(String token, String plate, String timeNeryBay) {

        LastLocationRequestTO lastLocation = new LastLocationRequestTO();
        lastLocation.setToken(token);
        lastLocation.setCid(clientId);
        lastLocation.setSrt(secret);
        lastLocation.setTimeNearby(timeNeryBay);
        lastLocation.setVclN(plate);
        return requestService.doPostHttps(lastLocation, url + lastestAddrUrl);
    }

    @Override
    public SinoiovlTO multiCarLastestPos(String token, String plates, String timeNeryBay) {

        LastLocationMultiRequestTO lastLocationMulti = new LastLocationMultiRequestTO();
        lastLocationMulti.setToken(token);
        lastLocationMulti.setCid(clientId);
        lastLocationMulti.setSrt(secret);
        lastLocationMulti.setTimeNearby(timeNeryBay);
        lastLocationMulti.setVclNs(plates);
        return requestService.doPostHttps(lastLocationMulti, url + multiCarLastestAddrUrl);
    }

    @Override
    public SinoiovlTO vehicleTrajectory(String token, String plate, String startTime, String endTime) {

        HisTrackRequestTO hisTrackRequestTO = new HisTrackRequestTO();
        hisTrackRequestTO.setToken(token);
        hisTrackRequestTO.setCid(clientId);
        hisTrackRequestTO.setSrt(secret);
        hisTrackRequestTO.setVclN(plate);
        hisTrackRequestTO.setQryBtm(startTime);
        hisTrackRequestTO.setQryEtm(endTime);
        return requestService.doPostHttps(hisTrackRequestTO, url + vehicleTrajectoryUrl);
    }

    @Override
    public SinoiovlTO vehicleCheckIn(String token, String plates) {

        VehicleCheckInRequestTo vehicleCheckInRequestTo = new VehicleCheckInRequestTo();
        vehicleCheckInRequestTo.setToken(token);
        vehicleCheckInRequestTo.setCid(clientId);
        vehicleCheckInRequestTo.setSrt(secret);
        vehicleCheckInRequestTo.setVclNs(plates);
        return requestService.doPostHttps(vehicleCheckInRequestTo, url + vehicleCheckInUrl);
    }

    @Override
    public SinoiovlTO inCustomAreaReg(String token, String areaName, String lonLat, String radius) {

        AreaRegRequestTO areaRegRequestTO = new AreaRegRequestTO();
        areaRegRequestTO.setToken(token);
        areaRegRequestTO.setCid(clientId);
        areaRegRequestTO.setSrt(secret);
        areaRegRequestTO.setUserflag(loginName);
        areaRegRequestTO.setAreaname(areaName);
        areaRegRequestTO.setLonlat(lonLat);
        areaRegRequestTO.setRadius(radius);
        areaRegRequestTO.setType(Constant.VEHICLE_TYPE);
        areaRegRequestTO.setActiontype(Constant.ACTION_TYPE_IN);
        return requestService.doPostHttps(areaRegRequestTO, url + inCustomAreaRegisttUrl);
    }

    @Override
    public SinoiovlTO inPolygonalAreaRegist(String token, String areaName, String lonLats) {

        AreaPolygonRegRequestTO areaPolygonRegRequestTO = new AreaPolygonRegRequestTO();
        areaPolygonRegRequestTO.setToken(token);
        areaPolygonRegRequestTO.setCid(clientId);
        areaPolygonRegRequestTO.setSrt(secret);
        areaPolygonRegRequestTO.setAreaname(areaName + Constant.SAIL_IN);
        areaPolygonRegRequestTO.setLonlats(lonLats);
        areaPolygonRegRequestTO.setType(Constant.VEHICLE_TYPE);
        areaPolygonRegRequestTO.setActiontype(Constant.ACTION_TYPE_IN);
        areaPolygonRegRequestTO.setCoorType(Constant.COOR_TYPE_BD);
        return requestService.doPostHttps(areaPolygonRegRequestTO, url + inPolygonalAreaRegistUrl);
    }

    @Override
    public SinoiovlTO inVehicleSubscription(String token, String paltes, String areaId) {

        VnoRegRequestTO vnoRegRequestTO = new VnoRegRequestTO();
        vnoRegRequestTO.setToken(token);
        vnoRegRequestTO.setCid(clientId);
        vnoRegRequestTO.setSrt(secret);
        vnoRegRequestTO.setUserflag(loginName);
        vnoRegRequestTO.setVnos(paltes);
        vnoRegRequestTO.setAreaid(areaId);
        return requestService.doPostHttps(vnoRegRequestTO, url + inVehicleSubscriptionUrl);
    }

    @Override
    public SinoiovlTO inDelVehicleSubscription(String token, String paltes, String areaId) {

        VnoDelRequestTO vnoDelRequestTO = new VnoDelRequestTO();
        vnoDelRequestTO.setToken(token);
        vnoDelRequestTO.setCid(clientId);
        vnoDelRequestTO.setSrt(secret);
        vnoDelRequestTO.setUserflag(loginName);
        vnoDelRequestTO.setVnos(paltes);
        vnoDelRequestTO.setAreaid(areaId);
        return requestService.doPostHttps(vnoDelRequestTO, url + inDelVehicleSubscriptionUrl);
    }

    @Override
    public SinoiovlTO inCDelCustomArea(String token, String areaId) {

        AreaDelRequestTO areaDelRequestTO = new AreaDelRequestTO();
        areaDelRequestTO.setToken(token);
        areaDelRequestTO.setCid(clientId);
        areaDelRequestTO.setSrt(secret);
        areaDelRequestTO.setUserflag(loginName);
        areaDelRequestTO.setAreaid(areaId);
        return requestService.doPostHttps(areaDelRequestTO, url + inDelCustomAreaUrl);
    }

    @Override
    public SinoiovlTO outCustomAreaReg(String token, String areaName, String lonLat, String radius) {

        AreaRegRequestTO areaRegRequestTO = new AreaRegRequestTO();
        areaRegRequestTO.setToken(token);
        areaRegRequestTO.setCid(clientId);
        areaRegRequestTO.setSrt(secret);
        areaRegRequestTO.setUserflag(loginName);
        areaRegRequestTO.setAreaname(areaName);
        areaRegRequestTO.setLonlat(lonLat);
        areaRegRequestTO.setRadius(radius);
        areaRegRequestTO.setType(Constant.VEHICLE_TYPE);
        areaRegRequestTO.setActiontype(Constant.ACTION_TYPE_OUT);
        return requestService.doPostHttps(areaRegRequestTO, url + outCustomAreaRegisttUrl);
    }

    @Override
    public SinoiovlTO outPolygonalAreaRegist(String token, String areaName, String lonLats) {
        AreaPolygonRegRequestTO areaPolygonRegRequestTO = new AreaPolygonRegRequestTO();
        areaPolygonRegRequestTO.setToken(token);
        areaPolygonRegRequestTO.setCid(clientId);
        areaPolygonRegRequestTO.setSrt(secret);
        areaPolygonRegRequestTO.setAreaname(areaName + Constant.SAIL_OUT);
        areaPolygonRegRequestTO.setLonlats(lonLats);
        areaPolygonRegRequestTO.setType(Constant.VEHICLE_TYPE);
        areaPolygonRegRequestTO.setActiontype(Constant.ACTION_TYPE_OUT);
        areaPolygonRegRequestTO.setCoorType(Constant.COOR_TYPE_BD);
        return requestService.doPostHttps(areaPolygonRegRequestTO, url + outPolygonalAreaRegistUrl);
    }

    @Override
    public SinoiovlTO outVehicleSubscription(String token, String paltes, String areaId) {

        VnoRegRequestTO vnoRegRequestTO = new VnoRegRequestTO();
        vnoRegRequestTO.setToken(token);
        vnoRegRequestTO.setCid(clientId);
        vnoRegRequestTO.setSrt(secret);
        vnoRegRequestTO.setUserflag(loginName);
        vnoRegRequestTO.setVnos(paltes);
        vnoRegRequestTO.setAreaid(areaId);
        return requestService.doPostHttps(vnoRegRequestTO, url + outVehicleSubscriptionUrl);
    }

    @Override
    public SinoiovlTO outDelVehicleSubscription(String token, String paltes, String areaId) {
        VnoDelRequestTO vnoDelRequestTO = new VnoDelRequestTO();
        vnoDelRequestTO.setToken(token);
        vnoDelRequestTO.setCid(clientId);
        vnoDelRequestTO.setSrt(secret);
        vnoDelRequestTO.setUserflag(loginName);
        vnoDelRequestTO.setVnos(paltes);
        vnoDelRequestTO.setAreaid(areaId);
        return requestService.doPostHttps(vnoDelRequestTO, url + outDelVehicleSubscriptionUrl);
    }

    @Override
    public SinoiovlTO outCDelCustomArea(String token, String areaId) {
        AreaDelRequestTO areaDelRequestTO = new AreaDelRequestTO();
        areaDelRequestTO.setToken(token);
        areaDelRequestTO.setCid(clientId);
        areaDelRequestTO.setSrt(secret);
        areaDelRequestTO.setUserflag(loginName);
        areaDelRequestTO.setAreaid(areaId);
        return requestService.doPostHttps(areaDelRequestTO, url + outDelCustomAreaUrl);
    }

    @Override
    public SinoiovlTO vehicleInPrejudge(String token, String palteNo, String endLon, String endLat) {

        VehicleInPrejudgeRequestTo vehicleInPrejudgeRequestTo = new VehicleInPrejudgeRequestTo();
        vehicleInPrejudgeRequestTo.setToken(token);
        vehicleInPrejudgeRequestTo.setCid(clientId);
        vehicleInPrejudgeRequestTo.setSrt(secret);
        vehicleInPrejudgeRequestTo.setVclN(palteNo);
        vehicleInPrejudgeRequestTo.setVco(Constant.PLATE_COLOR_YELLOW);
        vehicleInPrejudgeRequestTo.setEndLon(endLon);
        vehicleInPrejudgeRequestTo.setEndLat(endLat);
        return requestService.doPostHttps(vehicleInPrejudgeRequestTo, url + vehicleInPrejudgeUrl);
    }
}
