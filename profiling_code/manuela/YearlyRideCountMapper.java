import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class YearlyRideCountMapper extends Mapper<Text, Text, Text, IntWritable> {
    @Override
    public void map(Text key, Text value, Context context)
        throws IOException, InterruptedException {
        String line = key.toString();
        String[] values = line.split(",");
        if (values[0].length() >= 4 && !values[0].contains("start")) {
            context.write(new Text(values[0].substring(0,4)), new IntWritable(1));
        }
    }
}
