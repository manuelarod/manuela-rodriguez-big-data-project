import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;

public class CountRecs {
    public static void main(String[] args) throws Exception {
        Job job = new Job();
        job.setJarByClass(CountRecs.class);
        job.setJobName("Record Count");
	job.setNumReduceTasks(1);
        job.setMapperClass(CountRecsMapper.class);
        job.setCombinerClass(CountRecsReducer.class);
        job.setReducerClass(CountRecsReducer.class);
        job.setInputFormatClass(KeyValueTextInputFormat.class);
	job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
