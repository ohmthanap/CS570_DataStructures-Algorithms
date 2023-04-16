public class BinarySearch {
	/**
	 * Searches for a key in the array using Binary Search.
	 *
	 * Binary search first looks at the middle element. If the
	 * middle element is our key then returns that index. If it
	 * is less greater than our key then adjusts the upper bound.
	 * If it is less than our key then adjusts the lower bound.
	 *
	 * Time Complexity: O(log(N))
	 * Space Complexity: O(1)
	 *
	 * @param array the array in which to search.
	 * @param key the key to be searched.
	 * @return the index of the key if it is found, else -1.
	 */
	public static int binarySearch(int[] array, int key) {
		// Initialize our bounds.
		int low = 0;
		int high = array.length - 1;

		// Loop till our bounds cross.
		while (low <= high) {
			// Get the mid-point of our current bounds.
			int mid = low + (high - low) / 2;

			if (array[mid] == key) {
				// We found the key we were looking for, so return the index.
				return mid;
			} else if (array[mid] > key) {
				// The key we are looking for is less than the middle element.
				// Hence we set the upper bound to be the element just before
				// the middle element.
				high = mid - 1;
			} else {
				// The key we are looking for is greater than the middle element.
				// Hence we set the lower bound to be the element just after
				// the middle element.
				low = mid + 1;
			}
		}

		// The key wasn't found in the array, so return -1.
		return -1;
	}

	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};

		int index = binarySearch(array, 2);
		if (index == -1) {
			System.out.println("Number not found in the array!");
		} else {
			System.out.println("Number found at index: " + index);
		}

		index = binarySearch(array, 900);
		if (index == -1) {
			System.out.println("Number not found in the array!");
		} else {
			System.out.println("Number found at index: " + index);
		}
	}
}
