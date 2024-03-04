package eBook.EatBook.global.aladin.cotnroller;

import eBook.EatBook.global.aladin.service.AladinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class aladinApiController {

    private final AladinService aladinService;

    @GetMapping("/search-books")
    public String searchBooks(@RequestParam String s) {
        // 기본적으로 최대 10개의 결과를 가져오고, 시작 페이지는 1로 설정
        int maxResults = 10;
        int start = 1;

        return aladinService.searchBooks(s, maxResults, start);
    }
}
