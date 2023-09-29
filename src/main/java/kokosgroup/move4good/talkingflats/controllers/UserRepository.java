package kokosgroup.move4good.talkingflats.controllers;

import org.springframework.data.repository.CrudRepository;

import kokosgroup.move4good.talkingflats.models.User;
    public interface UserRepository extends CrudRepository<User,Integer> {

    public User findByLogin(String login);
}

