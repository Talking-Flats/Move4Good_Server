package kokosgroup.move4good.talkingflats.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_in_challenge_table")
public class UserInChallenge {
    @Id
    @Column(name = "user_in_challenge_id", nullable = false, unique = true)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "challenge_table")
    private Challenge challenge;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_table")
    private User user;
    @Column(name = "temp_result_steps")
    private int tempResultStep;
    @Column(name = "temp_result_speed")
    private int tempResultSpeed;
    @Column(name = "temp_result_time")
    private int tempResultTime;
    @Column(name = "temp_result_distance")
    private int tempResultDistance;
    @Column(name = "challenge_done")
    private Boolean done;
    @Column(name = "challenge_src")
    private String src;


    
    public UserInChallenge() {
    }
    public UserInChallenge(int id, Challenge challenge, User user, int tempResultStep, int tempResultSpeed,
            int tempResultTime, int tempResultDistance, Boolean done, String src) {
        this.id = id;
        this.challenge = challenge;
        this.user = user;
        this.tempResultStep = tempResultStep;
        this.tempResultSpeed = tempResultSpeed;
        this.tempResultTime = tempResultTime;
        this.tempResultDistance = tempResultDistance;
        this.done = done;
        this.src = src;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Challenge getChallenge() {
        return challenge;
    }
    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public int getTempResultStep() {
        return tempResultStep;
    }
    public void setTempResultStep(int tempResultStep) {
        this.tempResultStep = tempResultStep;
    }
    public int getTempResultSpeed() {
        return tempResultSpeed;
    }
    public void setTempResultSpeed(int tempResultSpeed) {
        this.tempResultSpeed = tempResultSpeed;
    }
    public int getTempResultTime() {
        return tempResultTime;
    }
    public void setTempResultTime(int tempResultTime) {
        this.tempResultTime = tempResultTime;
    }
    public int getTempResultDistance() {
        return tempResultDistance;
    }
    public void setTempResultDistance(int tempResultDistance) {
        this.tempResultDistance = tempResultDistance;
    }
    public Boolean getDone() {
        return done;
    }
    public void setDone(Boolean done) {
        this.done = done;
    }
    public String getSrc() {
        return src;
    }
    public void setSrc(String src) {
        this.src = src;
    }
    
}
    
