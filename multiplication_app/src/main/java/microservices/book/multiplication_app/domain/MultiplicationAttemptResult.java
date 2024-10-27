package microservices.book.multiplication_app.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Entity
@NoArgsConstructor
public class MultiplicationAttemptResult {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTEMPT_ID")
    private Long id;

    @JoinColumn(name = "MULTIPLICATION_ID")
    @ManyToOne(cascade = CascadeType.PERSIST)
    @NonNull
    private Multiplication multiplication;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    @NonNull
    private AppUser appUser;

    @NonNull
    private Integer result;

    private boolean isCorrect;

    public MultiplicationAttemptResult(@NonNull Multiplication multiplication, @NonNull AppUser appUser,
            @NonNull Integer result) {
        this.multiplication = multiplication;
        this.appUser = appUser;
        this.result = result;
    }

    public Multiplication getMultiplication() {
        return multiplication;
    }

    public void setMultiplication(Multiplication multiplication) {
        this.multiplication = multiplication;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
}
