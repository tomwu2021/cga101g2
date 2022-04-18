package aws;

import java.io.*;
import java.sql.Connection;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.fasterxml.uuid.Generators;
import com.picture.model.PictureVO;
import com.util.ImagesUtils;
import com.util.StreamUtils;
import org.apache.commons.io.IOUtils;

public class S3Service {

    private static final String BUCKETNAME = "cga101-02";


    public PictureVO uploadImageToS3(InputStream in, String fileName) {
        PictureVO vo = new PictureVO();
        String key = getGenerateFileKey(fileName);
        String url = "https://" + BUCKETNAME + ".s3.ap-northeast-1.amazonaws.com/" + key;
        AmazonS3 s3client = new AwsService().getS3Client();
        // 複製InputStream 給原圖和縮圖使用
        ByteArrayOutputStream baos = new StreamUtils().CloneInputStream(in);
        InputStream originalIn = new ByteArrayInputStream(baos.toByteArray());
        InputStream ready4SmallIn = new ByteArrayInputStream(baos.toByteArray());

        try {
            // Put the small image in S3
            int compressPx = 400; //縮圖寬度尺寸
            String smallImageName = compressPx + "px_" + fileName;
            String smallImageKey = getGenerateFileKey(smallImageName);
            String smallImageUrl = "https://" + BUCKETNAME + ".s3.ap-northeast-1.amazonaws.com/" + smallImageKey;

            //產生縮圖file start
            InputStream smallImage = new ImagesUtils().changeToSmallImg_w(ready4SmallIn, "png", compressPx);
            // 將stream 讀取成byte陣列
            byte[] buf2 = new byte[smallImage.available()];
            long contentLength2 = buf2.length;
            ObjectMetadata metadata2 = new ObjectMetadata();
            metadata2.setContentLength(contentLength2);
            //上傳至S3
            PutObjectRequest req2 = new PutObjectRequest(BUCKETNAME, smallImageKey, smallImage, metadata2)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            s3client.putObject(req2);
            //產生縮圖file end


            // 將原圖上傳至S3 start
            byte[] buf = new byte[originalIn.available()];
            long contentLength = buf.length;
            new Thread(() -> {
                ObjectMetadata metadata = new ObjectMetadata();
                metadata.setContentLength(contentLength);
                PutObjectRequest req = new PutObjectRequest(BUCKETNAME, key, originalIn, metadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead);
                s3client.putObject(req);
            }).start();
            // 將原圖上傳至S3 end


            //設置VO
            vo.setFileKey(key);
            vo.setFileName(fileName);
            vo.setUrl(url);
            vo.setSize(contentLength);
            vo.setPreviewUrl(smallImageUrl);
            vo.setPreviewKey(smallImageKey);

        } catch (AmazonServiceException ase) {
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Error Message: " + ace.getMessage());
        } catch (IOException e) {
            System.out.println("Error Message: " + e.getMessage());
            e.printStackTrace();
        }
        return vo;
    }

    public boolean deleteS3Image(String fileKey) {
        AmazonS3 s3client = new AwsService().getS3Client();
        try {
//			 delete picture from S3
            s3client.deleteObject(new DeleteObjectRequest(BUCKETNAME, fileKey));

            return true;
        } catch (AmazonServiceException ase) {
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
            return false;
        } catch (AmazonClientException ace) {
            System.out.println("Error Message: " + ace.getMessage());
            return false;
        }
    }

    public String getGenerateFileKey(String fileName) {
        String keyName = "thumbs/";
        UUID uuid = Generators.timeBasedGenerator().generate();
        keyName += uuid + "-" + fileName;
        return keyName;
    }

}
