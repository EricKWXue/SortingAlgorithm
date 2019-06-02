package com.eric.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 归并排序
 * @author Eric
 *
 */
public class MergeSortUtil {

	/**
	 * 归并排序
	 */
	public static void mergeSort(List<Integer> list){
		mergeSort(list, 0, list.size() - 1);
	}
	/**
	 * 递归处理两两归并排序结果
	 * @param list
	 * @param low
	 * @param high
	 */
	private static void mergeSort(List<Integer> list, int low, int high){
		if(low < high){
			int mid = (low + high)/2;
			//左序列排序
			mergeSort(list, low, mid);
			//有序列排序
			mergeSort(list, mid+1, high);
			//合并所有序列
			merge(list, low, mid, high);
		}
	}
	
	/**
	 * 合并两个有序的序列
	 * @param list
	 * @param low
	 * @param mid
	 * @param high
	 */
	private static void merge(List<Integer> list, int low, int mid, int high){
		List<Integer> tmpList = new ArrayList<>();
		for (Integer element : list) {
			tmpList.add(element);
		}
		int i = low;
		int j = mid + 1;
		//新序列下标
		int k = low;
		while(i <= mid && j <= high){
			if(tmpList.get(i).compareTo(tmpList.get(j)) < 0){
				list.set(k, tmpList.get(i));
				i++;
			}else{
				list.set(k, tmpList.get(j));
				j++;
			}
			//下标进一位
			k++;
		}
		//将剩下的没比较的数据直接拷贝接在后面
		while(i <= mid){
			list.set(k, tmpList.get(i));
			i++;
			k++;
		}
		while(j <= high){
			list.set(k, tmpList.get(j));
			j++;
			k++;
		}
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			list.add(random.nextInt(100));
		}
		
		System.out.println("original list:" + list);
		mergeSort(list);
		System.out.println("sorted list:" + list);

	}

}
