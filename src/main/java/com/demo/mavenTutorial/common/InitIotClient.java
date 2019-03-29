package com.demo.mavenTutorial.common;/**
 * @author shaoy
 * @date 2019/3/28 14:51
 */

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.iothub.IotHubClient;

/**
 * @ClassName InitIotClient
 * @Description TODO
 * @Version 1.0
 **/
public class InitIotClient {
    public IotHubClient initIotHubClient(String endpoint, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY) {
        BceClientConfiguration config = new BceClientConfiguration()
                .withCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY))
                .withEndpoint(endpoint);
        IotHubClient iotHubClient = new IotHubClient(config);

        return iotHubClient;
    }
}
