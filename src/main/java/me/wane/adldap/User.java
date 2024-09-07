package me.wane.adldap;

import lombok.*;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entry(objectClasses = {
        "organizationalPerson", "user",
        "person", "top"
}
)
public class User {

    @Id
    @Setter
    private Name id;

    private String displayName;  // 표시 이름
    private String userPrincipalName;  // 사용자 주체 이름
    private String userAccountControl;  // 사용자 계정 제어 (계정 상태)
    private String sAMAccountName;  // Security Account Manager 계정 이름
    private String mail;  // 이메일 주소
}
