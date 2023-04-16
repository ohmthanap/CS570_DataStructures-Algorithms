public class Complexity {
	public void complexity(int n) {
		// Time Complexity: O(1).
		// Space Complexity: O(1).
		for (int i = 0; i < 10; i++) {
			// Do something
		}

		// Time Complexity: O(N).
		// Space Complexity: O(1).
		for (int i = 0; i < n; i++) {
			// Do something
		}

		// Time Complexity: O(N^2)
		// Space Complexity: O(1).
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// Do something
			}
		}

		// Time Complexity: O(N^2)
		// Space Complexity: O(1)
		for (int i = 0; i < n; i++) {
			for (int j = n; j > 0; j--) {
				// Do something
			}
		}

		// Time Complexity: O(N*log(N)).
		// Space Complexity: O(N).
		for (int i = 0; i < n; i++) {
			int[] arr = new int[n];
			for (int j = n; j > 0; j /= 2) {
				// Do something
			}
		}
	}
}
