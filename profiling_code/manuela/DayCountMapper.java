import java.io.IOException;
import java.util.Arrays;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DayCountMapper extends Mapper<Text, Text, Text, IntWritable> {
    @Override
    public void map(Text key, Text value, Context context)
        throws IOException, InterruptedException {
        String line = key.toString();
        String[] values = line.split(",");
        if (values[0].length() >= 7 && !values[0].contains("start")) {
	    Timestamp ts = Timestamp.valueOf(values[0]);
	    String day = (new SimpleDateFormat("EEEE").format(ts.getTime()));
            context.write(new Text(day), new IntWritable(1));
        }
    }
}
