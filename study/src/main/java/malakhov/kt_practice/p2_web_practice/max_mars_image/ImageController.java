package malakhov.kt_practice.p2_web_practice.max_mars_image;

import malakhov.kt_practice.p2_web_practice.max_mars_image.model.Photo;
import malakhov.kt_practice.p2_web_practice.max_mars_image.model.PhotoList;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
public class ImageController {

    private final String url = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";
    private final String apiKey = "mafCqLdtcqSpmeUw8ZDUnJCUd22DLqE9f5RcSBDJ";

    @Cacheable(cacheNames = "largest image")
    @GetMapping(path = "nasa/range/{from}/{to}" /*produces = {IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE}*/ )
    public Photo getLargestPhoto(@PathVariable("from") int from, @PathVariable("to") int to) {
        Photo photo = new Photo();
        photo.setId(12);
        return photo;//findLargestPhoto(from, to);
        //return new RedirectView(findLargestPhoto(from, to).getImageSource());
    }

    public byte[] findLargestPhoto(int from, int to) {
        RestClient restClient = RestClient.create();
        List<Photo> photoList = new ArrayList<>();

        for (int i = from; i <= to; i++) {
            String uri = UriComponentsBuilder.fromUriString(url)
                    .queryParam("sol", i)
                    .queryParam("api_key", apiKey)
                    .build().toUriString();

            photoList.addAll(Objects.requireNonNull(restClient.get()
                            .uri(uri)
                            .accept(APPLICATION_JSON)
                            .retrieve()
                            .body(PhotoList.class))
                    .getPhotos());
        }

        return photoList.parallelStream()
                .map(photo -> {
                    URI uri = restClient.head()
                            .uri(photo.getImageSource())
                            .retrieve()
                            .toBodilessEntity()
                            .getHeaders()
                            .getLocation();
                    photo.setOriginUri(uri);
                    long photoSize = restClient.head()
                            .uri(uri)
                            .retrieve()
                            .toBodilessEntity()
                            .getHeaders()
                            .getContentLength();
                    photo.setContentLength(photoSize);
                    return photo;
                })
                .max(Comparator.comparing(Photo::getContentLength))
                .map(photo -> restClient.get()
                        .uri(photo.getOriginUri())
                        .retrieve()
                        .body(byte[].class))
                .orElseThrow();
    }
}
