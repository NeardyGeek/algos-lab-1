// You have a heist getaway sack with a capacity, and n items in front of you
// with sizes and worths. Figure out the maximum value you could
// get with the items.

// You are encouraged to make helper functions!

public class Robbery {

	// Using DP: Get the maximum value with capacity C and n items
	public int maximizeRobWorthRecur(
		int capacity,
		int[] sizes,
		int[] worths,
		int quantity
	) {
		// fill in here, change the return
		
		
		if(quantity==0 || capacity==0) {
			return 0;
		}
		
		if(sizes[quantity-1]>capacity) {
			return maximizeRobWorthRecur(capacity, sizes, worths, quantity-1);
		
		}else {
			return Math.max(worths[quantity-1]+maximizeRobWorthRecur(capacity-sizes[quantity-1],sizes,
					worths, quantity-1),maximizeRobWorthRecur(capacity, sizes, worths, quantity-1));
		}
		  
		
	}

	public int maximizeRobWorthBottomUp(
		int capacity,
		int[] sizes,
		int[] worths
	) {
		// fill in here, change the return
		
		int quantity = sizes.length;
		
		int[][] value = new int [quantity+1][capacity+1];
		
		for(int col=0; col<=capacity; col++) {
			value[0][col]=0;
		}
		
		for(int row=0; row<=quantity; row++) {
			value[row][0]=0;
		}
		
		for(int item=1; item<=quantity; item++) {
			for(int size=1; size<=capacity; size++) {
				if(sizes[item-1]<=size) {
					value[item][size]= Math.max(worths[item-1]+value[item-1][size-sizes[item-1]], value[item-1][size]);
				}else {
					value[item][size]=value[item-1][size];
				}
			}
		}
		
		
		return value[quantity][capacity];
	}

/**
* Bonus: figure out which items exactly
* Takes in a DP DPTable
* Returns an int array of the individual worths of the items you took
*/
	public int[] takeRobInventory(int[][] DPTable) {
		// fill in here, change the return
		return new int[DPTable.length];
	}

	public static void main(String[] args) {
		Robbery r = new Robbery();
		int bagCapacity = 40;
		int[] itemSizes = {2, 25, 6, 13, 1, 15, 8, 5, 17, 4};
		int[] itemWorths = {35, 120, 900, 344, 29, 64, 67, 95, 33, 10};
		int quantity= itemSizes.length;
		
		int maxWorthRecur = r.maximizeRobWorthRecur(bagCapacity, itemSizes, itemWorths, quantity);
		System.out.println("Max worth of the bag: " + maxWorthRecur);
		
		int maxWorthBottomUp = r.maximizeRobWorthBottomUp(bagCapacity, itemSizes, itemWorths);
		System.out.println("Max worth of the bag: " + maxWorthBottomUp);

		// Bonus: Fill in the helper method takeRobInventory that could help you
		//figure out which items go into the bag that make it max worth. Feel free
		//to change up the parameters and returned types + values of the helper
		// methods above.
		// int[] itemsPicked = r.takeRobInventory();
		// System.out.println("The items we take are worth:")
		// for (int i = 0; i < results.length; i++) {
		// 	System.out.print(results[i] + " ");
	}
}
