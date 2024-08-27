package malakhov.kt_practice.p1_stream_collectors_practice.exception;

public class ExerciseNotCompletedException extends RuntimeException {

    public ExerciseNotCompletedException() {
        super("Exercise not completed. Please provide implementation.");
    }

}
