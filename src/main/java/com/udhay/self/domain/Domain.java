/**
 * 
 */
package com.udhay.self.domain;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.udhay.self.DataResponse;
import com.udhay.self.ImageResponse;
import com.udhay.self.PostResponse;
import com.udhay.self.VisionClassificationPredict;

/**
 * @author udhay
 *
 */
@Service
public class Domain {
	
	@Autowired
	VisionClassificationPredict predict;

	static String accessToken = "EAAC894OT9c8BAAL7CZBvwYsU2MBZCnFSbvTlZBFpEyZASrgF5qIs84V58CdcO7l4efHziyZAznmT7uQZCk6eN5vBJQLdTJnD36xSGZAmyP7Y65WNJg9dZB8GfgqYJHi37hL8VLSzXonS8binAeJmgr8mqZBfBYTbsnlklcTA1t3axIecY8795Ci3z";
	static String pageAccessToken = "EAAC894OT9c8BAIdgu9YthcmDmS2ZBHZAj2lRCv4ZBmS25VO5Ac0LrLpsxc4oi1AoQtPEgIniNhqBXkwKXZCa0L1ThOWTaWIWNd5m9sY2UhZAMjfVlZCZCbPBPCsetnhj8ZBJlkp4e46Wts86iqdE18MVZCVjvVqn9qnZAjuTFZCLZAzBZBUaJJHpq3HK5SQZCXLJ1ihyIZD";
	static String pageID = "106853594299095";
	static String siteURL = "https://www.ikea.com/ca/en/p/kivik-sofa-skiftebo-dark-gray-s89305583/";
	static String fbBaseUrl = "https://graph.facebook.com/v6.0/";
	String projectId = "uplifted-matrix-272403";
	String modelId = "ICN1754605053651451904";
	
	String url20200356 = "https://www.wayfair.ca/furniture/pdp/andover-mills-bjorn-59-rolled-arm-settee-c000165139.html";
	String url20200398 = "https://www.wayfair.ca/furniture/pdp/greyleigh-riddleville-lift-top-coffee-table-with-storage-gryl5418.html?piid=37356226";
	
	String sku="20200356";
	
	Gson gson = new Gson();

	RestTemplate restTemplate = new RestTemplate();

	public void startSeriesActions() {
		System.out.println("--------------------Getting posts-------------------");
		String postID = getLatestPost();
		System.out.println("--------------------Getting image URL--------------------");
		String imageURL = getImageURL(postID);
		System.out.println("--------------------Getting product SKU from google-------------------");
		try {
			sku = getSkuFromGVision(imageURL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("--------------------Comment in post comment----------------------");
		postComment(postID, sku);
		System.out.println("--------------------Action completed-----------------------");
	}

	private String getLatestPost() {
		String postUrl = fbBaseUrl + pageID + "/posts?access_token=" + accessToken;
		PostResponse response = restTemplate.getForEntity(postUrl, PostResponse.class).getBody();
		System.out.println("Posts Response----> " + gson.toJson(response));
		return response.getData().get(0).getId();
	}

	private String getImageURL(String latestPostID) {
		String imageUrl = fbBaseUrl + latestPostID + "?fields=full_picture&access_token=" + accessToken;
		ImageResponse response = restTemplate.getForEntity(imageUrl, ImageResponse.class).getBody();
		System.out.println("Image Response-----> " + gson.toJson(response));
		return response.getFull_picture();
	}

	private String getSkuFromGVision(String imageURL) throws IOException {
		return predict.predict(projectId, modelId, imageURL);
	}

	private void postComment(String postID, String sku) {
		System.out.println("Sku found from google Vision "+sku);
		String commentMessage=url20200356;
		if("20200398".equalsIgnoreCase(sku)) {
			commentMessage=url20200398;
		}
		String commentUrl = fbBaseUrl + postID + "/comments?message=" + commentMessage + "&access_token="
				+ pageAccessToken;
		System.out.println("comment Message --> "+commentUrl);
		DataResponse response = restTemplate.postForEntity(commentUrl, null, DataResponse.class).getBody();
		System.out.println("Comment Response ------>  " + gson.toJson(response));
	}

}
