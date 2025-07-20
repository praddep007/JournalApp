//package net.engineeringdigest.journalApp.service;
//
//import net.engineeringdigest.journalApp.entity.User;
//import net.engineeringdigest.journalApp.repository.UserRepository;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class UserServiceTests {
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    UserService userService;
//
//    @Disabled
//    @Test
//    public void testAdd(){
//       assertEquals(6,3+3);
//       //assertTrue(5  > 7);
//    }
//
//
//    @Disabled
//    @ParameterizedTest
//    @ValueSource(strings = {
//            "Anil",
//            "Pradeep",
//            "Sunil"
//    })
//    public void testFindByUserName(String userName){
//        assertNotNull(userRepository.findByUserName(userName));
//    }
//
//
//    @Disabled
//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentProvider.class)
//    public void testSaveNewUser(User userName){
//        //assertNotNull(userRepository.findByUserName(userName));
//        //assertTrue(userService.saveNewUser(userName));
//    }
//
//
//    @Disabled
//    @ParameterizedTest
//    @CsvSource({
//            "6,2,3",
//            "4,2,2",
//            "12,6,7"
//    })
//    public void test(int excepted, int a, int b){
//        assertEquals(excepted,a+b);
//    }
//}
