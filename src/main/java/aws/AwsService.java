package aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AwsService {

	private static final String accessKey = "AKIATRU576IJDQJ4TVWP";
	private static final String secretAccessKey = "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";

	
	public BasicAWSCredentials getAWSCredentials() {
		BasicAWSCredentials creds = new BasicAWSCredentials(accessKey, secretAccessKey);
		return creds;
	}

	public AmazonS3 getS3Client() {
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(getAWSCredentials()))
				.withRegion(Regions.AP_NORTHEAST_1).build();
		return s3client;
	}
}
