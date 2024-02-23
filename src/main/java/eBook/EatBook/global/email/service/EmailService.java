package eBook.EatBook.global.email.service;

import eBook.EatBook.domain.member.entity.Member;
import eBook.EatBook.global.email.entity.Email1;
import eBook.EatBook.global.email.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final EmailRepository emailRepository;

    public void send(String to, String subject, String body) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            mimeMessageHelper.setTo(to); // 메일 수신자
            mimeMessageHelper.setSubject(subject); // 메일 제목
            mimeMessageHelper.setText(body, true); // 메일 본문 내용, HTML 여부
            mailSender.send(mimeMessage); // 메일방송
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
    public void saveConfirmCode(Member member, String confirmCode){
        Email1 email1 = Email1.builder()
                .toMember(member)
                .confirmCode(confirmCode)
                .createDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .build();

        this.emailRepository.save(email1);
    }


    public Email1 findConfirmCode(String confirmCode){
        Optional<Email1> optionalEmail = this.emailRepository.findByConfirmCode(confirmCode);
        if(optionalEmail.isEmpty()){
            return null;
        }

        return optionalEmail.get();
    }

    public void delete(Email1 email1) {
        this.emailRepository.delete(email1);
    }
}