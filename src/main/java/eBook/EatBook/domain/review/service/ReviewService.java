package eBook.EatBook.domain.review.service;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.review.DTO.ReviewForm;
import eBook.EatBook.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public void create(ReviewForm reviewForm, Member author) {
    }
}
