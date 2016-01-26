/**
 * http://coderwalk.blogspot.com/2012/12/counting-sort-java-implementation.html
 * 
 *
 */

public class CountingSort {
	public static void main(String[] args) {
		int[] arrayToSort = new int[] { 0, 3, 1, 0, 5, 2, 4, 5, 2 };
		int[] sortedArray = counting_sort(arrayToSort);

		for (int i = 0; i < sortedArray.length; i++) {
			System.out.print(sortedArray[i] + ",");
		}
	}

	public static int[] counting_sort(int[] arrayToSort) {
		int maxValue = getMaxVal(arrayToSort);
		int[] finalSortedArray = new int[arrayToSort.length];
		int[] tempArray = new int[maxValue + 1];

		for (int i = 0; i < arrayToSort.length; i++) {
			tempArray[arrayToSort[i]] = tempArray[arrayToSort[i]] + 1;
		}

		for (int i = 1; i < maxValue + 1; i++) {
			tempArray[i] = tempArray[i] + tempArray[i - 1];
		}

		for (int i = (arrayToSort.length - 1); i >= 0; i--) {
			finalSortedArray[tempArray[arrayToSort[i]] - 1] = arrayToSort[i];
			tempArray[arrayToSort[i]] = tempArray[arrayToSort[i]] - 1;
		}
		
		for(int i=0;i<finalSortedArray.length/2;i++){
			int temp=finalSortedArray[i];
			finalSortedArray[i] = finalSortedArray[(finalSortedArray.length-1)-i];
			finalSortedArray[(finalSortedArray.length-1)-i] = temp;
		}
		return finalSortedArray;
	}

	private static int getMaxVal(int[] arrayToSort) {
		int maxVal = Integer.MIN_VALUE;
		for (int i : arrayToSort) {
			if (i > maxVal) {
				maxVal = i;
			}
		}
		return maxVal;
	}
}