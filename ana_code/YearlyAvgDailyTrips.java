import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;

public class YearlyAvgDailyTrips {
    public static void main(String[] args) throws Exception {
        Job job = new Job();
        job.setJarByClass(YearlyAvgDailyTrips.class);
        job.setJobName("Yearly Avg Daily Trips");
        job.setNumReduceTasks(1);
        job.setMapperClass(YearlyAvgDailyTripsMapper.class);
        job.setCombinerClass(YearlyAvgDailyTripsReducer.class);
        job.setReducerClass(YearlyAvgDailyTripsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);
	job.setInputFormatClass(KeyValueTextInputFormat.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
