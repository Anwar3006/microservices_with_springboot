package microservices.book.multiplication_app.domain;

import lombok.Data;

@Data
public class MultiplicationAttemptResult {
    
    private final Multiplication multiplication;
    private final AppUser appUser;
    private final int result;

    public MultiplicationAttemptResult(Multiplication multiplication, AppUser appUser, int resultAttempt) {
        this.multiplication = multiplication;
        this.appUser = appUser;
        this.result = resultAttempt;
    }
}
