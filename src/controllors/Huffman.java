package controllors;
/**
 * @author Judy
 * 
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;



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

	HashMap<Character, String> code = new HashMap<>();
	StringBuffer test;


	// input is an array of frequencies, indexed by character code
	public HuffmanTree buildTree(char[] key ,int[] value) {
		PriorityQueue<HuffmanTree> trees = new PriorityQueue<HuffmanTree>();
		// initially,  a forest of leaves
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


	public  void printMap(CharacterCounter c) {


		char[] key = new char[c.counts.size()];
		int[] value = new int[c.counts.size()];
		int index = 0;
		for (Map.Entry<Character, Integer> mapEntry : c.getCharacterCounts()) {
			key[index] = mapEntry.getKey();
			value[index] = mapEntry.getValue();
			index++;
		}
		HuffmanTree tree = buildTree(key,value);

		outputTree(tree, "");




	}



	// Print out the codes; insert these codes into CodeTable
	public  void outputTree(HuffmanTree tree, String prefix) {
		assert tree != null : "Bad input tree";

		// This is a full binary tree so must not be null subtree
		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf)tree;
			System.out.println(leaf.value + "\t" + prefix);
			char temp  = leaf.value;
			code.put(temp, prefix);
			//total += prefix.length() * node.weight();

			//print string of codes
			StringBuffer huff =  new StringBuffer();

			for(int i = 0; i < Item.item.length(); i++){
				char temp4 = Item.item.charAt(i);
				huff.append(code.get(temp4));
			}
			test = huff;

		}
		else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode)tree; {
				outputTree(node.left, prefix + "0");
				outputTree(node.right, prefix + "1");
			}

		}

	}
	


}// end of Huffman
