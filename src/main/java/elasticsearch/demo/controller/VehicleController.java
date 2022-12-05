package elasticsearch.demo.controller;

import elasticsearch.demo.document.Vehicle;
import elasticsearch.demo.helper.Indices;
import elasticsearch.demo.search.request.SearchRequestDTO;
import elasticsearch.demo.search.response.VehicleResponse;
import elasticsearch.demo.service.FakeDataService;
import elasticsearch.demo.service.VehicleService;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;
    private final FakeDataService fakeDataService;

    @Autowired
    public VehicleController(VehicleService vehicleService, FakeDataService fakeDataService) {
        this.vehicleService = vehicleService;
        this.fakeDataService = fakeDataService;
    }

    @PostMapping("/search/multi")
    public <T> List<T> search(@RequestBody SearchRequestDTO searchRequestDTO){
        return (List<T>) vehicleService.multiSearch(Indices.VEHICLE_INDEX, searchRequestDTO, VehicleResponse.class);
    }

    @PostMapping("/search/handle")
    public <T> List<T> elasticSearchUsingHandleQuery(@RequestBody SearchRequestDTO searchRequestDTO){
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.should(QueryBuilders.matchPhrasePrefixQuery("description", searchRequestDTO.getTextSearch()).slop(10).maxExpansions(10));

        return (List<T>) vehicleService.handleSearch(Indices.VEHICLE_INDEX, searchRequestDTO, boolQueryBuilder, VehicleResponse.class);
    }

    @PostMapping("/search/match-phrase-prefix")
    public <T> List<T> elasticSearchUsingMatchPhrasePrefix(@RequestBody SearchRequestDTO searchRequestDTO) throws IOException {
        return (List<T>) vehicleService.matchPhrasePrefixSearch(Indices.VEHICLE_INDEX, searchRequestDTO, VehicleResponse.class);
    }

    @PostMapping("/search/regexp")
    public <T> List<T> elasticSearchUsingRegexp(@RequestBody SearchRequestDTO searchRequestDTO){
        return (List<T>) vehicleService.regexpSearch(Indices.VEHICLE_INDEX, searchRequestDTO, VehicleResponse.class);
    }

    @PostMapping("/search/match-phrase")
    public <T> List<T> elasticSearchUsingMatchPhrase(@RequestBody SearchRequestDTO searchRequestDTO){
        return (List<T>) vehicleService.matchPhraseSearch(Indices.VEHICLE_INDEX, searchRequestDTO, VehicleResponse.class);
    }

    @PostMapping("/search/boosting")
    public <T> List<T> elasticSearchUsingBoosting(@RequestBody SearchRequestDTO searchRequestDTO){
        return (List<T>) vehicleService.boostingSearch(Indices.VEHICLE_INDEX, searchRequestDTO, VehicleResponse.class);
    }

    @PostMapping("/search/fuzzy")
    public <T> List<T> elasticSearchUsingFuzzy(@RequestBody SearchRequestDTO searchRequestDTO){
        return (List<T>) vehicleService.fuzzySearch(Indices.VEHICLE_INDEX, searchRequestDTO, VehicleResponse.class);
    }

    @PostMapping("/search/wildcard")
    public <T> List<T> elasticSearchUsingWildcard(@RequestBody SearchRequestDTO searchRequestDTO){
        return (List<T>) vehicleService.wildCardSearch(Indices.VEHICLE_INDEX, searchRequestDTO, VehicleResponse.class);
    }
//    @PostMapping("/fake-data")
//    public void fakeData() {
//        fakeDataService.insertDummyData();
//    }
}
