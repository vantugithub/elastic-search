package elasticsearch.demo.document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;

@Data
public class Vehicle {
    private String id;
    private String number;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date created;
    private boolean status;
    private int quantity;
    private String description;
}
