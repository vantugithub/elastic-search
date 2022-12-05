package elasticsearch.demo.search.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedRequestDTO implements Serializable {
    private int page;
    private int size;
}
