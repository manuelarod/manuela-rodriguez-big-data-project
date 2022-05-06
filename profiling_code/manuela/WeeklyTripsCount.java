import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;

public class WeeklyTripsCount {
    public static void main(String[] args) throws Exception {
        Job job = new Job();
        job.setJarByClass(WeeklyTripsCount.class);
        job.setJobName("Weekly Trips Count");
        job.setNumReduceTasks(1);
        job.setMapperClass(WeeklyTripsCountMapper.class);
        job.setCombinerClass(WeeklyTripsCountReducer.class);
        job.setReducerClass(WeeklyTripsCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
	job.setInputFormatClass(KeyValueTextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
