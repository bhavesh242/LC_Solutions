
public class Leetcode1763 {
	
	//Divide and Conquer
	public String longestNiceSubstring(String s) {

		boolean[] upper = new boolean[26];
		boolean[] lower = new boolean[26];

		for (char c : s.toCharArray()) {
			int asci = (int) c;
			if (c >= 97) {
				upper[asci - 97] = true;
			} else {
				lower[asci - 65] = true;
			}
		}

		boolean flag = true;
		char breaking = '#';

		for (int i = 0; i < 26; i++) {
			if (upper[i] != lower[i]) {
				flag = false;
				breaking = upper[i] ? (char) (i + 97) : (char) (i + 65);
				break;
			}
		}

		if (flag) {
			return s;
		}

		String s1, s2;

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == breaking) {
				s1 = longestNiceSubstring(s.substring(0, i));
				s2 = longestNiceSubstring(s.substring(i + 1, s.length()));
				return s1.length() < s2.length() ? s2 : s1;
			}
		}

		return "";

	}
}