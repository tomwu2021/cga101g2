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

	public static final String BUCKETNAME = "cga101-02";
	public static final String CDN = "https://d148yrb2gzai3l.cloudfront.net/";

	public PictureVO uploadImageToS3(InputStream in, String fileName) {
		PictureVO vo = new PictureVO();
		String key = getGenerateFileKey(fileName);

		String url = (CDN + key).replace(" ","%20");
		String previewUrl = (url + "?d=600x400").replace("%20","%2520");
		AmazonS3 s3client = new AwsService().getS3Client();

		try {
			// 將原圖上傳至S3 start
			byte[] buf = new byte[in.available()];
			long contentLength = buf.length;
			new Thread(() -> {
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(contentLength);
				PutObjectRequest req = new PutObjectRequest(BUCKETNAME, key, in, metadata)
						.withCannedAcl(CannedAccessControlList.PublicRead);
				s3client.putObject(req);
			}).start();
			// 將原圖上傳至S3 end

			// 設置VO
			vo.setFileKey(key);
			vo.setFileName(fileName);
			vo.setUrl(url);
			vo.setSize(contentLength);
			vo.setPreviewUrl(previewUrl);

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
