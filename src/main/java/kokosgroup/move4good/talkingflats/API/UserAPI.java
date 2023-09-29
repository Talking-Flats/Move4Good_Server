package kokosgroup.move4good.talkingflats.API;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kokosgroup.move4good.talkingflats.controllers.UserRepository;
import kokosgroup.move4good.talkingflats.utils.JwtUtil;

@RestController
@RequestMapping("/api/v1/users")
public class UserAPI {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/protected")
    public Response protectedEndpoint(@HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);
        if (!JwtUtil.validateToken(token, "admin")) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        // TODO: Implement logic for your protected endpoint

        return Response.ok().build();
    }

    @GetMapping("/test")
    public String test() {
        return "XD";
    }


    @PostMapping("/login?login={login}&password={password}")
    public Response login(String login, String password) {
        if (login == null || password == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        else {
            if(userRepository.findByLogin(login) != null && userRepository.findByLogin(login).getPassword().equals(password) && userRepository.findByLogin(login).getRole().equals("admin")) {
                String token = JwtUtil.createToken("admin", "admin");
                return Response.ok().header("Authorization", "Bearer " + token).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GetMapping("/me") 
    public Response UserMe(@HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7);
        if (!JwtUtil.validateToken(token, "admin")) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        if(JwtUtil.getUsername(token) != null) {
            return Response.ok().entity(userRepository.findByLogin(JwtUtil.getUsername(token))).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }


}

