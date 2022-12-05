package elasticsearch.demo.controller;

import elasticsearch.demo.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/index")
public class IndexController {

    private final IndexService indexService;

    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @PostMapping("/recreate")
    public void recreateAllIndices() {
        indexService.recreateIndices(true);
    }

}
