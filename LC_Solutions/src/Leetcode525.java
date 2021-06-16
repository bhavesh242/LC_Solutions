import java.util.HashMap;
import java.util.Map;

public class Leetcode525 {
	public int findMaxLength(int[] nums) {

		// Prefix Count method
		int sum = 0;
		int maxWindow = 0;
		// Create a map that stores counts along with their indexex
		Map<Integer, Integer> ctIdx = new HashMap<Integer, Integer>();
		// Put 0,-1 as without any elements, count is 0 (-1 is added for window length
		// calculations)
		ctIdx.put(0, -1);

		for (int i = 0; i < nums.length; i++) {
			// Add 1 to sum for 1 and -1 for 0
			sum = sum + (nums[i] == 1 ? 1 : -1);
			// If such a sum was already encountered, it means the windown between these two
			// windows is 0, therefore check with maxWindow
			if (ctIdx.containsKey(sum)) {
				maxWindow = Math.max(maxWindow, i - ctIdx.get(sum));
			}
			// Otherwise store this count with it's index in map
			else {
				ctIdx.put(sum, i);
			}
		}

		return maxWindow;
	}
}