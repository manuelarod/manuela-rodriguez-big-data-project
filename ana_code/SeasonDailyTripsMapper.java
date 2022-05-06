import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SeasonDailyTripsMapper extends Mapper<Text, Text, Text, DoubleWritable> {
    HashMap<String, String> seasons  = new HashMap<String, String>() {{
    put("03", "spring");
    put("04", "spring");
    put("05", "spring");
    put("06", "summer");
    put("07", "summer");
    put("08", "summer");
    put("09", "fall");
    put("10", "fall");
    put("11", "fall");
    put("12", "winter");
    put("01", "winter");
    put("02", "winter");
    }};
    @Override
    public void map(Text key, Text value, Context context) throws IOException, InterruptedException {
        String line = key.toString();
        if (line.length() >= 7) {
	    double count = new Double(Integer.valueOf(value.toString()));
            context.write(new Text(seasons.get(line.substring(5,7))), new DoubleWritable(count));
        }
    }
}
