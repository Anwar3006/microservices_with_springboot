package microservices.book.multiplication_app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

/**
 *
 * @author anwa
 */


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



    public Long getId() {
        return Id;
    }



    public void setId(Long id) {
        Id = id;
    }



    public int getFactorA() {
        return factorA;
    }



    public void setFactorA(int factorA) {
        this.factorA = factorA;
    }



    public int getFactorB() {
        return factorB;
    }



    public void setFactorB(int factorB) {
        this.factorB = factorB;
    }
}
