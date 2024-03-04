package eBook.EatBook.global.aladin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;



@Service
@RequiredArgsConstructor
public class AladinService {

    @Value("${spring.custom.aladinkey}")
    private String aladinApiKey;

    public String searchBooks(String query, int maxResults, int start) {
        String apiUrl = "http://www.aladin.co.kr/ttb/api/ItemSearch.aspx";

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("ttbkey", aladinApiKey)
                .queryParam("Query", query)
                .queryParam("QueryType", "Title")
                .queryParam("MaxResults", maxResults)
                .queryParam("start", start)
                .queryParam("SearchTarget", "Book")
                .queryParam("output", "xml")
                .queryParam("Version", "20131101");

        return new RestTemplate().getForObject(builder.toUriString(), String.class);
    }


}
