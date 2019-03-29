package com.demo.mavenTutorial.service.serviceImpl;
/**
 * @author shaoy
 * @date 2019/3/28 14:50
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidubce.services.iotdm.IotDmV3Client;
import com.baidubce.services.iotdm.model.v3.device.*;
import com.baidubce.services.iotdm.model.v3.schema.Schema;
import com.baidubce.services.iotdm.model.v3.schema.SchemaListResponse;
import com.baidubce.services.iothub.IotHubClient;
import com.baidubce.services.iothub.model.ListResponse;
import com.baidubce.services.iothub.model.QueryEndpointResponse;
import com.demo.mavenTutorial.common.InitIotClient;
import com.demo.mavenTutorial.common.InitIotDmClient;
import com.demo.mavenTutorial.domain.DeviceView;
import com.demo.mavenTutorial.service.BCSIotService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BCSIotServiceImpl
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class BCSIotServiceImpl implements BCSIotService {

    private static final Logger logger = LoggerFactory.getLogger(BCSBucketServiceImpl.class);

    @Override
    /**
     * @methodName getEndpoints
     * @Description 获取IOT HUB项目列表
     * @Date 14:02 2019/3/29
     * @Param [endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY]
     * @return com.baidubce.services.iothub.model.ListResponse
     **/
    public ListResponse getEndpoints(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY) {
        InitIotClient initIotClient = new InitIotClient();
        IotHubClient iotHubClient = initIotClient.initIotHubClient(endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY);

        ListResponse responseList = iotHubClient.listEndpoints();
        return responseList;
    }

    @Override
    /**
     * @methodName createEndpoint
     * @Description 创建项目
     * @Date 10:34 2019/3/29
     * @Param [endPointName, endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY]
     * @return com.baidubce.services.iothub.model.QueryEndpointResponse
     **/
    public QueryEndpointResponse createEndpoint(String endPointName, String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY) {
        InitIotClient initIotClient = new InitIotClient();
        IotHubClient iotHubClient = initIotClient.initIotHubClient(endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY);

        QueryEndpointResponse responseQuery = iotHubClient.createEndpoint(endPointName);

        return responseQuery;
    }

    @Override
    /**
     * @methodName getDeviceList
     * @Description 获取设备列表
     * @Date 11:08 2019/3/29
     * @Param [endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY, searchName, searchValue]
     * @return com.baidubce.services.iotdm.model.v3.device.DeviceProfileListResponse
     **/
    public DeviceProfileListResponse getDeviceList(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY, String searchName, String searchValue) {
        InitIotDmClient initIotDmClient = new InitIotDmClient();
        IotDmV3Client iotDmV3Client = initIotDmClient.initDmClient(endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY);

        int pageNo = 1;
        int pageSize = 10;
        String orderBy = "name";
        String order = "asc";
        String name = searchName;
        String value = searchValue;
        String favourite = "all";

        DeviceProfileListResponse response = iotDmV3Client.getDeviceProfiles(pageNo, pageSize, orderBy, order, name, value, favourite);

        List<DeviceProfile> devices = response.getDevices();

        logger.info(devices.toString());

        return response;
    }

    @Override
    /**
     * @methodName getDeviceProfile
     * @Description 获取设备profile
     * @Date 10:50 2019/3/29
     * @Param [endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY, deviceName]
     * @return com.baidubce.services.iotdm.model.v3.device.DeviceProfileResponse
     **/
    public DeviceProfileResponse getDeviceProfile(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY, String deviceName) {
        InitIotDmClient initIotDmClient = new InitIotDmClient();
        IotDmV3Client iotDmV3Client = initIotDmClient.initDmClient(endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY);

        DeviceProfileResponse deviceProfile = iotDmV3Client.getDeviceProfile(deviceName);

        logger.info(deviceProfile.getState());
        logger.info(deviceProfile.getName());
        logger.info(deviceProfile.getCreateTime().toString());

        return deviceProfile;
    }

    @Override
    /**
     * @methodName getDeviceView
     * @Description 获取设备的view
     * @Date 10:53 2019/3/29
     * @Param [endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY, deviceName]
     * @return com.baidubce.services.iotdm.model.v3.device.DeviceViewResponse
     **/
    public DeviceViewResponse getDeviceView(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY, String deviceName) {
        InitIotDmClient initIotDmClient = new InitIotDmClient();
        IotDmV3Client iotDmV3Client = initIotDmClient.initDmClient(endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY);

        DeviceViewResponse deviceViewResponse = iotDmV3Client.getDeviceView(deviceName);

        return deviceViewResponse;
    }

    @Override
    /**
     * @methodName getDeviceAccess
     * @Description 获取设备接入详情
     * @Date 10:57 2019/3/29
     * @Param [endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY, deviceName]
     * @return com.baidubce.services.iotdm.model.v3.device.DeviceAccessDetailResponse
     **/
    public DeviceAccessDetailResponse getDeviceAccess(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY, String deviceName) {
        InitIotDmClient initIotDmClient = new InitIotDmClient();
        IotDmV3Client iotDmV3Client = initIotDmClient.initDmClient(endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY);

        DeviceAccessDetailResponse deviceAccessDetailResponse = iotDmV3Client.getDeviceAccessDetail(deviceName);

        return deviceAccessDetailResponse;
    }

    @Override
    /**
     * @methodName updateDevice
     * @Description 更新设备属性
     * @Date 14:16 2019/3/29
     * @Param [endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY, deviceName]
     * @return com.baidubce.services.iotdm.model.v3.device.DeviceViewResponse
     **/
    public DeviceViewResponse updateDevice(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY, ArrayList<DeviceView> paramList) {
        InitIotDmClient initIotDmClient = new InitIotDmClient();
        IotDmV3Client iotDmV3Client = initIotDmClient.initDmClient(endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        String deviceName = paramList.get(0).getDeviceName();
        UpdateDeviceViewRequest updateDeviceViewRequest = new UpdateDeviceViewRequest();

        for (DeviceView list : paramList) {
            ObjectNode reported = new ObjectMapper().createObjectNode();
            reported.put(list.getReportedName(), list.getReportedValue());

            ObjectNode desired = new ObjectMapper().createObjectNode();
            desired.put(list.getDesiredName(), list.getDesiredValue());

            updateDeviceViewRequest
                    .withReported(reported)
                    .withDesired(desired);
        }

        DeviceViewResponse deviceViewResponse = iotDmV3Client.updateDeviceView(deviceName, updateDeviceViewRequest);

        return deviceViewResponse;
    }

    @Override
    public SchemaListResponse getDeviceModel(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY, Integer index, Integer size, String orderBy, String order, String key) {
        InitIotDmClient initIotDmClient = new InitIotDmClient();
        IotDmV3Client iotDmV3Client = initIotDmClient.initDmClient(endpoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY);

        SchemaListResponse schemaListResponse = iotDmV3Client.getSchemas(index, size, orderBy, order, key);

        return schemaListResponse;
    }
}
