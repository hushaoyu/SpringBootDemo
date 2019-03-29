package com.demo.mavenTutorial.common;
/**
 * @author shaoy
 * @date 2019/3/28 15:16
 */

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iotdm.IotDmV3Client;

/**
 * @ClassName InitIotDmClient
 * @Description TODO
 * @Version 1.0
 **/
public class InitIotDmClient {
    public IotDmV3Client initDmClient(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY) {
        // 创建配置
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY))
                .withEndpoint(endpoint);

        // 初始化一个IotDmV3Client
        IotDmV3Client client = new IotDmV3Client(config);

        return client;
    }
}
