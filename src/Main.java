import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Runtime.getRuntime;
/* https://vinayakgarg.wordpress.com/2011/10/25/time-comparison-of-quick-sort-insertion-sort-and-bubble-sort/ */

public class Main {
	static boolean exit = false;
	static int numberToFind;
	static int[] arr = null;
	static boolean doneExec = false;

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);

		do {
			System.out.println("****************************");
			System.out.println("1) Run for each algorithm");
			System.out.println("2) Returns the avg for: ");
			System.out.println("   - Quick sort");
			System.out.println("   - Insertion sort");
			System.out.println("   - Merge sort");
			System.out.println("   - Binary Search");
			System.out.println("3) Exit");
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
							mergeSort(arr);
							Long runTimeMerge = System.nanoTime() - sTimeMerge;
							displayRunTime(runTimeMerge);
							break;
						case 3:
							System.out.println("INSERTION SORT");
							arr = inputSizeArr(takeSize());
							Long sTimeInsertionSort = System.nanoTime();
							insertionSort(arr);
							Long runTimeInsertionSort = System.nanoTime() - sTimeInsertionSort;
							displayRunTime(runTimeInsertionSort);
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
				AverageRunTime averageRunTime = new AverageRunTime();
				averageRunTime.runAll();
			} else if(choice1 == 3) {
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

	/**
	 * @return size of user input and will be used in {@link #inputSizeArr} to create array of length size.
	 * @throws IOException - invalid input
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

		int[] arr = inputSizeArr(size);
		System.out.println("Inputs read!!");
		System.out.println("Input size(N): " + arr.length);
		System.out.println("******************");

		return size;
	}

	/**
	 * @param arr array to implement algorithm on
	 */
	public static void quickSort(int[] arr) {
//		System.out.println("QUICK SORT");
		/** code **/
	}

	/**
	 * @param arr array to implement algorithm on
	 */
	public static void insertionSort(int[] arr) {
//		System.out.println("INSERTION SORT");
		/** code **/

	}

	/**
	 * @param arr array to implement algorithm on
	 */
	public static boolean binarySearch(int[] arr, int number) {
//		System.out.println("BINARY SEARCH");
		/** code **/
		return true;
	}

	/**
	 * @param arr array to implement algorithm on
	 */
	public static void mergeSort(int[] arr) {
//		System.out.println("MERGE SORT");
		/** code **/
	}

	/**
	 * @param inputSize Input size(N) returned by {@link #takeSize()}
	 * @return returns an array on length size filled with numbers read from @RandomText.txt inputSize number of times.
	 * @throws IOException
	 */
	public static int[] inputSizeArr(int inputSize) throws IOException {
		int[] arr = new int[inputSize];
		FileReader fileReader = new FileReader("/Users/willz/Desktop/DS and Algorithms/Seminar1/src/RandomText.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		int counter = 1;
		String line = null;
		while (((line = bufferedReader.readLine()) != null) && counter <= inputSize) {
			arr[counter - 1] = Integer.parseInt(line);
			counter++;
		}
		bufferedReader.close();
		return arr;
	}

	static class AverageRunTime {
		int[] array;
		int[] unsortedArray = this.array;
		// test
		Long[] test = new Long[5];


		AverageRunTime() throws IOException {
		}

		/**
		 * @return average run time of quicksort algorithm ran 5 times.
		 */
		Long avgRunTimeQuicksort() {
			Long totalRunTime = 0L;
			for(int i = 0; i < 5; i++) {
				Long startTime = System.nanoTime();
				quickSort(array);
				Long runTime = (System.nanoTime() - startTime);
				array = unsortedArray;
				// SPOT
				test[i] = runTime;
				totalRunTime += runTime;
			}
			return totalRunTime / 5;
		}

		/**
		 * @return average run time of quicksort algorithm ran 5 times.
		 */
		Long avgRunTimeInsertionSort() throws IOException {
			Long totalRunTime = 0L;
			for(int i = 0; i < 5; i++) {
				Long startTime = System.nanoTime();
				insertionSort(array);
				Long runTime = (System.nanoTime() - startTime);
				array = unsortedArray;
				totalRunTime += runTime;
			}
			return totalRunTime / 5;
		}

		/**
		 * @return average run time of quicksort algorithm ran 5 times.
		 */
		Long avgRunTimeMergeSort() throws IOException {
			Long totalRunTime = 0L;
			for(int i = 0; i < 5; i++) {
				Long startTime = System.nanoTime();
				mergeSort(array);
				Long runTime = (System.nanoTime() - startTime);
				array = unsortedArray;
				totalRunTime += runTime;
			}
			return totalRunTime / 5;
		}

		/**
		 * @return average run time of quicksort algorithm ran 5 times.
		 */
		Long avgBinarySearch() throws IOException {
			Long totalRunTime = 0L;
			for(int i = 0; i < 5; i++) {
				Long startTime = System.nanoTime();
				binarySearch(array, numberToFind);
				Long runTime = (System.nanoTime() - startTime);
				array = unsortedArray;
				totalRunTime += runTime;
			}
			return totalRunTime / 5;
		}

		/**
		 * * array and unsorted array are basically the same thing
		 * * But if we use just one array(e.g just (#array)) object the program can end up
		 * * sorting an already sorted array.So before we use array we factory reset it with unsortedArray in each call's method(e.g {@link #avgRunTimeQuicksort()} line 248).
		 *
		 * @throws IOException - thrown by {@link #takeSize()} and {@link #inputSizeArr(int)}
		 */
		void runAll() throws IOException {

			unsortedArray = inputSizeArr(takeSize());
			array = unsortedArray;
			System.out.println("Average run time - quicksort: " + avgRunTimeQuicksort() + " nanoseconds.");
			// SPOT
			System.out.println(array.length);
			System.out.println(unsortedArray.length);
			System.out.println(Arrays.toString(test));
			System.out.println("Average run time - Insertion sort: " + avgRunTimeInsertionSort() + " nanoseconds.");
			System.out.println("Average run time - Merge sort: " + avgRunTimeMergeSort() + " nanoseconds.");
			System.out.println("Average run time - Binary search: " + avgBinarySearch() + " nanoseconds.");
		}


	}


}

