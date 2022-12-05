package elasticsearch.demo.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import elasticsearch.demo.document.db_location_5;
import elasticsearch.demo.helper.Indices;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    private static final Logger LOG = LoggerFactory.getLogger(VehicleService.class);
    private static final ObjectMapper MAPPER = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
    private final RestHighLevelClient client;

    public LocationService(RestHighLevelClient client) {
        this.client = client;
    }

    public Boolean index(final db_location_5 db_location_5) {
        try {
            final String db_location_5AsString = MAPPER.writeValueAsString(db_location_5);

            final IndexRequest request = new IndexRequest(Indices.DB_LOCATION_5);
            request.id(String.valueOf(db_location_5.getId()));
            request.source(db_location_5AsString, XContentType.JSON);

            final IndexResponse response = client.index(request, RequestOptions.DEFAULT);

            return response != null && response.status().equals(RestStatus.OK);
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            return false;
        }
    }
}
