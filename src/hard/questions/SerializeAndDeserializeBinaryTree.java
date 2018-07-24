package hard.questions;

import java.util.ArrayList;

import helper.SolutionOutline;

public class SerializeAndDeserializeBinaryTree extends SolutionOutline {
	public void runTest() {
		this.setClassName("SerializeAndDeserializeBinaryTree");
		this.setDifficulty("Hard");
		
		this.printResult();
		TreeNode test = deserialize("1,2,3,null,null,4,5");				
		this.solution(test);				
	} 
	
	public void solution(TreeNode root) {
		String serialize = serialize(root);
		System.out.println("The Tree was serialized to: " + serialize);
		
		TreeNode tree = deserialize(serialize);
		String serialize2 = serialize(tree);
		
		System.out.println("The Tree was serialized AGAIN to: " + serialize2);		
	}
	
	public String serialize(TreeNode root) {
		String answer = "";
		int size = 10;
		ArrayList<String> data = new ArrayList<String>(size);
		
		for (int x = 0; x < size; x++) {
			data.add("null");
		}		
		parse(root, 1, data);		
		
		String lastElement = data.get(data.size()-1);
		while (lastElement == null || lastElement.equals("null")) {
			data.remove(data.size()-1);
			lastElement = data.get(data.size()-1);			
		} 
		
		
		for (int x = 1; x < data.size(); x++) {
			if (data.get(x) != null) {
				answer += data.get(x) + ",";
			} 			
		}		
		answer = answer.substring(0, answer.length()-1);
		return answer;
	}	
	
	public void parse(TreeNode node, int pos, ArrayList<String> data) {
		
		checkSize(data, pos);
		if (node != null) {			
			data.set(pos, Integer.toString(node.val));			
			parse(node.left, pos*2, data);
			parse(node.right, pos*2 + 1, data);			
		} else {			
			data.set(pos, "null");
		}
	}
	
	public void checkSize(ArrayList<String> data, int pos) {
		
		while(data.size() <= pos) {
			data.add(null);
		}
	}
		
	public TreeNode deserialize(String data) {
		
		String[] elements = data.split(",");
		TreeNode[] list = new TreeNode[elements.length+1];
		list[0] = null;
		
		for (int x = 0; x < elements.length; x++) {
			if (elements[x].equals("null")) {
				list[x+1] = null;
			} else {
				list[x+1] = new TreeNode(Integer.parseInt(elements[x]));
			}			
		}
		
		TreeNode current;
		for (int x = list.length - 1; x >= 1; x--) {
			current = list[x];
			if (current != null) {
				if (x % 2 == 0 && list[x/2] != null) {
					list[x/2].left = current;
				} else if (list[(x-1)/2] != null) {
					list[(x-1)/2].right = current;
				}
			}
		}				
		return list[1];
	}
}
