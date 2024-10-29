package microservices_book.social_multiplication.utils;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomFactorGeneratorImpl implements RandomFactorGenerator{
    final int MINIMUM_FACTOR = 11;
    final int MAXIMUM_FACTOR = 99;

    @Override
    public int generateRandomFactor() {
        return new Random().nextInt((MAXIMUM_FACTOR - MINIMUM_FACTOR) + 1) + MINIMUM_FACTOR;
    }
}
