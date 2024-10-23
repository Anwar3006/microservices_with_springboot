package microservices.book.multiplication_app.domain;

import lombok.Data;

/**
 *
 * @author anwa
 */

@Data
public class Multiplication {

    private final int factorA;
    private final int factorB;


    
    public Multiplication(int factorA, int factorB) {
        this.factorA = factorA;
        this.factorB = factorB;
    }



    public int getResult(){
        return this.factorA * this.factorB;
    }
}
