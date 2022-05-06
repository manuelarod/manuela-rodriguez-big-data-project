import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class YearlyAvgDailyTripsMapper extends Mapper<Text, Text, Text, DoubleWritable> {
    @Override
    public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String line = key.toString();
        if (line.length() >= 4) {
	    double count = new Double(Integer.valueOf(value.toString()));
            context.write(new Text(line.substring(0,4)), new DoubleWritable(count));
        }
    }
}
