package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.analytics.AnalyticsTestsModel;
import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientUtils {

    public static AnalyticsTestsModel sendRequest() {
        URI uri = null;
        try {
            uri = new URIBuilder()
                    .setScheme(PropertyUtils.getPropertyData(PropertyUtils.URL_SCHEME))
                    .setHost(PropertyUtils.getPropertyData(PropertyUtils.URL_HOST))
                    .setPathSegments("api", "runs", PropertyUtils.getPropertyData(PropertyUtils.URL_RUN_ID), "tests")
                    .build();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization", "Bearer " + PropertyUtils.getPropertyData(PropertyUtils.USER_TOKEN))
                .GET()
                .build();

        return readResponse(httpRequest);
    }

    private static AnalyticsTestsModel readResponse(HttpRequest request) {
        HttpResponse<byte[]> response = null;

        try {
            response = HttpClient
                    .newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofByteArray());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        if (response == null) {
            System.err.println("Response entity is null");
            return null;
        }

        AnalyticsTestsModel analyticsTestsModel = null;
        try {
            analyticsTestsModel = new ObjectMapper().readValue(response.body(), AnalyticsTestsModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return analyticsTestsModel;
    }
}
