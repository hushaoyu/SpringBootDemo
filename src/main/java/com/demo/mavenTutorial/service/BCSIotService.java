package com.demo.mavenTutorial.service;

import com.alibaba.fastjson.JSON;
import com.baidubce.services.iotdm.model.v3.device.DeviceAccessDetailResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceProfileListResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceProfileResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceViewResponse;
import com.baidubce.services.iotdm.model.v3.schema.Schema;
import com.baidubce.services.iotdm.model.v3.schema.SchemaListResponse;
import com.baidubce.services.iothub.model.ListResponse;
import com.baidubce.services.iothub.model.QueryEndpointResponse;
import com.demo.mavenTutorial.domain.DeviceView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shaoy
 * @date 2019/3/28 14:49
 */
public interface BCSIotService {

    ListResponse getEndpoints(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY);

    QueryEndpointResponse createEndpoint(String endPointName, String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY);

    DeviceProfileListResponse getDeviceList(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY, String name, String value);

    DeviceProfileResponse getDeviceProfile(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY, String deviceName);

    DeviceViewResponse getDeviceView(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY, String deviceName);

    DeviceAccessDetailResponse getDeviceAccess(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY, String deviceName);

    DeviceViewResponse updateDevice(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY, ArrayList<DeviceView> paramList);

    SchemaListResponse getDeviceModel(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY, Integer index, Integer size, String orderBy, String order, String key);
}
