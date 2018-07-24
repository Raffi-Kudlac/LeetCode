package medium.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.SolutionOutline;

public class LowestCommonAncestor extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("LowestCommonAncestor");
		this.setDifficulty("Medium");
		
		TreeNode root = deserialize("3,5,1,6,2,0,8,null,null,7,4");		
		this.setInput("Tree");
		TreeNode answer = this.solution(root, findNode(root, 6), findNode(root, 4));
		this.setOutput(answer.printVal());
		this.printResult();
	}		
	
	public TreeNode solution(TreeNode root, TreeNode p, TreeNode q) {
		List<TreeNode> pathP = findPath(root, p, new ArrayList<TreeNode>());
        List<TreeNode> pathQ = findPath(root, q, new ArrayList<TreeNode>());
        
        HashMap<Integer, Boolean> paths = new HashMap<Integer, Boolean>();
        
        for (TreeNode t : pathP) {
            paths.put(t.val, true);
        }        
        
        TreeNode LCA = null;
        for (TreeNode t : pathQ) {
            if (paths.containsKey(t.val)) {
                LCA = t;
            } else {
                break;
            }
        }
        
        return LCA;  
	}	
	
	
	public List<TreeNode> findPath(TreeNode current, TreeNode dest, List<TreeNode> path) {
        
        if (current == null) {
            return null;
        }
        
        path.add(current);
        if (current.val == dest.val) {            
            return path;
        }
        
        List<TreeNode> newPath = new ArrayList<TreeNode>();
        newPath.addAll(path);        
        
        List<TreeNode> answer = findPath(current.left, dest, newPath);
        
        if (answer == null) {
            newPath.clear();
            newPath.addAll(path);            
        } else {
            return answer;
        }
        
        return findPath(current.right, dest, newPath);                    
    }
}
