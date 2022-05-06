import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.util.*;


public class CountRecsReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        private IntWritable result= new IntWritable();
        public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{
        int sum=0;
        for (IntWritable val : values) {
                sum+=val.get();
        }
        result.set(sum);
        context.write(key, result);
        }
}

