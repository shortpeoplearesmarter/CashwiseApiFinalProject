package entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestBody {

    //creating a client
    private String company_name;
    private String client_name;
    private String seller_name;
    private String email;
    private String phone_number;
    private String address;
    private List<Integer> tags_id;


}
