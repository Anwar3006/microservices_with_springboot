package microservices.book.multiplication_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author anwa
 */

@Data
@Entity
@NoArgsConstructor
public class Multiplication {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MULTIPLICATION_ID")
    private Long Id;

    private int factorA;
    private int factorB;

    
    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
    }

    public int getResult(){
        return this.factorA * this.factorB;
    }
}
