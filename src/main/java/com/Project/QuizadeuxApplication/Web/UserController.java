//package com.Project.QuizadeuxApplication.Web;
//
//import com.Quizpourdeux.Quizpourdeux.Security.JWTHelper;
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTVerifier;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//import static com.Quizpourdeux.Quizpourdeux.Security.JWTUtil.AUTH_HEADER;
//import static com.Quizpourdeux.Quizpourdeux.Security.JWTUtil.SECRET;
//
//@RestController
//@RequestMapping("")
//@CrossOrigin("*")
//public class UserController {
//    private JWTHelper jwtHelper;
//
//    @GetMapping("/api/user")
//    public void generateNewAccessToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String jwtRefreshToken = jwtHelper.extractTokenFromHeaderifExist(request.getHeader(AUTH_HEADER)) ;
//        if(jwtRefreshToken!=null){
//            Algorithm algorithm= Algorithm.HMAC256(SECRET);
//            JWTVerifier jwtVerifier= JWT.require(algorithm).build();
//            DecodedJWT decodedJWT=jwtVerifier.verify(jwtRefreshToken);
//            String email =decodedJWT.getSubject();
////            UserDetails user= userDetailService.loadUserByUsername(email);
//            String jwtAccessToken=  jwtHelper.Create(email, null);
//            response.setContentType("application/json");
//        }else {
//            throw new RuntimeException("Refresh Token required");
//        }
//    }
//}
