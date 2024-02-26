package eBook.EatBook.domain.member.role;

import lombok.Getter;


@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_MEMBER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
