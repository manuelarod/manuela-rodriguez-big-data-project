import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WeeklyTripsCountMapper extends Mapper<Text, Text, Text, IntWritable> {
    @Override
    public void map(Text key, Text value, Context context)
        throws IOException, InterruptedException {
        String line = key.toString();
        String[] values = line.split(",");
        if (values[0].length() >= 10 && !values[0].contains("start")) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Integer.valueOf(values[0].substring(0,4)), Integer.valueOf(values[0].substring(5,7)), Integer.valueOf(values[0].substring(8,10)));
	    int week = calendar.get(Calendar.WEEK_OF_YEAR);
	    String dt = values[0].substring(0,7) + " " + Integer.toString(week);
            context.write(new Text(dt), new IntWritable(1));
        }
    }
}
