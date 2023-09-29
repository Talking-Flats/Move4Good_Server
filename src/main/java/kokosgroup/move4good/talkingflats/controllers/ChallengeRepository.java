package kokosgroup.move4good.talkingflats.controllers;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kokosgroup.move4good.talkingflats.models.Challenge;
import kokosgroup.move4good.talkingflats.models.User;

public interface ChallengeRepository extends CrudRepository<Challenge,Integer>{
    public List<Challenge> findAllByCompanyOwner(User companyOwner);
}
