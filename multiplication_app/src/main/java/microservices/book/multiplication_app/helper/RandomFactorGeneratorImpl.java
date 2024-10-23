package microservices.book.multiplication_app.helper;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomFactorGeneratorImpl implements RandomFactorGenerator{

    private final int MIN_FACTOR = 11;
    private final int MAX_FACTOR = 99;

    @Override
    public int generateRandomFactor() {
        return new Random().nextInt((MAX_FACTOR - MIN_FACTOR) + 1) + MIN_FACTOR;
    }
    
}
