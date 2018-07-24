package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import helper.SolutionOutline;

public class InOrderTraversal extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("InOrderTraversal");
		this.setDifficulty("Medium");
		String tree = "1,null,2,null,null,3";
		TreeNode test = deserialize(tree); 		
		this.setInput(tree);
		
		List<Integer> answer = this.solution(test);
		this.setOutput(Arrays.toString(answer.toArray()));
		this.printResult();
	} 
	
	public List<Integer> solution(TreeNode root) {
		ArrayList<Integer> answer = new ArrayList<Integer>();
        Stack<TreeNode> path = new Stack<TreeNode>();
        HashMap<TreeNode, Boolean> visited = new HashMap<>();
        
        TreeNode pointer = root;
        
        do {            
            boolean left = goLeft(pointer, visited);
            boolean right = goRight(pointer, visited);
            
            if (left) {
                path.add(pointer);
                pointer = pointer.left;                
            } else if (!left && !visited.containsKey(pointer)) {
                answer.add(pointer.val);
                visited.put(pointer, true);
                
                if (right) {
                	path.add(pointer);
                    pointer = pointer.right;
                }
                
            } else if (!left && right && visited.containsKey(pointer)) {
                path.add(pointer);
                pointer = pointer.right;                
            } else if (!left && !right) {
                pointer = path.pop();                
            }
        } while(path.size() > 0);
        
        return answer;
	}
	
	public boolean goLeft(TreeNode node, HashMap<TreeNode, Boolean> visited) {                
        
        return !(node.left == null || visited.containsKey(node.left));        
    }
    
    public boolean goRight(TreeNode node, HashMap<TreeNode, Boolean> visited) {                
        return !(node.right == null || visited.containsKey(node.right));        
    }
}
