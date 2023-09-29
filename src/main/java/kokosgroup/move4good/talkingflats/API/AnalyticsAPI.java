package kokosgroup.move4good.talkingflats.API;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kokosgroup.move4good.talkingflats.controllers.ChallengeRepository;
import kokosgroup.move4good.talkingflats.controllers.UserRepository;
import kokosgroup.move4good.talkingflats.models.User;
import kokosgroup.move4good.talkingflats.utils.JwtUtil;


@RestController
@RequestMapping("/api/v1/analytics")
public class AnalyticsAPI {
    
    @Autowired
    ChallengeRepository challengeRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/challenges")
    public Response getAllChallenges(@HeaderParam("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        else {
            User user = userRepository.findByLogin(JwtUtil.getUsername(authHeader.substring(7)));
            if(user != null) {
                return (Response) challengeRepository.findAllByCompanyOwner(user);
            }

        }
        return null;
        
    }
}
