package microservices.book.multiplication_app.domain;

import lombok.Data;

@Data
public class AppUser {
    
    private final String alias;

    public AppUser(String alias) {
        this.alias = alias;
    }
}
