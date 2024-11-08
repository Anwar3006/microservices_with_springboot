package microservices_book.gamification_service.client;


import microservices_book.gamification_service.client.dto.MultiplicationAttempt;

public interface MultiplicationClientService {

    /**
     * Gets a {@link MultiplicationAttempt} from our Multiplcation Microservice using RESTful paradigm
     * 
     * @param attemptId
     * @return {@link MulticationAttempt}
     */
    MultiplicationAttempt getAttemptById(final Long attemptId);
}
