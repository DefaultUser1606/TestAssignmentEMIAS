package ChildBehaviourRatingService.WebModels;

public class ChildBehaviourRatingResponse {
    public final String Message;
    public final boolean IsGood;

    public ChildBehaviourRatingResponse(final String message, final boolean isGood) {
        Message = message;
        IsGood = isGood;
    }
}
