import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Clean {
    public static void main(String[] args) throws Exception {
        Job job = new Job();
        job.setJarByClass(Clean.class);
        job.setJobName("Clean Job");
            job.setNumReduceTasks(1);
        job.setMapperClass(CleanMapper.class);
        job.setCombinerClass(CleanReducer.class);
        job.setReducerClass(CleanReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
