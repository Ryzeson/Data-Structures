/**
 * Ryzeson Maravich
 * hw7
 * HuffmanCODEC.java
 * 
 * Encoding and decoding algorithms using Huffman Coding
 */

public class HuffmanCODEC {

	private HuffmanTree codeTree;
	
	public HuffmanCODEC(HuffmanTree tree) {
		codeTree = tree;
	}

	/**
	 * encodes msg using the huffman code tree given in the constructor and returns an encoded string with only 0's and 1's
	 * @param msg	message to be encoded
	 * @return encoded string with only 0's and 1's; if msg cannot be encoded using the given code tree, return null
	 */
	public String encode(String msg) {
		if (codeTree.empty()) {
			return null;
		}

		int size = msg.length();
		HuffmanNode ptr = codeTree.getRoot();

		String encoding = "";
		char ch;

		for(int i = 0; i < size; i++) {
			String bit = "";
			ch = msg.charAt(i);
			while (!ptr.isLeaf()) {
				if (ptr.getLeft().contains(ch)) {
					ptr = ptr.getLeft();
					bit += "0";
				}
				else if (ptr.getRight().contains(ch)) {
					ptr = ptr.getRight();
					bit += "1";
				}
				else {
					return null;
				}
			}
			encoding += bit;
			ptr = codeTree.getRoot();
		}

		return encoding;

	}

	/**
	 * decodes bits (encoded message of 0's and 1's) using the huffman code tree given in the constructor and returns the decoded (original) message
	 * @param bits 	encoded message of 0's and 1's to decode
	 * @return decoded (original) message; if bits cannot be decoded using the given code tree, return null
	 */ 
	public String decode(String bits) {
		if (codeTree.empty()) {
			return null;
		}

		int size = bits.length();
		HuffmanNode ptr = codeTree.getRoot();
		
		String decoding = "";
		int i = 0; 

		while (i < size) {
			while (!ptr.isLeaf()) {
				if(bits.charAt(i) == '0') {
					ptr = ptr.getLeft();
				}
				else if (bits.charAt(i) == '1') {
					ptr = ptr.getRight();
				}
				else {
					return null;
				}
				i++;
			}
			decoding += ptr.leafValue();
			ptr = codeTree.getRoot();
		}
		return decoding;
	}

}
