import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.Runtime.getRuntime;

/* https://vinayakgarg.wordpress.com/2011/10/25/time-comparison-of-quick-sort-insertion-sort-and-bubble-sort/ */
/* https://www.javatpoint.com/insertion-sort-in-java */
/* https://www.javatpoint.com/bubble-sort-in-java */
/* https://stackabuse.com/merge-sort-in-java/ */
/* http://www.kylesconverter.com/time/nanoseconds-to-seconds */
/* https://en.wikipedia.org/wiki/Best,_worst_and_average_case */
/* https://medium.com/basecs/making-sense-of-merge-sort-part-1-49649a143478 */
/* https://www.cs.auckland.ac.nz/courses/compsci220s1t/archive/compsci220ft/tutorials/tut04/TUTORIAL-4.pdf */

public class Main {
	static boolean exit = false;
	static int numberToFind;
	static ArrayList<Integer> arr = new ArrayList<>();
	static boolean doneExec = false;

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);

		do {
			System.out.println("****************************");
			System.out.println("1) Run for each algorithm");
			System.out.println("Returns the avg for: ");
			System.out.println("   2 - Quick sort");
			System.out.println("   3 - Insertion sort");
			System.out.println("   4 - Merge sort");
			System.out.println("   5 - Binary Search");
			System.out.println("6) Exit");
			System.out.println("****************************");
			System.out.print(">> ");

			int choice1 = input.nextInt();
			input.nextLine();

			if(choice1 == 1) {
				do {
					System.out.println("=====================================================================================================");
					System.out.println("Big oh analysis on several sorting and a searching algorithms.");
					System.out.println("The running time of each algorithm is calculated by (currentTIme - startTime)");
					System.out.println("Running time is displayed in nano seconds, milli seconds and seconds!");
					System.out.println("=====================================================================================================");

					int choice = 0;
					boolean done = false;
					do {
						try {
							System.out.println("******************");
							System.out.println("******************");
							System.out.println("1) Quick sort");
							System.out.println("2) Merge sort");
							System.out.println("3) Insertion sort");
							System.out.println("4) Binary search");
							System.out.println("5) Exit");
							System.out.println("******************");
							System.out.print(">> ");
							choice = input.nextInt();
							done = true;
						} catch (InputMismatchException e) {
							input.nextLine();
							System.err.println("Number must be an integer");
						}
					} while (!done);

					if(choice == 5)
						exit = true;

					switch (choice) {
						case 1:
							System.out.println("QUICK SORT");
							// we call this everytime so that the algorithm can have a new unsorted array everytime.
							arr = inputSizeArr(takeSize());
							Long sTime = System.nanoTime();
							quickSort(arr);
							Long runtime = (System.nanoTime() - sTime);
							displayRunTime(runtime);
							break;
						case 2:
							System.out.println("MERGE SORT");
							arr = inputSizeArr(takeSize());
							Long sTimeMerge = System.nanoTime();
							mergeSort(arr, 0, arr.size() - 1);
							Long runTimeMerge = System.nanoTime() - sTimeMerge;
							displayRunTime(runTimeMerge);
							break;
						case 3:
							System.out.println("INSERTION SORT");
							arr = inputSizeArr(takeSize());
							LinkedList<Integer> integers = covertToLinkedList(arr);
//							System.out.println(integers.getClass().getSimpleName());
							Long sTimeInsertionSort = System.nanoTime();
							insertionSort(integers);
							Long runTimeInsertionSort = System.nanoTime() - sTimeInsertionSort;
							displayRunTime(runTimeInsertionSort);
							// Test
							System.out.print(Arrays.toString(integers.toArray()) + System.lineSeparator());
							break;
						case 4:
							System.out.println("BINARY SEARCH");
							arr = inputSizeArr(takeSize());
							Long sTimeBinarySearch = System.nanoTime();
							System.out.print("Enter number to search for: ");
							numberToFind = input.nextInt();
							input.nextLine();
							binarySearch(arr, numberToFind);
							Long runtimeBinarySearch = (System.nanoTime() - sTimeBinarySearch);
							displayRunTime(runtimeBinarySearch);
							break;
						case 5:
							System.out.println("Thanks for using!");
							System.exit(0);
							break;
						default:
							System.out.println("Invalid input");
					}
				} while (!exit);
			} else if(choice1 == 2) {
				// quick sort
				AverageRunTime averageRunTime = new AverageRunTime();
				System.out.println("not yet implemented");

			} else if(choice1 == 3) {
				// insertion sort
				AverageRunTime averageRunTime = new AverageRunTime();
				averageRunTime.runInsertion();
			} else if(choice1 == 4) {
				// Merge sort
				AverageRunTime averageRunTime = new AverageRunTime();
				averageRunTime.runMergeSort();
			} else if(choice1 == 5) {
				// binary search
				AverageRunTime averageRunTime = new AverageRunTime();
				System.out.println("not yet implemented");

			} else if(choice1 == 6) {
				doneExec = true;
				System.out.println("Bye!");
				getRuntime().exit(0);
			} else {
				System.out.println("Invalid input!");
			}
		} while (!doneExec);
	}

	/**
	 * @param runTime runtime of the algorithm to display runtimes of.
	 */
	static void displayRunTime(Long runTime) {
		System.out.println("Run time: " + OutputColor.ANSI_BLUE + runTime + " nano seconds.");

		double milliseconds = ((double) runTime / 1_000_000);
		System.out.println(OutputColor.ANSI_RESET + "Run time: " + OutputColor.ANSI_BLUE + milliseconds + " milli seconds.");

		double seconds = ((double) runTime / 1_000_000_000);
		System.out.println(OutputColor.ANSI_RESET + "Run time: " + OutputColor.ANSI_BLUE + seconds + (" seconds.") + OutputColor.ANSI_RESET);
	}

	static int compare(int a, int b) {
		return Integer.compare(a, b);
	}

	static LinkedList<Integer> covertToLinkedList(ArrayList<Integer> arr) {
		LinkedList<Integer> integers = new LinkedList<>();
		for(Integer integer : arr) {
			integers.add(integer);
		}
		return integers;
	}

	/**
	 * @return size of user input and will be used in {@link #inputSizeArr} to create array list of length {@code size}.
	 * @throws IOException - invalid input(input not instanceof int)
	 *                     - if size < 0 or size > 1000000 repeat input
	 */
	public static int takeSize() throws IOException {
		Scanner input = new Scanner(System.in);
		boolean passed = false, done = false;
		int size = 0;
		do {
			do {
				try {
					System.out.println("******************");
					System.out.println("Enter input size");
					System.out.print(">> ");
					size = input.nextInt();
					input.nextLine();
					done = true;
				} catch (InputMismatchException e) {
					input.nextLine();
					System.err.println("Invalid input size(1 - 1000000)");
				}
			} while (!done);

			if(size < 0 || size > 1000000) {
				System.err.println("Invalid input size(1 - 1000000)");
			} else passed = true;
		} while (!passed);

		ArrayList<Integer> arr = inputSizeArr(size);
		System.out.println("Inputs read!!");
		System.out.println("Input size(N): " + arr.size());
		System.out.println("******************");

		return size;
	}

	/**
	 * @param arr - ArrayList to implement algorithm on
	 */
	public static void quickSort(ArrayList<Integer> arr) {
//		System.out.println("QUICK SORT");
		/** code **/
	}

	/**
	 * @param integers - LinkedList to implement algorithm on.
	 */
	public static void insertionSort(LinkedList<Integer> integers) {
		int j;
		for(int i = 1; i < integers.size(); i++) {   // 0(N)
			int temp = integers.get(i);
			for(j = i; j > 0 && compare(temp, integers.get(j - 1)) < 0; j--) {  // O(N) worst case, if array is reversed
				integers.set(j, integers.get(j - 1));
			}
			integers.set(j, temp);
		}
		// worst case : 0(N^2)
	}

	/**
	 * @param arr    - ArrayList to implement algorithm on
	 * @param number - number to search for
	 * @return - if number exists in sort ArrayList return true, otherwise false.
	 */
	public static boolean binarySearch(ArrayList<Integer> arr, int number) {
//		System.out.println("BINARY SEARCH");
		/** code **/
		return true;
	}

	/**
	 * @param arr  - ArrayList to implement algorithm on
	 * @param low  - 0. It is basically the starting index of the DS.
	 * @param high - size of DS minus 1.
	 */
	public static void mergeSort(ArrayList<Integer> arr, int low, int high) {
		/** code **/
		// base case
		if(high <= low) return;

		// gets the mid of the list
		int middle = (high + low) / 2;
		// merge sort from 0 to the middle of the list
		mergeSort(arr, low, middle);
		// merge sort from the middle + 1(because you don't want the work on the middle index twice) to high
		mergeSort(arr, middle + 1, high);
		// merge this sub arrays
		mergeAl(arr, low, middle, high);
	}

	/**
	 * Called from mergeSort.
	 *
	 * @param arr    - ArrayList to implement algorithm on
	 * @param low    - 0. It is basically the starting index of the DS.
	 * @param middle - middle of the DS at instance, (low...middle...high)
	 * @param high   - size of DS minus 1.
	 */
	private static void mergeAl(ArrayList<Integer> arr, int low, int middle, int high) {
		// temp sub arrays
		int[] leftArray = new int[middle - low + 1];
		int[] rightArray = new int[high - middle];

		// fill up the arrays
		for(int i = 0; i < leftArray.length; i++)
			leftArray[i] = arr.get(low + i);

		for(int i = 0; i < rightArray.length; i++)
			rightArray[i] = arr.get(middle + i + 1);

		// Iterators containing current index of temp sub arrays
		int leftIndex = 0;
		int rightIndex = 0;

		// Copying from leftArray and rightArray back into array
		for(int i = low; i < high + 1; i++) {
			// if un copied numbers still exists in both arrays, get the smallest
			if(leftIndex < leftArray.length && rightIndex < rightArray.length) {
				/**
				 * Getting the smallest:
				 * if the left index of left array is smaller, add that else work it for right
				 */
				if(leftArray[leftIndex] < rightArray[rightIndex]) {
					arr.set(i, leftArray[leftIndex]);
					leftIndex++;
				} else {
					arr.set(i, rightArray[rightIndex]);
					rightIndex++;
				}
			} else if(leftIndex < leftArray.length) {
				// if no numbers in right array, copy the rest of left array
				arr.set(i, leftArray[leftIndex]);
				leftIndex++;
			} else if(rightIndex < rightArray.length) {
				// if no numbers in left array, copy the rest of right
				arr.set(i, rightArray[rightIndex]);
				rightIndex++;
			}
		}
	}

	/**
	 * @param inputSize Input size(N) returned by {@link #takeSize()}
	 * @return returns an array of length size filled with numbers read from @RandomText.txt inputSize number of times.
	 * @throws IOException
	 */
	public static ArrayList inputSizeArr(int inputSize) throws IOException {
		ArrayList<Integer> arrayList = new ArrayList<>(inputSize);
		FileReader fileReader = new FileReader("/Users/willz/Desktop/DS and Algorithms/Seminar1/src/RandomText.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		int counter = 1;
		String line = null;
		while (((line = bufferedReader.readLine()) != null) && counter <= inputSize) {
			arrayList.add(Integer.parseInt(line));
			counter++;
		}
		bufferedReader.close();
		return arrayList;
	}

	static class AverageRunTime {
		ArrayList<Integer> arrayList;
		ArrayList<Integer> unsortedArrayList = this.arrayList;
		// test
		Long[] testInsertionSort = new Long[11];
		Long[] testMergeSort = new Long[11];

		AverageRunTime() throws IOException {
		}

		/**
		 * @return average run time of quicksort algorithm ran 5 times.
		 */
		// TODO - parameter of number of runs from user input
		Long avgRunTimeQuicksort() {
			Long totalRunTime = 0L;
			for(int i = 0; i < 11; i++) {
				Long startTime = System.nanoTime();
				quickSort(arrayList);
				Long runTime = (System.nanoTime() - startTime);
				arrayList = unsortedArrayList;
				/**
				 * Calculating avg from the second run time because the first run time has complilation cost.
				 * So from the test array logged on the console, the first number will always be way higher than the rest
				 * Adding that high number to the Array of runs times just for obs.
				 */
				if(i >= 1) {
					totalRunTime += runTime;
				}
			}
			return totalRunTime / 10;
		}

		/**
		 * @return average run time of quicksort algorithm ran 5 times.
		 */
		Long avgRunTimeInsertionSort() throws IOException {
			Long totalRunTime = 0L;
			for(int i = 0; i < 11; i++) {
				LinkedList<Integer> integers = covertToLinkedList(arrayList);
				Long startTime = System.nanoTime();
				insertionSort(integers);
				Long runTime = (System.nanoTime() - startTime);
				arrayList = unsortedArrayList;
				testInsertionSort[i] = runTime;
				/**
				 * Calculating avg from the second run time because the first run time has complilation cost.
				 * So from the test array logged on the console, the first number will always be way higher than the rest
				 * Adding that high number to the Array of runs times just for obs.
				 */
				if(i >= 1) {
					totalRunTime += runTime;
				}
			}
			return totalRunTime / 10;
		}

		/**
		 * @return average run time of quicksort algorithm ran 5 times.
		 */
		Long avgRunTimeMergeSort() throws IOException {
			Long totalRunTime = 0L;
			for(int i = 0; i < 11; i++) {
				Long startTime = System.nanoTime();
				mergeSort(arrayList, 0, arrayList.size() - 1);
				Long runTime = (System.nanoTime() - startTime);
				arrayList = unsortedArrayList;
				testMergeSort[i] = runTime;
				/**
				 * Calculating avg from the second run time because the first run time has complilation cost.
				 * So from the test array logged on the console, the first number will always be way higher than the rest
				 * Adding that high number to the Array of runs times just for obs.
				 */
				if(i >= 1) {
					totalRunTime += runTime;

				}
			}
			return totalRunTime / 10;
		}

		/**
		 * @return average run time of quicksort algorithm ran 5 times.
		 */
		Long avgBinarySearch() throws IOException {
			Long totalRunTime = 0L;
			for(int i = 0; i < 11; i++) {
				Long startTime = System.nanoTime();
				binarySearch(arrayList, numberToFind);
				Long runTime = (System.nanoTime() - startTime);
				arrayList = unsortedArrayList;
				/**
				 * Calculating avg from the second run time because the first run time has complilation cost.
				 * So from the test array logged on the console, the first number will always be way higher than the rest.
				 * Adding that high number to the Array of runs times just for obs.
				 */
				if(i >= 1) {
					totalRunTime += runTime;
				}
			}
			return totalRunTime / 10;
		}

		/**
		 * * array and unsorted array are basically the same thing
		 * * But if we use just one ArrayList(e.g just {@code array}) object the program can end up
		 * * sorting an already sorted array.So before we use array we factory reset it with {@code unsortedArray} in each call's method(e.g {@link #avgRunTimeQuicksort()} line 248).
		 *
		 * @throws IOException - thrown by {@link #takeSize()} and {@link #inputSizeArr(int)}
		 */
		void runAll() throws IOException {
			unsortedArrayList = inputSizeArr(takeSize());
			arrayList = unsortedArrayList;
			System.out.println("Average run time - quicksort: " + avgRunTimeQuicksort() + " nanoseconds.");
			// test
			System.out.println(arrayList.size());
			System.out.println(unsortedArrayList.size());
			System.out.println("Average run time - Binary search: " + avgBinarySearch() + " nanoseconds.");
		}

		void runInsertion() throws IOException {
			unsortedArrayList = inputSizeArr(takeSize());
			arrayList = unsortedArrayList;
			System.out.println("Average run time - Insertion sort: " + avgRunTimeInsertionSort() + " nanoseconds.");
			System.out.println(Arrays.toString(testInsertionSort));
		}

		void runMergeSort() throws IOException {
			unsortedArrayList = inputSizeArr(takeSize());
			arrayList = unsortedArrayList;
			System.out.println("Average run time - Merge sort: " + avgRunTimeMergeSort() + " nanoseconds.");
			System.out.println(Arrays.toString(testMergeSort));
		}
	}
}

