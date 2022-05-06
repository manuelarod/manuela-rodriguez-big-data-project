import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountRecsMapper extends Mapper<Text, Text, Text, IntWritable> {
    @Override
    public void map(Text key, Text value, Context context)
        throws IOException, InterruptedException {
        String line = key.toString();
        if (line.length() > 0) {
            context.write(new Text("Records"), new IntWritable(1));
        }
    }
}
