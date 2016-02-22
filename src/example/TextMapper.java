package example;
import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class TextMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text>{

      int  lineno= 0;
      //map method that performs framing the initial key value pairs
      public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException
      {
    	    // get filename to send it as key for sorting.
    	    FileSplit fileSplit = (FileSplit)reporter.getInputSplit();
            String k = fileSplit.getPath().getName();

            //taking one line at a time 
            String line = value.toString();
            // get the position of the meaningful character
            int pos = lineno % 40;
            // get the character from the string
            String character = Character.toString(line.charAt(pos));
            // convert pipe symbol to next line 
            if(character.charAt(0) == '|')
            	character = "\n";
            // sending to output collector which in turn passes the same to reducer
            output.collect(new Text(k),  new Text(character));      
            lineno++;
       }
}