import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;

public class addTripTime{
        public static void main(String[] args) throws Exception {
                Configuration conf = new Configuration();
                Job job= Job.getInstance(conf, "addtriptime");
                job.setJarByClass(addTripTime.class);
                job.setMapperClass(addTripTimeMapper.class);
                job.setOutputKeyClass(Text.class);
                job.setOutputValueClass(IntWritable.class);
                FileInputFormat.addInputPath(job, new Path(args[0]));
                FileOutputFormat.setOutputPath(job, new Path(args[1]));
                job.setNumReduceTasks(0);
                System.exit(job.waitForCompletion(true) ? 0 : 1);
        }
}
