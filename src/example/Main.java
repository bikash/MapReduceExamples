/*
 * Author: Bikash Agrawal
 * Date: 27 June 2016
 * Description: Wordcount example in MapReduce
 * 
 */
package example;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;



// Being able to reason about code and help make it better is a critical
// aspect of our work, and code reviewing is something we do almost every day.
// While we may not write much Java code ourselves, it's important to be able
// to understand it, since we make use of many Java libraries.

// This code is an attempt at implementing MapReduce, and has a lot of issues.
// Please help make it better! You don't have to find every single problem, but
// especially issues related to correctness are serious.

// Add your comments as Java comments above the section/line you are commenting.
// When you are done, please send back the entire file or a unified diff which
// adds your comments.

// Your comments should consider (in order of importance)
// - Correctness
// - Readability
// - Robustness
// - Usability
// - Extensibility
// - Maintainability
// - Performance

// Less important, but not unimportant are
// - Coding style
// - Idiomatic use of the language
// - Formatting

// Good luck!


/**
 * The Class Main.
 */
public class Main {
    
    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static final void main(String[] args) throws Exception {
        MapReduce mr = new MapReduce(args[0], args[1]);

        // TODO(bakksjo): Replace this, read from file (given by args[2]) instead.
        ArrayList<String> values = new ArrayList<String>();
        values.add("revailed, and all the chief Korah, and they had made our hand of you to his clothes. And God formed man his hand:) That be bereaved of his house, and said, Because there shall my servant my son mourning. And it not. And thou hast done unto the wilderness: and, behold, I sent me that thing that God took sheep lying words. And he begat Lamech lived after them, That my master made thee a new wine. Let there hath also the dust of Basemath, Esau's wife. These are dead out of Aram. And Abram was concubine to pass on");
        values.add("befall him. And Abimelech rose up a strong ass, and of peoples, and to be the ring, and upon the land, and they said unto me, and they sat upon his daughter-in-law. And Jehovah made a window, and the prostitute, that the he-goats which I buried in the least of Egypt, and his chariot, and Rebekah is the birds of Canaan unto his face: and, behold, Esau thy seed. And he said unto the rams of the commandment of Jacob took him in one was hardened, and Letushim, and saw the river seven ewe lambs of the servant my dead");
        values.add("thy father, and our daughters. And he said, Let us build us with my strength and Serug two years old man, dwelling was the field, and he said, I will give thee yesternight. And the name of them as thou bless thee unto thee. And nations of Israel, that place of water. And the reed-grass. And, behold, it was called his father. And the men are the Plain of Isaac, Go forth the people go up to pass, when he said unto them, Go therefore shall no plant them into the land of the dove found her; And the seventh");
        values.add("arose a Canaanitish woman. And God created and she said, My spirit of thy voice: and moreover I go through them; and called his sons' wives upon beast, and for Moses said unto Laban, and drink, and Aaron. And Jehovah appeared to his blood of the first is for bread, till thou shalt go from off bearing. And he had commanded: and thou mayest freely eat: but all the city, and Kenaz. And it was gathered together, and rest for it be much as Jehovah brought out of Abraham. And the Canaanites, among the ring, and Judah, saying, The sons");
        values.add("feeding the families of his garment in the ark went down by day unto him. And they heard that come, and the eldest, and let Pharaoh that the south. And Jehovah our asses. And Jehovah left hand of the pledge from Paddan, Rachel envied her not, Abram: I know good ears: and Hazarmaveth, and fifty righteous with his wife's name Jehovah said to do in the Jebusite, and said unto him, and with God: if thou hast changed towards the ark; they, neither shall be Abram in their dough which Abraham begat sons of it was purchased from his two");
        mr.run(values);

        // instead of sleep better to use job.waitForCompletion in mapreduce
        Thread.sleep(5000); // Wait until processing is finished. 

        // Print results
        for (Entry<String, ArrayList<Integer>> entry : mr.reducer.output.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
/** 
 * The MapReduce class runs Mapreduce program for wordcount if user select wordcount else does nothing
 */
class MapReduce {
    Mapper mapper;
    Reducer reducer;

    public MapReduce(String mapper, String reducer) {
        if (mapper.equals("WordCountMapper")) {
            this.mapper = new WordCountMapper();
        } else if (mapper.equals("NoOpMapper")) {
            this.mapper = new NoOpMapper();
        }
        if (reducer.equals("WordCountReducer")) {
            this.reducer = new WordCountReducer();
        } else if (reducer.equals("NoOpReducer")) {
            this.reducer = new NoOpReducer();
        }
    }

    /**
     * Run.
     *
     * @param values is array list of text in the document files
     */
    public void run(ArrayList<String> values) {
        // Phase 1: Map.
        for (int i = 0; i < values.size(); i++) {
            mapper.map(values.get(i));
        }
        // Phase 2: Reduce.
        for (final Entry<String, ArrayList<Integer>> entry : mapper.output.entrySet()) {
            Thread reduceThread = new Thread() {
                    public void run() {
                        reducer.reduce(entry.getKey(), entry.getValue().toArray(new Integer[]{} ));
                    }
                };
            reduceThread.start();
        }
    }
}

/** 
 * The MapperReducerBase class that produce output <key,value> format 
 * 
 */
class MapperReducerBase {
    // Output map that automatically sorts by key.
    TreeMap<String, ArrayList<Integer>> output = new TreeMap<String, ArrayList<Integer>>();

    // Called from map() to emit data.
    /**
     * emit function represent <key,value>.
     *
     * @param key is string  
     * @param value is string 
     */
    public void emit(String key, int value) {
        ArrayList<Integer> out = output.get(key);
        if (out != null) {
            out.add(value);
        } else {
            out = new ArrayList<Integer>();
            out.add(value);
            output.put(key, out);
        }
    }
}

/** 
 * The Mapper class that produce output <key,value> format and take text as inputformat
 * 
 */
class Mapper extends MapperReducerBase {
    // To be implemented by subclass.
    public void map(String input) {
    }
    
}

/** 
 * The Reducer class that produce output <key,value> format and take text as <key,array of values>
 * 
 */
class Reducer extends MapperReducerBase {
    // Implemented by subclass.
    public void reduce(String key, Integer[] values) {
    }
}

/** 
 * The NoOpMapper class does nothing special 
 * 
 */
//Mapper that does nothing special.
class NoOpMapper extends Mapper {
    /**
     * map function emit <key,value>.
     *
     * @param input as string of text 
     */	
	public void map(int input) {
		emit("key", input);
	}
}

/** 
 * The NoOpReducer class does nothing special 
 * 
 */
//Reducer that does nothing.
class NoOpReducer extends Reducer {
  /**
     * reduce function emit <key,value> and does nothing.
     *
     * @param key as string of words 
     * @param values as array of count 
     */	
 public void reduce(String key, int[] values) {
     for (int i = 0; i < values.length; ++i) {
         emit(key, values[i]);
     }
 }
 
}

/** 
 * The WordCountMapper class get input string and convert into <key,value> format for wordcount
 * 
 */
//wordcount
class WordCountMapper extends Mapper {
	 /**
     * map function emit <key,value> and output <words,count>.
     *
     * @param input as string of text 
     */		
 public void map(String input) {
     
	 // Find words, separated by spaces.
     StringTokenizer tokenizer = new StringTokenizer(input);
     while (tokenizer.hasMoreTokens()) {
    	 String word = tokenizer.nextToken();
    	 // remove all ".,:;)(" special character from words and convert them to lowercase so 
    	 // that they are identical
    	 word = word.replaceAll("[.,:;)(]", "").toLowerCase().trim();
    	 emit(word, 1);
    }
     

 }
}

/** 
 * The WordCountReducer class get <key,value> and convert into <key,sum> format for wordcount
 * 
 */
class WordCountReducer extends Reducer {
	 /**
     * reduce function emit <key,value> and output <words,noofwords>.
     *
     * @param keys as string of words
     * @param values as count of words 
     */			
 public void reduce(String key, Integer[] values) {
     int sum = 0;
     for (int i = 0; i < values.length; i++) {
         // Sum each value.
    	 sum += values[i];
     }
     emit(key, sum);
 }
}


