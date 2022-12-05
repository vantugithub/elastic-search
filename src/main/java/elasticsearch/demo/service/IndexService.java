package elasticsearch.demo.service;

import elasticsearch.demo.helper.Indices;
import elasticsearch.demo.helper.Util;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class IndexService {
    private static final Logger LOG = LoggerFactory.getLogger(IndexService.class);
    private static final List<String> INDICES = Arrays.asList(Indices.VEHICLE_INDEX);
    private final RestHighLevelClient restHighLevelClient;

    @Autowired
    public IndexService(RestHighLevelClient restHighLevelClient) {
        this.restHighLevelClient = restHighLevelClient;
    }

    @PostConstruct
    public void tryToCreateIndices() {
        recreateIndices(false);
    }

    public void recreateIndices(boolean deleteExisting) {
        String settings = Util.loadAsString("static/es-settings.json");

        if (settings == null) {
            LOG.error("Failed to load index settings");
            return;
        }
        for (String indexName : INDICES){
            try {
                boolean indexExists = restHighLevelClient.indices().exists(new GetIndexRequest(indexName), RequestOptions.DEFAULT);
                if (indexExists) {
                    if (!deleteExisting) {
                        continue;
                    }
                    restHighLevelClient.indices().delete(new DeleteIndexRequest(indexName), RequestOptions.DEFAULT);
                }
                CreateIndexRequest createIndexRequest = new CreateIndexRequest(indexName);


                String mappings = loadMappings(indexName);

                if (mappings != null) {
                    createIndexRequest.mapping(mappings, XContentType.JSON);
                }
                createIndexRequest.settings(settings, XContentType.JSON);
                restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            }
            catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    private String loadMappings(String indexName){
        StringBuilder pathStr = new StringBuilder();
        pathStr.append("static/mappings/").append(indexName).append(".json");
        String mappings = Util.loadAsString(pathStr.toString());

        if (mappings == null) {
            LOG.error("Failed to load mappings for index name '{}'", indexName);

            return null;
        }

        return mappings;
    }
}
