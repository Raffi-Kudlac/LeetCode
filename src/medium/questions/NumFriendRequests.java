package medium.questions;

import java.util.Arrays;

import helper.SolutionOutline;

public class NumFriendRequests extends SolutionOutline {
	public void runTest() {
		this.setClassName("NumFriendRequests");
		this.setDifficulty("Medium");
		
		int[] test = {3, 4, 77, 76, 70, 80, 10};		
		this.setInput(test);
		int answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public int solution(int ages[]) {
		Arrays.sort(ages);
        
        int answer = 0;
        
        for (int indexA = ages.length - 1; indexA >=1; indexA--) {
            
            int indexB = indexA - 1;
            
            while (indexB >= 0 && ages[indexB] > 0.5 * ages[indexA] + 7) {
                answer += checkAge(ages[indexA], ages[indexB]);
                indexB--;
            }
        }
        
        return answer;
    }
	
	public int checkAge(int ageA, int ageB) {
        
        if (ageB > 100 && ageA < 100) {
            return 0;
        } else if (ageA == ageB) {
            // counts for age A friending Age B and visa versa.
        	// because ages are sorted, age B will not ask age A so account for that
        	return 2;
        } else {
            return 1;
        }
    }
}
