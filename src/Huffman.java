
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;



abstract class HuffmanTree implements Comparable<HuffmanTree> {
    public final int frequency; // the frequency of this tree
   
  
    public HuffmanTree(int freq) { 
    	frequency = freq;
    	}

    // compares on the frequency
    public int compareTo(HuffmanTree tree) {
        return frequency - tree.frequency;
    }
}

class HuffmanLeaf extends HuffmanTree {
    public final char value; // the character this leaf represents

    public HuffmanLeaf( char val, int freq) {
        super(freq);
        value = val;
    }

	

}

class HuffmanNode extends HuffmanTree {
    public final HuffmanTree left, right; // subtrees

    public HuffmanNode(HuffmanTree l, HuffmanTree r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}

public class Huffman {
	
	
    // input is an array of frequencies, indexed by character code
    public static HuffmanTree buildTree(char[] key ,int[] value) {
    	PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
        // initially, we have a forest of leaves
        // one for each non-empty character
    	
    	 for (int i = 0; i < key.length; i++)
             if (key[i] > 0)
                 trees.offer(new HuffmanLeaf(key[i], value[i]));
    	
        assert trees.size() > 0;
        // loop until there is only one tree left
        while (trees.size() > 1) {
            // two trees with least frequency
            HuffmanTree a = trees.poll();
            HuffmanTree b = trees.poll();

            // put into new node and re-insert into queue
            trees.offer(new HuffmanNode(a, b));
        }
    	  
        return trees.poll();
  
    }
    
    
	public static void printMap(CharacterCounter c) {
        
       // Map<String, Object> map = new HashMap<String, Object>();
        char[] key = new char[c.counts.size()];
        int[] value = new int[c.counts.size()];
        int index = 0;
        for (Map.Entry<Character, Integer> mapEntry : c.getCharacterCounts()) {
            key[index] = mapEntry.getKey();
            value[index] = mapEntry.getValue();
            index++;
        }
        HuffmanTree tree = buildTree(key,value);
   
        printCodes(tree, new StringBuffer());
       
    }
  

    public static void printCodes(HuffmanTree tree, StringBuffer prefix) {
        assert tree != null;
        if (tree instanceof HuffmanLeaf) {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            

            // print out character, frequency, and code for this leaf (which is just the prefix)
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);

        } else if (tree instanceof HuffmanNode) {
            HuffmanNode node = (HuffmanNode)tree;

            // traverse left
            prefix.append('0');
            printCodes(node.left, prefix);
            prefix.deleteCharAt(prefix.length()-1);

            // traverse right
            prefix.append('1');
            printCodes(node.right, prefix);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

  

	}
   
   
   
