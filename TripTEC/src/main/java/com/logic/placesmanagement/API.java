package com.logic.placesmanagement;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlaceDetailsRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

public class API {
	GeoApiContext context;
	Gson gson;
	String placeId;
	
	public GeoApiContext getContext() {
		return context;
	}
	public void setContext(GeoApiContext context) {
		this.context = context;
	}
	public Gson getGson() {
		return gson;
	}
	public void setGson(Gson gson) {
		this.gson = gson;
	}
	public String getPlaceId() {
		return placeId;
	}
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	public void Test() throws ApiException, InterruptedException, IOException {
		context = new GeoApiContext.Builder().apiKey("AIzaSyAMNKLnQIP4wvOZFQUB0PKnANDMuK9hty0").build();
		GeocodingResult[] results =  GeocodingApi.geocode(context,"1600 Amphitheatre Parkway Mountain View, CA 94043").await();
		gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(results[0].addressComponents));
		System.out.println(gson.toJson(results[0].placeId));
		placeId = results[0].placeId;
	}
	public PlaceDetailsRequest placeDetails(GeoApiContext context, String placeId) {
		PlaceDetailsRequest request = new PlaceDetailsRequest(context);
		request.placeId(placeId);
		return request;
	}
}