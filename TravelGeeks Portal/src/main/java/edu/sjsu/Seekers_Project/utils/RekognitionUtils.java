package edu.sjsu.Seekers_Project.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.AmazonRekognitionException;
import com.amazonaws.services.rekognition.model.DetectLabelsRequest;
import com.amazonaws.services.rekognition.model.DetectLabelsResult;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.Label;
import com.amazonaws.services.rekognition.model.S3Object;

import edu.sjsu.Seekers_Project.model.PlaceDetails;

public class RekognitionUtils {
	final static Logger logger = Logger.getLogger(RekognitionUtils.class);
	private static AmazonRekognition rekognitionClient = null;

	private static Map<String, PlaceDetails.CategoryEnum> categoryLabels = new HashMap<String, PlaceDetails.CategoryEnum>();
	private static Set<String> mountain = new HashSet<String>(Arrays.asList("Crest", "Mountain", "Canyon","Glacier","Mountain Range", "Ice", "Snow"));
	private static Set<String> forest = new HashSet<String>(Arrays.asList("Forest", "Land", "Marsh","Swamp", "Jungle", "Plant", "Tree", "Vegetation", "Flora", "Park", "Flower", "Blossom"));
	private static Set<String> beach = new HashSet<String>(Arrays.asList("Beach", "Lake","Island", "Ocean","Sea", "Coast", "Harbor", "Port", "Palm Tree", "Waterfront","Water"));
	private static Set<String> architecture = new HashSet<String>(Arrays.asList("Building","Architecture" , "Tower", "Lighthouse", "Downtown", "Beacon","Dome", "Monument" ));
	
	public RekognitionUtils() {

	}

	public AmazonRekognition getRekognitionClient() {
		try {
			if (rekognitionClient == null) {
				AWSCredentialsProvider awsCredentialsProvider = new ClasspathPropertiesFileCredentialsProvider(
						"config/application.properties");
				awsCredentialsProvider.getCredentials();
				rekognitionClient = AmazonRekognitionClientBuilder.standard().withRegion(Regions.US_EAST_1)
						.withCredentials(awsCredentialsProvider).build();

			}

		} catch (AmazonServiceException ase) {
			ase.printStackTrace();
			return null;
		} catch (AmazonClientException ace) {
			ace.printStackTrace();
			return null;
		}
		return rekognitionClient;
	}

	public PlaceDetails.CategoryEnum getPlaceCategory(String username, String fileName) {
		if (rekognitionClient == null)
			getRekognitionClient();

		String key = username + "/" + fileName;
		String bucketName = CommonUtils.FILE_PROPERTIES.get("basebucket");

		DetectLabelsRequest request = new DetectLabelsRequest()
				.withImage(new Image().withS3Object(new S3Object().withName(key).withBucket(bucketName)))
				.withMaxLabels(10).withMinConfidence(50F);

		logger.info("key ::  " + key);
		logger.info("bucketName :: " + bucketName);

		PlaceDetails.CategoryEnum ret = null;
		try {
			DetectLabelsResult result = rekognitionClient.detectLabels(request);
			List<Label> labels = result.getLabels();
			Set<String> categoriesIdentified = new HashSet<String>();

			logger.info("Detected labels for " + key);
			logger.info("labels " + labels);

			if (labels != null) {

				logger.info(labels.get(0).getName() + ": " + labels.get(0).getConfidence().toString());

				for (Label label : labels) {
					categoriesIdentified.add(label.getName());
				}

				if (CollectionUtils.containsAny(categoriesIdentified, mountain)) {
					ret = PlaceDetails.CategoryEnum.Mountains;
				} else if (CollectionUtils.containsAny(categoriesIdentified, forest)) {
					ret = PlaceDetails.CategoryEnum.Forest;
				} else if (CollectionUtils.containsAny(categoriesIdentified, beach)) {
					ret = PlaceDetails.CategoryEnum.Beach;
				} else if (CollectionUtils.containsAny(categoriesIdentified, architecture)) {
					ret = PlaceDetails.CategoryEnum.Architecture;
				} else {
					ret = PlaceDetails.CategoryEnum.Architecture;
				}

				System.out.println("ret: " + categoriesIdentified.toString());
			}

		} catch (AmazonRekognitionException e) {
			logger.error(e);
			return ret;
		}
		return ret;
	}

}
