package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import helper.SolutionOutline;

public class PennilessPilgramRiddle extends SolutionOutline {
	
	String right = "Right";
	String left = "Left";
	String up = "Up";
	String down = "Down";
	
	public void runTest() {
		this.setClassName("PennilessPilgramRiddle");
		this.setDifficulty("Medium");
		
		String test = "";		
		this.setInput(test);
		List<String> answer = this.solution2();
		String output = "Path: " + Arrays.toString(answer.toArray());
		output = output + "\n\tDept: " + calculateDept(answer);
		this.setOutput(output);
		this.printResult();
	} 
		
	public List<String> solution2() {
		
		ArrayList<String> path = new ArrayList<String>();
		path.add(right);
		path.add(right);
		Town t = TravelTheNewTown(path);
		t.printTown();
		Node start = t.searchTown(0, 2);
		List<String> answer = findPath(t, start, 4.0, path, 2);
		
		return answer;
	}
	
	public double calculateDept(List<String> path) {
		
		double amount = 0;
		for (String s: path) {
			if (s.equals(left)) {
				amount -= 2;
			} else if (s.equals(right)) {
				amount += 2;
			} else if (s.equals(up)) {
				amount = amount /2;
			} else if (s.equals(down)) {
				amount *= 2;
			}
		}
		
		return amount;
		
	}
	
	public List<String> findPath(Town town, Node current, double owe, ArrayList<String> path, int depth) {
		
		try {			
			if (current.x == town.height - 1 && current.y == town.width - 1 && owe == 0.0) {
				return path;
			} else if (current.x == town.height - 1 && current.y == town.width - 1 && owe != 0.0) {
				//System.out.println("made it to the end with a dept of: " + owe);
				return null;
			} 
						
			List<String> answer;
			
			// goes down
			if (!current.down.visited) {
				//System.out.println("current node: (" + current.x + ", " + current.y + ") going down");
				path.add(down);
				current.down.setVisited(true);
				answer = findPath(town, current.down.getOtherNode(current), owe * 2.0, path, depth+1);
				if (answer != null) {
					return answer;
				}
				resetPath(path, depth);
				town = TravelTheNewTown(path);
				current = town.searchTown(current.x, current.y);
			}			
			
			
			// goes right
			if (!current.right.visited) {
				//System.out.println("current node: (" + current.x + ", " + current.y + ") going right");
				path.add(right);
				current.right.setVisited(true);
				answer = findPath(town, current.right.getOtherNode(current), owe + 2.0, path, depth+1);			
				if (answer != null) {
					return answer;
				}
				resetPath(path, depth);
				town = TravelTheNewTown(path);
				current = town.searchTown(current.x, current.y);
			}			
			
			
			// goes left
			if (!current.left.visited) {
				//System.out.println("current node: (" + current.x + ", " + current.y + ") going left");
				path.add(left);
				current.left.setVisited(true);
				answer = findPath(town, current.left.getOtherNode(current), owe - 2.0, path, depth+1);			
				if (answer != null) {
					return answer;
				}
				resetPath(path, depth);
				town = TravelTheNewTown(path);
				current = town.searchTown(current.x, current.y);
			}			
			
			
			// goes up
			if (!current.up.visited) {
				//System.out.println("current node: (" + current.x + ", " + current.y + ") going up");
				path.add(up);
				current.up.setVisited(true);
				answer = findPath(town, current.up.getOtherNode(current), owe / 2.0, path, depth+1);			
				if (answer != null) {
					return answer;
				}
			}
								
			
		} catch (Exception e) {
			return null;
		}
		
		return null;
	}
	
	public Town TravelTheNewTown(ArrayList<String> path) {
		Town t = buildTown();
		
		Node current = t.topLeft;
		for (String s: path) {
			if (s.equals(right)) {
				current.right.setVisited(true);
				current = current.right.getOtherNode(current);
			} else if (s.equals(left)) {
				current.left.setVisited(true);
				current = current.left.getOtherNode(current);
			} else if (s.equals(up)) {
				current.up.setVisited(true);
				current = current.up.getOtherNode(current);
			} else if (s.equals(down)) {
				current.down.setVisited(true);
				current = current.down.getOtherNode(current);
			}
		}
		
		return t;
	}
	
	public void resetPath(List<String> path, int depth) {
		
		while (path.size() > depth) {
			path.remove(path.size() -1);
		}
	}
	
	
	public Town buildTown() {		
		
		int width = 6;
		int height = 6;
		Town town = new Town(height, width);
		
		Node n;
		for (int x = 0; x < height; x++ ) {
			for (int y = 0; y < width; y++) {
				n = new Node(x, y);
				town.AddNode(n);
			}
		}		
		return town;
	}
	
	public class Town {
		Node topLeft;
		int height = 0;
		int width = 0;
		
		Town(Node tf) {
			topLeft = tf;
		}
		
		Town (int h, int w){
			height = h;
			width = w;
		}
		
		public void printTown() {
			Node x = topLeft;
			Node y = topLeft;
			
			while (x != null) {
				while (y != null) {
					System.out.print("(" + y.x + ", " + y.y +")");
					y = y.right.getOtherNode(y);
					if (y != null) {
						System.out.print(" --> ");
					} else {
						System.out.println();
						System.out.println();
					}
				}
				
				x = x.down.getOtherNode(x);
				y = x;
			}
		}
		
		public void AddNode(Node n) {
			
			if (topLeft == null) {
				topLeft = n;
				return;
			}
			
			Node above = searchTown(n.x - 1, n.y);
			Node toTheLeft = searchTown(n.x, n.y-1);
			Node toTheRight = searchTown(n.x, n.y + 1);
			Node below = searchTown(n.x + 1, n.y);
			
			if (above != null) {
				n.up = above.down;
				n.up.setOtherNode(n);
			}
			
			if (toTheLeft != null) {
				n.left = toTheLeft.right;
				n.left.setOtherNode(n);
			}
			
			if (toTheRight != null) {
				n.right = toTheRight.left;
				n.right.setOtherNode(n);
			}
			
			if (below != null) {
				n.down = below.up;
				n.down.setOtherNode(n);
			}
		}
		
		public Node searchTown(int coordX, int coordY) {
			
			Node x = topLeft;
			Node y = topLeft;
			
			while (x != null) {
				while (y != null) {
					if (y.x == coordX && coordY == y.y) {
						return y;
					}
					y = y.right.getOtherNode(y);
				}
				
				x = x.down.getOtherNode(x);
				y = x;
			}
			
			return null;
		}
	}
	
	public class Node {
		Edge up;
		Edge left;
		Edge down;
		Edge right;
		int x;
		int y;		
		
		Node (int x, int y) {
			this.x = x;
			this.y = y;
			up = new Edge(this);
			down = new Edge(this);
			left = new Edge(this);
			right = new Edge(this);
		}
		
		public Edge[] getAllEdges() {
			Edge[] array = {up, right, down, left};
			return array;
		}
	}
	
	public class Edge {
		Node a;
		Node b;
		boolean visited;
		
		Edge(Node a, Node b) {
			this.a = a;
			this.b = b;
			visited = false;
		}
		
		Edge(Node a) {
			this.a = a;
			visited = false;
		}
		
		public Node getOtherNode(Node n) {
			if (n.equals(a)) {
				return b;
			}			
			return a;
		}
		
		public void setOtherNode(Node n) {
			if (a == null) {
				a = n;
			} else if (b == null) {
				b = n;
			}
		}
		
		public void setVisited(boolean v) {this.visited = v;}
		
		public boolean getVisited() {return visited;}
	}
}
