package microservices.book.multiplication_app.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class MultiplicationAttemptResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="ATTEMPT_ID")
    private Long id;

    @JoinColumn(name="")
    @ManyToOne(cascade=CascadeType.PERSIST)
    private final Multiplication multiplication;
    
    private final AppUser appUser;
    private final int result;

    public MultiplicationAttemptResult(Multiplication multiplication, AppUser appUser, int resultAttempt) {
        this.multiplication = multiplication;
        this.appUser = appUser;
        this.result = resultAttempt;
    }
}
