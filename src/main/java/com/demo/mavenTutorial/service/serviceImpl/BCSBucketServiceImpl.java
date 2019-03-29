package com.demo.mavenTutorial.service.serviceImpl;
/**
 * @author shaoy
 * @date 2019/3/28 10:34
 */

import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.model.BucketSummary;
import com.baidubce.services.bos.model.CreateBucketResponse;
import com.demo.mavenTutorial.common.InitBosClient;
import com.demo.mavenTutorial.service.BCSBucketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BCSBucketServiceImpl
 * @Description TODO
 * @Version 1.0
 **/
@Service
public class BCSBucketServiceImpl implements BCSBucketService {
    private static final Logger log = LoggerFactory.getLogger(BCSBucketServiceImpl.class);
    private String bucketName = "hushaoyu-bucket-1";

    @Override
    public ArrayList<String> getBCSBucket(String ACCESS_KEY_ID, String SECRET_ACCESS_KEY) {
        log.info("初始化BOS客户端");
        InitBosClient initBosClient = new InitBosClient();
        BosClient client = initBosClient.initCosClient(ACCESS_KEY_ID, SECRET_ACCESS_KEY);

        List<BucketSummary> buckets = client.listBuckets().getBuckets();

        ArrayList<String> bucketNames = new ArrayList<>();

        for (BucketSummary bucket : buckets) {
            bucketNames.add(bucket.getName());
        }

        log.info("bucket列表" + bucketNames);
        return bucketNames;
    }

    @Override
    public Boolean createBCSBucket(String bucketName, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY) {
        log.info("初始化BOS客户端");
        InitBosClient initBosClient = new InitBosClient();
        BosClient client = initBosClient.initCosClient(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        if (initBosClient.bucketExit(client, bucketName)) {
            log.warn("bucket already exit");
            return false;
        } else {
            client.createBucket(bucketName);
            CreateBucketResponse createBucketResponse = new CreateBucketResponse();
            log.info("bucket创建成功: " + createBucketResponse.getName());
            return true;
        }
    }

    @Override
    public String uploadFile(MultipartFile file, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY) throws IOException {
        log.info("初始化BOS客户端");
        InitBosClient initBosClient = new InitBosClient();
        BosClient client = initBosClient.initCosClient(ACCESS_KEY_ID, SECRET_ACCESS_KEY);

        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        InputStream inputStream = file.getInputStream();

        String objectKey = file.getOriginalFilename();

        try {
            client.putObject(bucketName, objectKey, inputStream);
            return "success";
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return "false";
        }
    }

    @Override
    public String getFileUrl(String fileName, String ACCESS_KEY_ID, String SECRET_ACCESS_KEY) {
        log.info("初始化BOS客户端");
        InitBosClient initBosClient = new InitBosClient();
        BosClient client = initBosClient.initCosClient(ACCESS_KEY_ID, SECRET_ACCESS_KEY);

        String fileUrl = client.generatePresignedUrl(bucketName, fileName, -1).toString();

        return fileUrl;
    }
}
