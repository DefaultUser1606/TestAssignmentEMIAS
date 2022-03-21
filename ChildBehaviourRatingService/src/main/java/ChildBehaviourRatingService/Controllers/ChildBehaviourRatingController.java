package ChildBehaviourRatingService.Controllers;

import ChildBehaviourRatingService.WebModels.ChildBehaviourRatingResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Random;

@RestController
@Validated
public class ChildBehaviourRatingController {
    private final Random Rng;

    ChildBehaviourRatingController() {
        Rng = new Random(System.currentTimeMillis());
    }

    @GetMapping("/childBehaviourRating/{childId}")
    ResponseEntity<ChildBehaviourRatingResponse> GetChildBehaviourRating(
            @PathVariable("childId") @Min(1) final long childId) {
        boolean result = Rng.nextBoolean();
        return ResponseEntity.status(HttpStatus.OK).body(new ChildBehaviourRatingResponse(null, result));
    }
}
