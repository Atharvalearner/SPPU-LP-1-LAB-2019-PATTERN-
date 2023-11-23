import java.util.*;
class Item {
    int weight, value;
    Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}
public class KnapsackGreedy {
    public static double getMaxValue(Item[] items, int capacity) {
        Arrays.sort(items, Comparator.comparingDouble(item -> (double) item.value / item.weight));
        double maxValue = 0;
        int currWeight = 0;
        for (Item item : items) {
            if (currWeight + item.weight <= capacity) {
                currWeight += item.weight;
                maxValue += item.value;
            } else {
                int remainCap = capacity - currWeight;
                maxValue += (double) item.value * remainCap / item.weight;
                break;
            }
        }
        return maxValue;
    }
    public static void main(String[] args) {
		Item[] items = {new Item(2, 10), new Item(3, 5), new Item(5, 15), new Item(7, 7), new Item(1, 6)};
		int capacity = 15;
        double result = getMaxValue(items, capacity);
        System.out.println("Maximum value in Knapsack = " + result);
    }
}