package kokosgroup.move4good.talkingflats.utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import redis.clients.jedis.Jedis;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION_TIME = 86400000; // 24 hours
    private static final String REDIS_KEY_PREFIX = "jwt:";

    public static String createToken(String username, String role) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(null, key)
                .compact();

        // Store the token in Redis
        Jedis jedis = new Jedis("localhost");
        jedis.setex(REDIS_KEY_PREFIX + token, (int) (EXPIRATION_TIME / 1000), username);
        jedis.close();

        return token;
    }

    public static boolean validateToken(String token, String role) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token);
            Jedis jedis = new Jedis("localhost");
            String username = jedis.get(REDIS_KEY_PREFIX + token);
            jedis.close();
            return username != null && role.equals(getRole(username));
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUsername(String token) {
        Jedis jedis = new Jedis("localhost");
        String username = jedis.get(REDIS_KEY_PREFIX + token);
        jedis.close();
        return username;
    }


    private static String getRole(String username) {
        // TODO: Implement logic to retrieve the user's role from your database
        return "user";
    }
}