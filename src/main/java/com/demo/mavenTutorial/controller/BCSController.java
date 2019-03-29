package com.demo.mavenTutorial.controller;
/**
 * @author shaoy
 * @date 2019/3/28 10:59
 */

import com.baidubce.services.iotdm.model.v3.device.DeviceAccessDetailResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceProfileListResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceProfileResponse;
import com.baidubce.services.iotdm.model.v3.device.DeviceViewResponse;
import com.baidubce.services.iotdm.model.v3.schema.SchemaListResponse;
import com.baidubce.services.iothub.model.ListResponse;
import com.baidubce.services.iothub.model.QueryEndpointResponse;
import com.demo.mavenTutorial.domain.DeviceView;
import com.demo.mavenTutorial.service.BCSBucketService;
import com.demo.mavenTutorial.service.BCSIotService;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BCSController
 * @Description TODO
 * @Version 1.0
 **/
@RequestMapping("/bcs")
@RestController
@Api(value = "BCS")
public class BCSController {
    @Value("${bcs.bos.ak}")
    String ACCESS_KEY_ID;
    @Value("${bcs.bos.sk}")
    String SECRET_ACCESS_KEY;
    @Value("${bcs.iot.endpoint}")
    String endPoint;
    @Value("${bcs.dm.endpoint}")
    String dmEndPoint;

    @Autowired
    BCSBucketService bcsBucketService;
    @Autowired
    BCSIotService bcsIotService;

    @ApiOperation(value = "获取存储桶列表")
    @RequestMapping(value = "/bos/getBuckets", method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> getBuckets() {
        return bcsBucketService.getBCSBucket(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
    }

    @ApiOperation(value = "创建存储桶")
    @ApiImplicitParam(name = "bucketName", value = "桶名称", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/bos/createBucket", method = RequestMethod.POST)
    @ResponseBody
    public Boolean getBuckets(@RequestParam String bucketName) {
        return bcsBucketService.createBCSBucket(bucketName, ACCESS_KEY_ID, SECRET_ACCESS_KEY);
    }

    @ApiOperation(value = "上传文件到存储桶")
    @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "file", paramType = "form")
    @RequestMapping(value = "/bos/uploadFile", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public String uploadFile(@RequestBody MultipartFile file) throws IOException {
        return bcsBucketService.uploadFile(file, ACCESS_KEY_ID, SECRET_ACCESS_KEY);
    }

    @ApiOperation(value = "获取存储桶文件url")
    @ApiImplicitParam(name = "fileName", value = "文件名称/路径", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/bos/getUrl", method = RequestMethod.GET)
    @ResponseBody
    public String getUrl(@RequestParam String fileName) {
        return bcsBucketService.getFileUrl(fileName, ACCESS_KEY_ID, SECRET_ACCESS_KEY);
    }

    @ApiOperation(value = "获取IOT HUB 项目列表")
    @RequestMapping(value = "/iot/getEndpoint", method = RequestMethod.GET)
    @ResponseBody
    public ListResponse getEndpoints() {
        return bcsIotService.getEndpoints(endPoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY);
    }

    @ApiOperation(value = "创建IOT HUB项目")
    @ApiImplicitParam(name = "endPointName", value = "IOT HUG项目所在终端节点", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/iot/createEndpoint", method = RequestMethod.GET)
    @ResponseBody
    public QueryEndpointResponse getEndpoints(@RequestParam String endPointName) {
        return bcsIotService.createEndpoint(endPointName, endPoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY);
    }

    @ApiOperation(value = "获取IOT HUB项目设备列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "需要查询的属性名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "value", value = "该属性的值", required = true, dataType = "String", paramType = "query")})
    @RequestMapping(value = "/iot/getDevices", method = RequestMethod.GET)
    @ResponseBody
    public DeviceProfileListResponse getDevices(@RequestParam String name, @RequestParam String value) {
        return bcsIotService.getDeviceList(dmEndPoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY, name, value);
    }

    @ApiOperation(value = "获取IOT HUB项目设备的profile")
    @ApiImplicitParam(name = "deviceName", value = "设备名称", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/iot/getDeviceProfile", method = RequestMethod.GET)
    @ResponseBody
    public DeviceProfileResponse getDeviceProfile(@RequestParam String deviceName) {
        return bcsIotService.getDeviceProfile(dmEndPoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY, deviceName);
    }

    @ApiOperation(value = "获取IOT HUB项目设备的view")
    @ApiImplicitParam(name = "deviceName", value = "设备名称", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/iot/getDeviceView", method = RequestMethod.GET)
    @ResponseBody
    public DeviceViewResponse getDeviceView(@RequestParam String deviceName) {
        return bcsIotService.getDeviceView(dmEndPoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY, deviceName);
    }

    @ApiOperation(value = "获取IOT HUB项目设备接入详情")
    @ApiImplicitParam(name = "deviceName", value = "设备名称", required = true, dataType = "String", paramType = "query")
    @RequestMapping(value = "/iot/getDeviceAccess", method = RequestMethod.GET)
    @ResponseBody
    public DeviceAccessDetailResponse getDeviceAccess(@RequestParam String deviceName) {
        return bcsIotService.getDeviceAccess(dmEndPoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY, deviceName);
    }

    @ApiOperation(value = "更新IOT HUB项目设备属性")
//    @ApiImplicitParam(name = "paramList", value = "设备名称", required = true)
    @RequestMapping(value = "/iot/updateDevice", method = RequestMethod.POST)
    @ResponseBody
    public DeviceViewResponse updateDevice(@RequestBody ArrayList<DeviceView> paramList) {
        return bcsIotService.updateDevice(dmEndPoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY, paramList);
    }

    @ApiOperation(value = "获取IOT HUB项目模型列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "index", value = "查询的结果页索引", required = true, dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "size", value = "查询的结果页大小", required = true, dataType = "int", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "orderBy", value = "排序的索引列", required = true, dataType = "String", paramType = "query", defaultValue = "createTime"),
            @ApiImplicitParam(name = "order", value = "升序还是排序", required = true, dataType = "String", paramType = "query", defaultValue = "asc"),
            @ApiImplicitParam(name = "key", value = "查询关键字，模型名称", required = true, dataType = "String", paramType = "query", defaultValue = "client_1")})
    @RequestMapping(value = "/iot/getDeviceModel", method = RequestMethod.GET)
    @ResponseBody
    public SchemaListResponse getDeviceModel(@RequestParam Integer index, @RequestParam Integer size, @RequestParam String orderBy, @RequestParam String order, @RequestParam String key) {
        return bcsIotService.getDeviceModel(dmEndPoint, ACCESS_KEY_ID, SECRET_ACCESS_KEY, index, size, orderBy, order, key);
    }
}
