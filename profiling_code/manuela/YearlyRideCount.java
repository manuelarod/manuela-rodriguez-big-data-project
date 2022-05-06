import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;

public class YearlyRideCount {
    public static void main(String[] args) throws Exception {
        Job job = new Job();
        job.setJarByClass(YearlyRideCount.class);
        job.setJobName("Yearly Ride Count");
        job.setNumReduceTasks(1);
        job.setMapperClass(YearlyRideCountMapper.class);
        job.setCombinerClass(YearlyRideCountReducer.class);
        job.setReducerClass(YearlyRideCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
	job.setInputFormatClass(KeyValueTextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
