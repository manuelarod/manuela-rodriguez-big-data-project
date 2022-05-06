import org.apache.hadoop.mapreduce.Mapper.Context;
import java.io.IOException;
import java.util.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.*;
import java.util.StringTokenizer;

 public class CountRecsMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
             private final static IntWritable one = new IntWritable(1);
             private Text word = new Text();
             public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
               String line = value.toString();
               word.set("Total # of records");
               StringTokenizer tokenizer = new StringTokenizer(line,"\n");
               while (tokenizer.hasMoreTokens()) {
                 String eachLine=tokenizer.nextToken();
                 context.write(word,one);
                }
              }
 }
