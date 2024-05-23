package cinema.utils;

import cinema.dto.LoginDto;
import cinema.entity.Role;
import cinema.exception.AppExceptionDto;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class JWTTokenUtils {
   private static final long EXPIRATION_TIME = 864000000; // 10 days, thời hạn của token
    private static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
   private static final String PREFIX_TOKEN = "Bearer"; // Ký tự đầu của token
   private static final String AUTHORIZATION = "Authorization"; // Key của token trên header

   // Hàm này dùng để tạo ra token
   public String createAccessToken(LoginDto loginDto) {
       // Tạo giá trị thời hạn token ( bằng thời gian hiện tại + 10 ngày hoặc tuỳ theo )
       Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
       String token = Jwts.builder()
               .setId(String.valueOf(loginDto.getAccountId())) //set giá trị Id
               .setSubject(loginDto.getPhoneNumber()) // set giá trị subject
               .setIssuedAt(new Date())
               .setIssuer("VTI")
               .setExpiration(expirationDate) // set thời hạn của token
               .signWith(SignatureAlgorithm.HS512, key) // khai báo phương thức mã hóa token và chữ ký bí mật
               .claim("authorities", loginDto.getRole().name()) // thêm trường authorities để lưu giá trị phân quyền
               .claim("user-Agent", loginDto.getUserAgent()) // thêm trường user-Agent để lưu thông tin trình duyệt đang dùng
               .compact(); // Tạo ra token từ các thông tin trên

       return token;
   }


   // Hàm này dùng để giải mã hóa token
   public LoginDto parseAccessToken(String token) {
       LoginDto loginDto = new LoginDto();
       if (!token.isEmpty()) {
           try {
               token = token.replace(PREFIX_TOKEN, "").trim();
               Claims claims = Jwts.parser()
                       .setSigningKey(key).build()
                       .parseClaimsJws(token).getBody();
               // Lấy ra các thông tin
               String user = claims.getSubject();
               Role role = Role.valueOf(claims.get("authorities").toString());
               String userAgent = claims.get("user-Agent").toString();
               // Gán các thông tin vào đối tượng LoginDto, có thể sử dụng constructor
               loginDto.setPhoneNumber(user);
               loginDto.setRole(role);
               loginDto.setUserAgent(userAgent);
           } catch (Exception e) {
               log.error(e.getMessage());
               return null;
           }
       }
       return loginDto;
   }


   public boolean checkToken(String token, HttpServletResponse response, HttpServletRequest httpServletRequest) {
       try {
           if (StringUtils.isBlank(token) || !token.startsWith(PREFIX_TOKEN)) { // token bị trống -> lỗi
               responseJson(response, new AppExceptionDto("Token ko hợp lệ", 401, httpServletRequest.getRequestURI()));
               return false;
           }
           // Bỏ từ khóa Bearer ở token và trim() 2 đầu
           token = token.replace(PREFIX_TOKEN, "").trim();


           LoginDto loginDto = parseAccessToken(token);
           if (loginDto == null) { // Ko có token trên hệ thống
               responseJson(response, new AppExceptionDto("Token ko tồn tại hoặc hết hạn",401, httpServletRequest.getRequestURI()));
               return false;
           }
       } catch (Exception e) {
           responseJson(response, new AppExceptionDto(e.getMessage(),401, httpServletRequest.getRequestURI()));
           return false;
       }
       return true;
   }




   // Hàm này dùng để response dữ liệu khi gặp lỗi
   private void responseJson(HttpServletResponse response, AppExceptionDto appException){
       response.setCharacterEncoding("UTF-8");
       response.setContentType("application/json");
       response.setStatus(401);
       try {
           response.getWriter().print(JSON.toJSONString(appException));
       } catch (IOException e) {
           log.debug(e.getMessage());
           throw new RuntimeException(e);
       }
   }
}


