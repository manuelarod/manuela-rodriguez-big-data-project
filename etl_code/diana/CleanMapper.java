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

public class CleanMapper extends Mapper<LongWritable,Text,Text,Text> {
             private Text word = new Text();
	     String comma=",";
             public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
               String line = value.toString();
               StringTokenizer tokenizer = new StringTokenizer(line,"\n");
	       String eachLine=tokenizer.nextToken();
               String[] sep_lines=value.toString().split(",");
               word.set(sep_lines[1]+comma+sep_lines[2]+comma+sep_lines[4]+comma+sep_lines[5]+comma+sep_lines[6]+comma+sep_lines[9]+comma+sep_lines[10]+comma+sep_lines[12]+comma+sep_lines[18]);
               context.write(word,null);
	
                
              }
 }
