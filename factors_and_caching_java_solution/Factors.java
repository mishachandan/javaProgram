	import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class Factors {

	final static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	static int size = 100;

	public static void main(String[] args) {
		 int input[] = { 20, 10, 40, 2, 5 };
		// int input[] = { 2,5, 20, 10, 40 };
		// int input[] = { 40, 20, 10, 5, 2, 14, 7,23 };
		//int input[] = { 40, 20, 10, 5, 2, 14, 7,23 , 100, 64};
		/*int input[] = { 40, 20, 10, 5, 2, 14, 7,23 , 100, 64,
						14, 21, 32, 64, 102, 86, 70,  128, 35, 90,
						1092, 72, 38, 115 , 80, 15, 30, 8, 50, 48};*/
		
		
		// withoutCaching(input);

		
		  Long start = System.currentTimeMillis();
		  
		  withCaching(start,CountingSort.counting_sort(input));
		 
		//withoutCaching(generateRandomArray());

		//Long start = System.currentTimeMillis();
		//withCaching(start, CountingSort.counting_sort(generateRandomArray()));
	}

	/**
	 * This function simply stores factors in collection and displays it.
	 * 
	 * @param input
	 */
	public static void withoutCaching(int input[]) {

		Long start = System.currentTimeMillis();

		HashMap<FactKey, List<Integer>> cachingMap = new HashMap<FactKey, List<Integer>>();
		for (int i = 0; i < input.length; i++) {
			List<Integer> facts = new ArrayList<Integer>();
			for (int j = 0; j < input.length; j++) {
				if (i != j) {
					// System.out.println("---> "+i+" "+j+" "+input[i]+" "+input[j]);
					if (input[i] % input[j] == 0) {
						facts.add(input[j]);
					}
				}
			}
			cachingMap.put(new FactKey(input[i]), facts);

		}

		System.out.println("Time to execute without Caching: " + (System.currentTimeMillis() - start)
				+ " millisecond/s");

		System.out.println("-----------------------------------------------------------");

		Set set = cachingMap.entrySet();
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Entry me = (Entry) i.next();
			System.out.print(((FactKey) me.getKey()).getKey() + ": ");
			System.out.println(me.getValue());
		}

	}

	public static void withCaching(long start, int input[]) {

		// Long start = System.currentTimeMillis();

		Map<FactKey, List<Integer>> cachingTreeMap = new TreeMap<FactKey, List<Integer>>(new MyComparator());

		for (int i = 0; i < input.length; i++) {

			List<Integer> facts = new ArrayList<Integer>();

			if (cachingTreeMap.size() == 0) { // first entry in TreeMap
				for (int j = 0; j < input.length; j++) {
					if (i != j) {

						if (input[i] % input[j] == 0) {
							facts.add(input[j]);
						}
					}
				}

			} else { // something in Cache

				Set set = cachingTreeMap.entrySet();
				Iterator treeIter = set.iterator();
				boolean flag = false;
				while (treeIter.hasNext()) {
					Entry me = (Entry) treeIter.next();
					int treeKey = ((FactKey) me.getKey()).getKey();

					// System.out.println("--- ---" + input[i] + "--" + treeKey
					// + "--- " + (input[i] % treeKey));

					if (treeKey % input[i] == 0) {

						List targetList = (List) me.getValue();
						List requiredList = targetList.subList(targetList.indexOf(input[i]) + 1, targetList.size());
						facts.addAll(requiredList);
						flag = true;
						break;
					}
				}

				if (flag == false) {
					for (int j = 0; j < input.length; j++) {
						if (i != j) {

							if (input[i] % input[j] == 0) {
								facts.add(input[j]);
							}
						}
					}
				}

			}
			// displayList(facts);
			cachingTreeMap.put(new FactKey(input[i]), facts);

		}

		System.out.println("Time to execute with Caching: " + (System.currentTimeMillis() - start) + " millisecond/s");
		System.out.println("-----------------------------------------------------------");

		System.out.println("---------------------------");
		Set set = cachingTreeMap.entrySet();
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Entry me = (Entry) i.next();
			System.out.print(((FactKey) me.getKey()).getKey() + ": ");
			// System.out.println(((List) me.getValue()).size());
			displayList((List) me.getValue());
		}

	}

	private static void displayList(List<Integer> list) {
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Integer integer = (Integer) iterator.next();
			System.out.print("\t" + integer);
		}
		System.out.println();
	}

	private static void displayTime() {
		Calendar now = Calendar.getInstance();
		System.out.println(dateFormat.format(now.getTime()));
	}

	private static int[] generateRandomArray() {
		int[] input = new int[size];

		Random generator = new Random();

		for (int i = 0; i <= (size - 1); i++) {
			int num = generator.nextInt((size * 5));

			if (num != 0)
				input[i] = num;
			else
				input[i] = 2;

		}

		return input;
	}
}
