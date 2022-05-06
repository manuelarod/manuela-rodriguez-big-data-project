import java.io.IOException;
import java.util.Arrays;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DurationMapper extends Mapper<Text, Text, Text, Text> {
    @Override
    public void map(Text key, Text value, Context context)
        throws IOException, InterruptedException {
        String line = key.toString();
        String[] values = line.split(",");
        if (values[0].length() >= 7 && !values[0].contains("start")) {
	    Timestamp start_ts = Timestamp.valueOf(values[0]);
	    Timestamp end_ts = Timestamp.valueOf(values[1]);
	    long ms = end_ts.getTime() - start_ts.getTime();
	    double s = ms / 1000.0;
	    String new_line = String.join(",",values) + "," + String.valueOf(s);
            context.write(new Text(new_line), new Text(""));
        }
    }
}
