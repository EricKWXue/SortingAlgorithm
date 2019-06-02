package com.eric.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 从小到大排序，交换排序
 * @author Eric
 *
 */
public class ExchangeSortUtil {
	
	/**
	 * 冒泡排序
	 * 第一趟：从后往前,两两比较相邻元素的值，若逆序（A[i-1] > A[i]），则交换直到序列比较完。此时A[0]元素在最终位置上
	 * 第二趟：最小的元素不再比较，待排序序列减少一个元素，重复步骤1，此时A[1]元素在最终位置上
	 * 第n-1趟：排序结束，此时A[n-2]、A[n-1]元素在最终位置上
	 * @param list
	 */
	public static void bubbleSort(List<Integer> list){
		//需要排序n-1趟
		for(int i=0; i<list.size()-1; i++){
			//是否发生交换标志位
			boolean isExchange = false;
			//从后往前两两比较，交换顺序
			for(int j=list.size()-1; j>i; j--){
				//A[i-1] > A[i], 则交换
				if(list.get(j-1).compareTo(list.get(j)) > 0){
					Integer tmpElement = list.get(j);
					list.set(j, list.get(j-1));
					list.set(j-1, tmpElement);
					isExchange = true;
				}
			}
			//如果这一趟没有发生交换，则说明该序列已经有序
			if(!isExchange){
				break;
			}
		}
	}
	
	/**
	 * 快速排序
	 * 在待排序序列中任取一个元素pivot作为基准，通过一趟排序将待排序序列划分为独立的两部分A[0]~A[k-1]和A[k+1]~A[n-1]，使得A
	 * [k]大于A[0]~A[k-1]，且A[k]小于A[k+1]~A[n-1]，则pivot放在了最终位置A[k]上。
	 * 
	 * 而后分别递归地对两个子序列重复上面的过程，直到每部分只有一个元素或者空为止。
	 * 
	 * @param list
	 */
	public static void quickSort(List<Integer> list){
		quickSort(list, 0, list.size() - 1);
	}
	
	/**
	 * 用于快速排序的递归算法
	 * @param list
	 * @param low
	 * @param high
	 */
	private static void quickSort(List<Integer> list, int low, int high){
		if(low < high){
			int k = partition(list, low, high);
			quickSort(list, low, k-1);
			quickSort(list, k+1, high);
		}
	}
	
	/**
	 * 快速排序的划分算法，返回上述中的k
	 * （按照对快速排序的理解，自己写的笨方法=.=）
	 * @deprecated 
	 */
	/*private static int partition(List<Integer> list, int low, int high){
		//被比较的元素
		Integer currentElement = list.get(low);
		//最后返回的位置k
		int k = low;
		//是否从后往前逼近的标志位，Forward or Backward
		boolean isBackward = true;
		//从low+1 和 high两端逐渐往中间逼近。从后往前逼近一次，或者从前往后逼近一次，视为一趟。
		low ++;
		while(low <= high){
			if(isBackward){
				//从后往前逼近
				if(list.get(high).compareTo(currentElement) < 0){
					//将值放在k的位置上
					list.set(k, list.get(high));
					k = high;
					isBackward = false;
				}
				high --;
			}else{
				//从前往后逼近
				if(list.get(low).compareTo(currentElement) > 0){
					//将值放在k的位置上
					list.set(k, list.get(low));
					k = low;
					isBackward = true;
				}
				low ++;
			}
		}
		list.set(k, currentElement);
		return k;
	}*/
	
	/**
	 * 快速排序的划分算法，返回上述中的k
	 * （教科书式的方法）
	 * @return
	 */
	private static int partition(List<Integer> list, int low, int high){
		//被比较的元素
		Integer currentElement = list.get(low);
		//从后往前逼近多次，再从前往后逼近多次，视为一趟。
		while(low < high){
			//先从后往前逼近
			while(low < high && list.get(high).compareTo(currentElement) >= 0){
				high --;
			}
			//若后面的某个元素小于被比较的元素，则将值放在low的位置上
			list.set(low, list.get(high));
			
			//再从前往后逼近
			while(low < high && list.get(low).compareTo(currentElement) <= 0){
				low ++;
			}
			//若前面的某个元素大于被比较的元素，则将值放到high的位置上
			list.set(high, list.get(low));
		}
		//最后空出来的永远是low位置
		list.set(low, currentElement);
		return low;
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			list.add(random.nextInt(100));
		}
		
		System.out.println("original list:" + list);
		//bubbleSort(list);
		quickSort(list);
		System.out.println("sorted list:" + list);

	}

}
