import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanMapper extends Mapper<LongWritable, Text, Text, Text> {
    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {
        String line = value.toString();
        String[] values = line.split(",");
	String val = "";
        if (values.length == 13) {
	    val = String.join(",", Arrays.copyOfRange(values,2,5))+ "," + String.join(",",Arrays.copyOfRange(values,8,10)) + "," + values[6] + "," + String.join(",",Arrays.copyOfRange(values,10,13));
        } else if (values.length == 15) {
	    val = values[1].substring(1, values[1].length() - 1) + "," + values[2].substring(1, values[2].length() - 1) + "," + String.join(",",Arrays.copyOfRange(values,4,7))+ "," + String.join(",",Arrays.copyOfRange(values,8,11)) + "," + values[12];
	}
	context.write(new Text(val),new Text(""));
    }
}
