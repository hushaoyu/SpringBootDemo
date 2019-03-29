package com.demo.mavenTutorial.common;
/**
 * @author shaoy
 * @date 2019/3/28 11:07
 */

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;

/**
 * @ClassName InitBosClient
 * @Description TODO
 * @Version 1.0
 **/
public class InitBosClient {

    /**
     * @param ACCESS_KEY_ID
     * @param SECRET_ACCESS_KEY
     * @Description 初始化client
     */
    public BosClient initCosClient(String ACCESS_KEY_ID, String SECRET_ACCESS_KEY) {
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        BosClient client = new BosClient(config);

        return client;
    }

    /**
     * @param bosClient
     * @param bucketName
     * @Description 判断存储桶是否存在
     */
    public Boolean bucketExit(BosClient bosClient, String bucketName) {
        boolean exists = bosClient.doesBucketExist(bucketName);
        return exists;
    }
}
