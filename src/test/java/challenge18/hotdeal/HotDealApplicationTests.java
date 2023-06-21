//package challenge18.hotdeal;
//
//import challenge18.hotdeal.common.Enum.UserRole;
//import challenge18.hotdeal.domain.limited.dto.LimitedProductRequestDto;
//import challenge18.hotdeal.domain.limited.entity.LimitedProduct;
//import challenge18.hotdeal.domain.limited.repository.LimitedProductRepository;
//import challenge18.hotdeal.domain.limited.service.LimitedProductService;
//import challenge18.hotdeal.domain.user.dto.SignupRequest;
//import challenge18.hotdeal.domain.user.entity.User;
//import challenge18.hotdeal.domain.user.repository.UserRepository;
//import challenge18.hotdeal.domain.user.service.UserService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.CountDownLatch;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//@SpringBootTest(properties = "spring.config.location = classpath:application-test.yml")
//public class HotDealApplicationTests {
//
//    @Autowired
//    LimitedProductRepository limitedProductRepository;
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    private LimitedProductService limitedProductService;
//    @Autowired
//    private UserService userService;
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    @DisplayName("한정판 상품 구매 동시성 문제 테스트")
//    void concurrencyAboutLimitedProductTests() throws InterruptedException {
//        // given
//        LimitedProduct limitedProduct = new LimitedProduct(
//                new LimitedProductRequestDto("에어조던1 시카고 OG", 280000, "신발", "스니커즈", 500)
//        );
//        limitedProductRepository.saveAndFlush(limitedProduct);
//
//        List<User> testUsers = new ArrayList<>();
//        for (int i = 1; i <= 500; i++) {
//            User user = new User("testUser" + i, "password", UserRole.ROLE_USER);
//            userRepository.saveAndFlush(user);
//            testUsers.add(user);
//        }
//
//        ExecutorService executorService = Executors.newFixedThreadPool(500);
//        CountDownLatch latch = new CountDownLatch(500);
//
//        // when
//        for (User user : testUsers) {
//            executorService.execute(() -> {
//                limitedProductService.buyLimitedProduct(1L, user);
//                latch.countDown();
//            });
//        }
//
//        latch.await();
//        // then
//        LimitedProduct product = limitedProductRepository.findById(1L).orElseThrow();
//        Assertions.assertEquals(0, product.getAmount());
//    }
//
//    @Test
//    @DisplayName("회원가입 동시성 테스트")
//    void signupConcurrencyTests() throws InterruptedException {
//        // given
//        ExecutorService executorService = Executors.newFixedThreadPool(100);
//        CountDownLatch latch = new CountDownLatch(100);
//        List<Integer> users = new ArrayList<>();
//
//        // when
//        for (int i = 0; i < 100; i++) {
//            final int j = i;
//            SignupRequest requestDto = new SignupRequest("testId", "testPassword" + i, false, "");
//            executorService.execute(() -> {
//                try {
//                    userService.signup(requestDto);
//                    users.add(j);
//                } catch (Exception ignored) {
//                } finally {
//                    latch.countDown();
//                }
//            });
//        }
//
//        latch.await();
//
//        // then
//        Assertions.assertEquals(1, users.size());
//    }
//}
