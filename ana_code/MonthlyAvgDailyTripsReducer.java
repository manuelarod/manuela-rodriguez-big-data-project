import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MonthlyAvgDailyTripsReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable>{
    @Override
    public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double val = 0;
        for (DoubleWritable value : values) {
            val += value.get();
        }
        double avg = val / 30.0;
        context.write(key, new DoubleWritable(avg));
    }
}
