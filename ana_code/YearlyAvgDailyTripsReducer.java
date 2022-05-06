import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class YearlyAvgDailyTripsReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable>{
    @Override
    public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double count = 0;
        for (DoubleWritable value : values) {
            count += value.get();
        }
        double avg = count / 365.0;
        context.write(key, new DoubleWritable(avg));
    }
}
