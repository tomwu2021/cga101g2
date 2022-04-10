package aws;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.fasterxml.uuid.Generators;
import com.picture.model.PictureVO;

public class S3Service {

	private static final String bucketName = "cga101-02";

	public PictureVO uploadImageToS3(InputStream in, String fileName) {
		PictureVO vo = new PictureVO();
		String key = getGenerateFileKey(fileName);
		String url = "https://" + bucketName + ".s3.ap-northeast-1.amazonaws.com/";
		url += key;
		AmazonS3 s3client = new AwsService().getS3Client();
		try {
			byte[] buf = new byte[in.available()];
			Long contentLength = Long.valueOf(buf.length);
			// Obtain the Content length of the Input stream for S3 header
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(contentLength);

			PutObjectRequest req = new PutObjectRequest(bucketName, key, in, metadata)
					.withCannedAcl(CannedAccessControlList.PublicRead);
			s3client.putObject(req);
			// Put the object in S3
			vo.setFileKey(key);
			vo.setFileName(fileName);
			vo.setpUrl(url);
			vo.setSize(contentLength);

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

	public String getGenerateFileKey(String fileName) {
		String keyName = "thumbs/";
		UUID uuid = Generators.timeBasedGenerator().generate();
		keyName += uuid + "-" + fileName;
		return keyName;
	}

}
