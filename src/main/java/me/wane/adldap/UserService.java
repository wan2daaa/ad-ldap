package me.wane.adldap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.wane.adldap.dto.CreateUserRequest;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.naming.ldap.LdapName;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final LdapTemplate ldapTemplate;

    private final PasswordEncoder passwordEncoder;

    public void saveUser(CreateUserRequest request) {
//        Name domainName = LdapNameBuilder.newInstance() //Name 내에는 Base DN //사용자 DN관련된 정보만 꼭 추가해야됨, userPrincipalName 같은걸 넣으면 에러남
//                .add("cn", request.cn())
//                .build();

        LdapName domainName = LdapUtils.newLdapName("cn=" + request.cn()); //위와 같은 결과
        User createUser = User.create(domainName, request, passwordEncoder);
        log.info("createUser: {}", createUser);
//         사용자 정보를 AD에 저장
        ldapTemplate.create(createUser);

//        userRepository.save(user); //error 32 - No Such Object - Directory not found <- 이유를 찾는중
        //javax.naming.NameAlreadyBoundException: [LDAP: error code 68 - 00000524: UpdErr: DSID-031A11FA, problem 6005 (ENTRY_EXISTS), data 0
        //중복된 값 입력시 에러 발생
    }

    public Boolean isPasswordMatch(String cn, String password) {
        User user = findUserByCN(cn);

        return passwordEncoder.matches(password, user.getUserPassword());
    }

    public User findUserByCN(String cn) {

        Name dn = LdapNameBuilder.newInstance()
                .add("cn", cn)
                .build();

        User user = userRepository.findById(dn)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        log.info("user: {}", user);
        return user;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }


}
