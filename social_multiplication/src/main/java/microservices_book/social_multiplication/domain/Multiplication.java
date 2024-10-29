package microservices_book.social_multiplication.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Entity
@Getter
@Setter
public class Multiplication {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MULTIPLICATION_ID")
    private Long id;

    private Integer factorA;
    private Integer factorB;

    public Multiplication(Integer factorA, Integer factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
    }

    public int getResult(){
        return this.getFactorA() * this.getFactorB();
    }
}
