package challenge18.hotdeal.domain.user;

import challenge18.hotdeal.common.Enum.UserRole;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Value("${adminToken}")
    private static String ADMINTOKEN;


    // 회원가입
    public void signup(SignupReqeust reqeust) {
        UserRole role = UserRole.USER;

        // 중복 회원 체크
        Optional<User> user = checkUserExist(reqeust.getUserId());
        if(user.isPresent()){
            throw new DuplicateRequestException("중복된 회원이 이미 존재합니다.");
        }

        // 관리자 회원가입 체크
        if(reqeust.isAdmin()){
            if(!reqeust.getAdminToken().equals(ADMINTOKEN)){
                throw new IllegalArgumentException("ADMINTOKEN이 유효하지 않습니다.");
            }
            role = UserRole.ADMIN;
        }

        userRepository.save(new User(reqeust.getUserId(), reqeust.getPassword(), role));
    }

    // 로그인
    public void login(LoginRequest request) {
        // 회원정보 존재 유무 체크
        Optional<User> user = checkUserExist(request.getUserId());
        if(!user.isPresent()){
            throw new NullPointerException("입력하신 회원정보가 존재하지 않습니다.");
        }

        // 비밀번호 체크
        if(!user.get().getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    private Optional<User> checkUserExist(Long userId){
        return userRepository.findByUserId(userId);
    }



}
