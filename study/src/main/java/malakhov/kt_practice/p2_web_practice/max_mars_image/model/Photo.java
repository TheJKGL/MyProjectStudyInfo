package malakhov.kt_practice.p2_web_practice.max_mars_image.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Setter
@Getter
public class Photo {
    int id;
    int sol;
    @JsonProperty("img_src")
    String imageSource;

    long contentLength;
    URI originUri;

    public Photo() {
    }
}
