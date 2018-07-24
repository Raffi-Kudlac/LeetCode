package helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionOutline {
	String className;
	String input;
	String output;
	String difficulty;
	
	public void printResult() {
		System.out.println("ClassName: " + className);
		System.out.println("Difficulty: " + difficulty + "\n");
		System.out.println("Input: " + "\n\t" + input);
		System.out.println("Ouput: " + "\n\t" + output);
	}
	
	public void runTest() {
		this.setClassName("");
		this.setDifficulty("Medium");
		
		String test = "";		
		this.setInput(test);
		String answer = this.solution();
		this.setOutput(answer);
		this.printResult();
	} 
	
	public String solution() {
		return "";
	}		
	
	public String TwoDCharArrayToStirng(char[][] array) {
		String answer = "";
		
		for (char[] data: array) {
			answer += Arrays.toString(data) + "\n\t";
		}
		
		return answer;
	}
	
	public String TwoDIntArrayToStirng(int[][] array) {
		String answer = "";
		
		for (int[] data: array) {
			answer += Arrays.toString(data) + "\n\t";
		}
		
		return answer;
	}
	
	public String TwoDListToString(List<List<Integer>> list) {
		String answer = "";
		
		for (List<Integer> numberList: list) {
			answer += numberList.toString() + "\n\t";
		}
		
		return answer;
	} 
		
	public TreeNode deserialize(String data) {
		
		String[] elements = data.split(",");
		TreeNode[] list = new TreeNode[elements.length + 1];
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
	
	
	public TreeNode findNode(TreeNode node, int val) {
		if (node == null) {
			return null;
		} else if (node.val == val) {
			return node;
		}
				
		TreeNode left = findNode(node.left, val);
		
		if (left == null) {
			return findNode(node.right, val);
		} else {
			return left;
		}		
	}
	
	
	public ListNode buildOrderedList(int n) {
		ListNode head = new ListNode(1);		
		ListNode pointer = head;
		for (int x = 2; x <= n; x++) {
			pointer.next = new ListNode(x);
			pointer = pointer.next;
		}
		
		return head;
	}
	
	public class ListNode {
		
		public int val;
		public ListNode next;
		
		public ListNode(int x) {val = x;}
		
		public String printVal() {
			if (next == null) {
				return Integer.toString(val);
			} else {
				return  Integer.toString(val) + "->" + next.printVal();
			}
		}
	}
	
	public class TreeNode {
		 public int val;
		 public TreeNode left;
		 public TreeNode right;
		 public TreeNode(int x) { val = x; }
		 
		 public String printVal() {
			 return Integer.toString(val);
		 }
	}
	
	public class UndirectedGraphNode {
		public int label;
		public List<UndirectedGraphNode> neighbors;
		public UndirectedGraphNode(int x) { 
			label = x; 
			neighbors = new ArrayList<UndirectedGraphNode>(); 
		}
	};
	
	
	// ************** Getters & Setters **************

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getInput() {
		return input;
	}
	
	public void setInput(int input) {
		this.input = Integer.toString(input);
	}
	
	public void setInput(int[] input) {
		this.input = Arrays.toString(input);
	}
	
	public void setInput(String input) {
		this.input = input;
	}

	public void setOutput(int[] output) {
		this.output = Arrays.toString(output);
	}
	
	public String getOutput() {
		return output;
	}
	
	public void setOutput(boolean output) {
		this.output = Boolean.toString(output);
	}
	
	public void setOutput(int output) {
		this.output = Integer.toString(output);
	}

	public void setOutput(String output) {
		this.output = output;
	}
	
	public void setOutput(List<List<Integer>> list) {
		this.output = TwoDListToString(list);
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}	
}

