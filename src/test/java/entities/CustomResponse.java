package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {

    private String category_title;
//    private String email;

    //get a category id;
    private int category_id;
    List<CustomResponse> responses;

    private int seller_id;
    private String email;

}
