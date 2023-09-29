package kokosgroup.move4good.talkingflats.models;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "challenge_table")
public class Challenge {
    @Id
    @Column(name = "challenge_id", nullable = false, unique = true)
    private int id;
    @Column(name = "challenge_goal", nullable = false)
    private String goal;
    @Column(name = "challenge_start_date", nullable = false)
    private Date startDate;
    @Column(name = "challenge_end_date", nullable = false)
    private Date endDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_table")
    private User companyOwner;
    @Column(name = "challenge_src", nullable = false)
    private String src;

    
    public Challenge() {
    }

    public Challenge(int id, String goal, Date start_date, Date end_date, User company_owner, String src) {
        this.id = id;
        this.goal = goal;
        this.startDate = start_date;
        this.endDate = end_date;
        this.companyOwner = company_owner;
        this.src = src;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public User getCompanyOwner() {
        return companyOwner;
    }

    public void setCompanyOwner(User companyOwner) {
        this.companyOwner = companyOwner;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
   
    
    
}
