package eBook.EatBook.global.email.entity;

import eBook.EatBook.global.baseEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Email1 extends BaseEntity {
    // 보내는 사람 이메일
    @Column
    private String toEmail;

    @Column
    private String confirmCode;
}
