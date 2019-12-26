
public class BTTest {
	
	public static void main(String[] args) {
		BTNode<Integer> n3 = new BTNode<>(3, null, null);
		BTNode<Integer> n4 = new BTNode<>(4, null, null);
		BTNode<Integer> n5 = new BTNode<>(5, null, null);
		
		BTNode<Integer> n2 = new BTNode<>(2, null, n3);
		BTNode<Integer> n6 = new BTNode<>(6, n4, n5);
		
		BTNode<Integer> n1 = new BTNode<>(1, n2, n6);
		
		System.out.println(BTNode.countNodes(n1));
		System.out.println(BTNode.getHeight(n1));
		
		System.out.println(BTNode.preorder(n1));
		System.out.println(BTNode.postorder(n1));
		System.out.println(BTNode.inorder(n1));
		System.out.println(BTNode.levelorder(n1));
		
	}
}
