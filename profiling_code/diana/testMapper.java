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

public class testMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
             private Text word = new Text();
	     private IntWritable one=new IntWritable(1);
             public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
               String line = value.toString();
               StringTokenizer tokenizer = new StringTokenizer(line,"\n");
               while (tokenizer.hasMoreTokens()) {
		 String comma=",";
                 String eachLine=tokenizer.nextToken();
		 String[] sep_lines=eachLine.split(",");
		 word.set(sep_lines[1]+comma+sep_lines[4]+comma+sep_lines[6]);
                 context.write(word,null);
                }
              }
 }
