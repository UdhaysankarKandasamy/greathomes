package com.udhay.self;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.cloud.automl.v1.AnnotationPayload;
import com.google.cloud.automl.v1.ExamplePayload;
import com.google.cloud.automl.v1.Image;
import com.google.cloud.automl.v1.ModelName;
import com.google.cloud.automl.v1.PredictRequest;
import com.google.cloud.automl.v1.PredictResponse;
import com.google.cloud.automl.v1.PredictionServiceClient;
import com.google.protobuf.ByteString;

@Component
public class VisionClassificationPredict {

	static String filePath = "https://scontent.xx.fbcdn.net/v/t1.0-9/p720x720/91192822_108005834183871_4512076728876662784_o.jpg?_nc_cat=106&_nc_sid=8024bb&_nc_oc=AQlmpQF9OK_IvZ33TuEKkjXcUvu2La629fwruHCJD1dRAW8-BqzM7YGxMQFfLC3RfDQ&_nc_ht=scontent.xx&_nc_tp=6&oh=71f808787d6ac8a21645244f11d118cb&oe=5EA7E0D9";
	
	/*
	 * public static void main(String[] args) throws IOException { //
	 * TODO(developer): Replace these variables before running the sample. String
	 * projectId = "uplifted-matrix-272403"; String modelId =
	 * "ICN1754605053651451904"; predict(projectId, modelId, filePath); }
	 */

	/*
	 * static void authExplicit(String jsonPath) throws IOException {
	 * GoogleCredentials credentials = GoogleCredentials.fromStream(new
	 * FileInputStream(jsonPath)) .createScoped(Lists.newArrayList(
	 * "https://www.googleapis.com/auth/cloud-platform")); Storage storage =
	 * StorageOptions.newBuilder().setCredentials(credentials).build().getService();
	 * System.out.println("Buckets:"); // Page<Bucket> buckets = storage.list();
	 * 
	 * for (Bucket bucket : buckets.iterateAll()) {
	 * System.out.println(bucket.toString()); }
	 * 
	 * }
	 */
	
	
	/*
	 * static void getFile() { RestTemplate rt = new RestTemplate(); byte[]
	 * imageBytes = rt.getForObject(filePath, byte[].class); try {
	 * System.out.println("byte array "+imageBytes);
	 * Files.write(Paths.get("image.jpg"), imageBytes); } catch (IOException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } }
	 */

	public String predict(String projectId, String modelId, String filePath) throws IOException {
		// Initialize client that will be used to send requests. This client only needs
		// to be created
		// once, and can be reused for multiple requests. After completing all of your
		// requests, call
		// the "close" method on the client to safely clean up any remaining background
		// resources.
		try (PredictionServiceClient client = PredictionServiceClient.create()) {
			// Get the full path of the model.
			ModelName name = ModelName.of(projectId, "us-central1", modelId);
			
			RestTemplate rt = new RestTemplate();
			byte[] imageBytes = rt.getForObject(filePath, byte[].class);
			ByteString content = ByteString.copyFrom(imageBytes);
			Image image = Image.newBuilder().setImageBytes(content).build();
			ExamplePayload payload = ExamplePayload.newBuilder().setImage(image).build();
			PredictRequest predictRequest = PredictRequest.newBuilder().setName(name.toString()).setPayload(payload)
					.putParams("score_threshold", "0.5") // [0.0-1.0] Only produce results higher than this value
					.build();
			PredictResponse response = client.predict(predictRequest);
			for (AnnotationPayload annotationPayload : response.getPayloadList()) {
				System.out.format("Predicted class name: %s\n", annotationPayload.getDisplayName());
				System.out.format("Predicted class score: %.2f\n", annotationPayload.getClassification().getScore());
			}
			return response.getPayloadList().get(0).getDisplayName();
		}
	}
}