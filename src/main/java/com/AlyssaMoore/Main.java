package com.AlyssaMoore;

import com.google.maps.ElevationApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import static com.google.maps.GeocodingApi.geocode;

public class Main {

    public static void main(String[] args) throws Exception{

        Scanner letterScanner = new Scanner(System.in);

        String key = null;
        try (BufferedReader reader = new BufferedReader(new FileReader("Key.txt"))) {
            key = reader.readLine();
        } catch (Exception ioe) {
            System.out.println("No key file found or could not read key, please verify key.txt exists");
        }

        GeoApiContext context = new GeoApiContext().setApiKey(key);

        System.out.println("Please enter a place");
        String place = letterScanner.nextLine();

        GeocodingApiRequest location = geocode(context, place);
        System.out.println(location);

        GeocodingResult[] results = GeocodingApi.geocode(context, place).await();

        int resultsToDisplay;
        if (results.length >= 1) {
            LatLng firstResult = results[0].geometry.location;
            System.out.println(firstResult);
        }
        else if (results.length == 0){
            System.out.println("Sorry, no results");
        }
    }
}