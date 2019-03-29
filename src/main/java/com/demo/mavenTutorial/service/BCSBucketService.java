package com.demo.mavenTutorial.service;/**
 * @author shaoy
 * @date 2019/3/28 10:32
 */

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @ClassName BCSBucketService
 * @Description TODO
 * @Version 1.0
 **/
public interface BCSBucketService {
    ArrayList<String> getBCSBucket(String ak, String sk);

    Boolean createBCSBucket(String bucketName, String ak, String sk);

    String uploadFile(MultipartFile file, String ak, String sk) throws IOException;

    String getFileUrl(String fileName, String ak, String sk);
}
