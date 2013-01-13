package jp.projects.miya.mapreduce_example;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author 
 *
 */
public class TestMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	/**
	 *
	 */
	private static final Logger LOG = LoggerFactory.getLogger(TestMapper.class);

	/*
	 * (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Mapper#setup(org.apache.hadoop.mapreduce.Mapper.Context)
	 */
	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		TestMapper.LOG.info("setup");
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Mapper#map(KEYIN, VALUEIN, org.apache.hadoop.mapreduce.Mapper.Context)
	 */
	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String s = value.toString();
		for (String word : s.split("\\W+")) {
			if (word.length() > 0) {
				context.write(new Text(word), new IntWritable(1));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.hadoop.mapreduce.Mapper#cleanup(org.apache.hadoop.mapreduce.Mapper.Context)
	 */
	@Override
	protected void cleanup(Context context) throws IOException,
			InterruptedException {
		TestMapper.LOG.info("cleanup");
	}
}
