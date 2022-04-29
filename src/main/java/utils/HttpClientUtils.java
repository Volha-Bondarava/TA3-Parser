package utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.logging.Logger;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.http.client.utils.URIBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.analytics.AnalyticsTestsModel;

public final class HttpClientUtils {
    private static final Logger LOG = Logger.getLogger(HttpClientUtils.class.getSimpleName());

    private HttpClientUtils() {}

    public static AnalyticsTestsModel getSpecifiedTests(MultiValuedMap<String, String> parameters) {
        URI uri = null;
        try {
            URIBuilder uriBuilder = new URIBuilder()
                    .setScheme(PropertyUtils.getPropertyData(PropertyUtils.URL_SCHEME))
                    .setHost(PropertyUtils.getPropertyData(PropertyUtils.URL_HOST))
                    .setPathSegments("api", "runs", PropertyUtils.getPropertyData(PropertyUtils.URL_RUN_ID), "tests")
                    .setParameter("page[num]", "1").setParameter("page[size]", PropertyUtils.getPropertyData(PropertyUtils.URL_TESTS_LIMIT));
            parameters.entries().forEach(entry -> uriBuilder.addParameter(entry.getKey(), entry.getValue()));
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            LOG.severe(e.getMessage());
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
                    .send(request, BodyHandlers.ofByteArray());
           LOG.info(request.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        if (response == null) {
            LOG.info("Response entity is null");
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
