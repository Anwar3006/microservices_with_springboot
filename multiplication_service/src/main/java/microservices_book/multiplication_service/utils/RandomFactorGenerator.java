package microservices_book.multiplication_service.utils;

public interface RandomFactorGenerator {
    
    /**
     * Generates a random factor between 11 and 99 inclusive.
     * @return int
     */
    int generateRandomFactor();
}
