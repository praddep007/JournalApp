//package net.engineeringdigest.journalApp.service;
//
//import net.engineeringdigest.journalApp.entity.User;
//import net.engineeringdigest.journalApp.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//
//import  static org.mockito.Mockito.*;
//
//
//public class UserDetailsSServiceImplTest {
//
//    @InjectMocks
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setUp(){
//        MockitoAnnotations.openMocks(this);
//        //MockitoAnnotations.initMocks(this);
//    }
//
//    @Disabled
//    @Test
//     public void loadUserByUsernameTest(){
//       when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Anil").password("dfghj").roles(new ArrayList<>()).build());
//       UserDetails user = userDetailsService.loadUserByUsername("Anil");
//   }
//}
