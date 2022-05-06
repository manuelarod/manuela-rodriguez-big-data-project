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

public class addTripTimeMapper extends Mapper<LongWritable,Text,Text,Text> {
             private Text word = new Text();
             String comma=",";
	     int duration=0;
             public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
               String[] sep_lines=value.toString().split(",");
	       String line = value.toString();
	       try{
	       		
	       
	       		String[] pickupDayTime =sep_lines[0].split(" ");
	       		String[] pickupTime = pickupDayTime[1].split(":");
	       		String [] dropoffDayTime = sep_lines[1].split(" ");
	       		String [] dropoffTime = dropoffDayTime[1].split(":");
	       		int minsPickUp= Integer.parseInt(pickupTime[0])*60 + Integer.parseInt(pickupTime[1]);
	       		int minsDropOff = Integer.parseInt(dropoffTime[0])*60 + Integer.parseInt(dropoffTime[1]);
	       		if (minsDropOff<minsPickUp){
	       			duration=24*60 + Integer.parseInt(dropoffTime[1])-minsPickUp; 
	       		}
	       		else{
	       			duration = minsDropOff-minsPickUp;
	       		}
			word.set(line+comma+duration);
	       }
	       catch (ArrayIndexOutOfBoundsException e){
	       		word.set(line+comma+null);	
	       }
               context.write(word,null);


            }
 }
