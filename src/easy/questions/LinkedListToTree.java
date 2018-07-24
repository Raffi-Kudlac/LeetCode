package easy.questions;

import java.util.ArrayList;

import helper.SolutionOutline;

public class LinkedListToTree extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("LinkedListToTree");
		this.setDifficulty("Medium");
		
		ListNode test = buildOrderedList(9);		
		this.setInput(test.printVal());
		TreeNode answer = this.solution3(test);
		this.setOutput(serialize(answer));
		this.printResult();
	}
	
	public TreeNode solution3(ListNode head) {
		ArrayList<TreeNode> root = new ArrayList<>();
		ArrayList<TreeNode> level = new ArrayList<>();
		ArrayList<TreeNode> temp = new ArrayList<>();
		
		root.add(new TreeNode(head.val));
		level.addAll(root);
		ListNode pointer = head.next;
		
		while (pointer != null) {
			
			for (TreeNode node: level) {
				
				if (pointer == null) {
					break;
				}
				
				pointer = addNode(pointer, true, node);
				pointer = addNode(pointer, false, node);						
				
				temp.add(node.left);
				temp.add(node.right);
			}
			
			level.clear();
			level.addAll(temp);
			temp.clear();
		}
		
		return root.get(0);
	}
	
	public ListNode addNode (ListNode node, boolean left, TreeNode treeNode) {
		
		if (node == null) {
			return null;
		} else if (left) {
			treeNode.left = new TreeNode(node.val);		
		} else {
			treeNode.right = new TreeNode(node.val);
		}
		
		return node.next;
	}
	
	public TreeNode solution2(ListNode head) {
		
		TreeNode root = new TreeNode(head.val);			
		int size = 1;
		ListNode pointer = head;
		while(pointer.next != null) {
			size++;
			pointer = pointer.next;
		}
		int depth = (int) (Math.log10(size)/Math.log10(2)) + 1;
		
		pointer = head.next;
		for (int x = 1; x < depth; x++) {
			pointer = buildNextLevel(pointer, root, x);
		}
		
		return root;
	}
	
	public ListNode buildNextLevel(ListNode node, TreeNode treeNode, int level) {
		
		if (node == null) {
			return null;
		} else if (level == 1) {
			treeNode.left = new TreeNode(node.val);
			node = node.next;			
			if (node != null) {
				treeNode.right = new TreeNode(node.val);
			}
			return node.next;
		} else {
			ListNode n = buildNextLevel(node, treeNode.left, level -1);
			return buildNextLevel(n, treeNode.right, level -1);
		}				
	}
	
	
	public TreeNode solution(ListNode head) {
		
		int size = 1;
		ListNode pointer = head;
		while(pointer.next != null) {
			size++;
			pointer = pointer.next;
		}
		
		int depth = (int) (Math.log10(size)/Math.log10(2)) + 1;
		
		TreeNode[][] tree = new TreeNode[depth][(int)Math.pow(2, depth - 1)];
		pointer = head;
		
		for (int currentDepth = 0; currentDepth < depth; currentDepth++) {
			
			for (int shift = 0; shift < Math.pow(2, currentDepth); shift++) {
				if (pointer == null) {
					break;
				}
				tree[currentDepth][shift] = new TreeNode(pointer.val);
				
				if (currentDepth != 0 && shift % 2 == 0) {
					tree[currentDepth - 1][shift/2].left = tree[currentDepth][shift];
				} else if (currentDepth != 0 && (shift - 1) % 2 == 0) {
					tree[currentDepth - 1][(shift - 1)/2].right = tree[currentDepth][shift];
				}								
				pointer = pointer.next;
			}
		}
		
		return tree[0][0];
	}
}
