package malakhov.test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InterviewTestAssignment2 {
    public static void main(String[] args) {
        List<Data> dataList = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime time = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0);
        List<Data> fullDataList = new ArrayList<>();

        for (int i = 0, j = 0; i <= 24; i++) {
            LocalDateTime oneHoursLater = time.plusHours(1);
            Data data = dataList.get(j);

            if (data.from.isEqual(time) && data.to.isEqual(oneHoursLater)) {
                j++;
                fullDataList.add(data);
            } else {
                fullDataList.add(new Data(time, oneHoursLater, 0L));
            }
        }
    }

    public static class Data {

        public Data(LocalDateTime to, LocalDateTime from, Long capacity) {
            this.to = to;
            this.from = from;
            this.capacity = capacity;
        }

        Long capacity;
        LocalDateTime from;
        LocalDateTime to;
    }
}
