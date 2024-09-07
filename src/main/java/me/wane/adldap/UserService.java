package me.wane.adldap;

import lombok.RequiredArgsConstructor;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final LdapTemplate ldapTemplate;

    public void saveUser() {
        Name dn = LdapNameBuilder.newInstance("CN=Users,DC=ad,DC=wan2daaa,DC=com") //Name 내에는 Base DN //사용자 DN관련된 정보만 꼭 추가해야됨, userPrincipalName 같은걸 넣으면 에러남
                .add("cn", "test10")
                .build();

        User createUser = User.builder()
                .id(dn)
                .displayName("test10")//unique
                .userPrincipalName("test10@gmail.com")  // 사용자 CN(Common Name)을 추가 //unique
                .userAccountControl("544")
                .sAMAccountName("test10")//unique
                .mail("test10@gmail.com")  // 사용자 CN(Common Name)을 추가 //unique
                .build();

        // 사용자 정보를 AD에 저장
        ldapTemplate.create(createUser);

        //javax.naming.NameAlreadyBoundException: [LDAP: error code 68 - 00000524: UpdErr: DSID-031A11FA, problem 6005 (ENTRY_EXISTS), data 0
        //중복된 값 입력시 에러 발생
    }

}
