package microservices_book.social_multiplication.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MultiplicationAttempt {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ATTEMPT_ID")
    private Long id;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="MULTIPLICATION_ID")
    private Multiplication multiplication;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="USER_ID")
    private AppUser user;

    private Integer result;

    public MultiplicationAttempt(Multiplication multiplication, AppUser user, Integer result) {
        this.multiplication = multiplication;
        this.user = user;
        this.result = result;
    }
}
