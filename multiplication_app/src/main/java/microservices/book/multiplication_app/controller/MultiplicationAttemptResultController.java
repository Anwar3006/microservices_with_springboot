package microservices.book.multiplication_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.book.multiplication_app.domain.MultiplicationAttemptResult;
import microservices.book.multiplication_app.service.MultiplicationService;


@RestController
@RequestMapping("/results")
public class MultiplicationAttemptResultController {
	
	@Autowired
	private MultiplicationService multiplicationService;

	@PostMapping
	public ResponseEntity<Boolean> postAttemptAndGetResult(@RequestBody final MultiplicationAttemptResult attempt) {
		//create copy of attempt
		MultiplicationAttemptResult copyAttempt = new MultiplicationAttemptResult(attempt.getMultiplication(), 
																				attempt.getAppUser(), 
																				attempt.getResult());
		
		return ResponseEntity.ok(multiplicationService.checkAttempt(copyAttempt));
	}
}
