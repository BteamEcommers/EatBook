package eBook.EatBook.domain.service_center.Service;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.domain.service_center.DTO.FaqCreateForm;
import eBook.EatBook.domain.service_center.Entity.Faq;
import eBook.EatBook.domain.service_center.Repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    public void create(FaqCreateForm faqCreateForm, Member member) {
        Faq faq = Faq.builder()
                .member(member)
                .title(faqCreateForm.getTitle())
                .content(faqCreateForm.getContent())
                .build();
        this.faqRepository.save(faq);
    }

    public List<Faq> findAllFaq(){
        return this.faqRepository.findAll();
    }
    public Faq getFaq(Integer FaqId) {
        Optional<Faq> faq = this.faqRepository.findById(FaqId);
        if (faq.isEmpty()) {
            return null;
        }
        return faq.get();
    }
    public void modify(Faq faq,Member member ,String title, String content) {
        Faq modifyFaq = faq.toBuilder()
                .member(member)
                .title(title)
                .content(content)
                .build();

        this.faqRepository.save(modifyFaq);
    }

    public void delete(Faq faq) {
        this.faqRepository.delete(faq);
    }

}
