package malakhov.study.java8.stream_collectors.exception;

public class ExerciseNotCompletedException extends RuntimeException {

    public ExerciseNotCompletedException() {
        super("Exercise not completed. Please provide implementation.");
    }

}
