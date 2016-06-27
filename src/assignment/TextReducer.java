package assignment;
import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;


public class TextReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text> {
	//reduce method accepts the Key Value pairs from mappers, do the string concatenation based on keys and produce the final output
    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException
    {
        
    	String str = "";
        /*iterates through all the values available with a key and string concatenation of its values*/
        while (values.hasNext())
        {
            str = str + values.next().toString();
        }
        
        // reverse the string.
        StringBuilder dest = new StringBuilder(str.length());
        for (int i = (str.length() - 1); i >= 0; i--){
            dest.append(str.charAt(i));
        }
        // output the key value pairs, key is null here.
        output.collect(new Text(), new Text(dest.toString()));
    }
}


