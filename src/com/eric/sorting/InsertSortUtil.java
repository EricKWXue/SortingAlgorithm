package com.eric.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 从小到大排序：插入排序
 * @author Eric
 * @param <E>
 *
 */
public class InsertSortUtil {
	/**
	 * 直接插入排序算法
	 * A[0],...,A[i-1],A[i],A[i+1],...,A[n]
	 * 1）找到A[i]在A[0]到A[i-1]中的插入位置k
	 * 2）将A[k]到A[i-1]的所有元素全部往后移动一个位置
	 * 3）将A[i]复制到A[k]中
	 */
	public static void directInsertSort(List<Integer> list){
		for (int i = 1; i < list.size(); i++) {
		    //取出待排序元素
			Integer currentElement = list.get(i);
			//将当前位置看作是空的，位置为k。与前面的序列比较
			int k = i;
			//若当前元素比较大，则跳出循环
			for(int j = i - 1; j >= 0 && currentElement.compareTo(list.get(j)) < 0; j--){
				//若被比较的元素元素大，则该元素向前移动一位
				list.set(j + 1, list.get(j));
				//记录此时的空闲位置为k
				k = j;
			}
			list.set(k, currentElement);
		}
	}
	/**
	 * 折半插入排序算法
	 * 1）找到A[i]在A[0]到A[i-1]中的插入位置k，采用折半方法查找
	 * 2）将A[k]到A[i-1]的所有元素全部往后移动一个位置
	 * 3）将A[i]复制到A[k]中
	 */
	public static void halfInsertSort(List<Integer> list){
		for (int i = 1; i < list.size(); i++) {
			Integer currentElement = list.get(i);
			//折半查找k
			int low = 0;
			int high = i - 1;
			while(low <= high){
				int mid = (low + high) / 2;
				if(currentElement.compareTo(list.get(mid)) < 0){
					high = mid - 1;
				}else{
					low = mid + 1;
				}	
			}
			//找到k的位置即为high+1，将k到i-1的元素往后移动
			for(int j = i - 1; j >= high + 1; j--){
				list.set(j + 1, list.get(j));
			}
			//在k位置中插入目标元素
			list.set(high + 1, currentElement);
		}
	}
	/**
	 * 取步长d1=n/2,进行插入排序
	 * d2=d1/2，进行插入排序
	 * d3=d2/2，进行插入排序
	 * ....
	 * dn=1，进行插入排序
	 * 希尔排序
	 * （貌似有点问题，有空再调整）
	 */
	public static void shellSort(List<Integer> list){
		for(int di=list.size()/2; di>=1; di=di/2){
			//取步长为di的数进行直接插入排序,第二个数开始遍历
			for(int i=di; i<list.size(); i=i+di){
				Integer currentElement = list.get(i);
				//找到步骤为di排序的插入位置k
				int k = i;
				for(int j = i - di; j >= 0 && currentElement.compareTo(list.get(j)) < 0; j = j - di){
                    list.set(j + di, list.get(j));
                    k = j;
				}
				list.set(k, currentElement);
			}
			System.out.println("di="+ di +", list:" + list);
		}
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			list.add(random.nextInt(100));
		}
		
		System.out.println("original list:" + list);
		//directInsertSort(list);
		//halfInsertSort(list);
		shellSort(list);
		System.out.println("sorted list:" + list);
	}

}
