package com.eric.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * �鲢����
 * @author Eric
 *
 */
public class MergeSortUtil {

	/**
	 * �鲢����
	 */
	public static void mergeSort(List<Integer> list){
		mergeSort(list, 0, list.size() - 1);
	}
	/**
	 * �ݹ鴦�������鲢������
	 * @param list
	 * @param low
	 * @param high
	 */
	private static void mergeSort(List<Integer> list, int low, int high){
		if(low < high){
			int mid = (low + high)/2;
			//����������
			mergeSort(list, low, mid);
			//����������
			mergeSort(list, mid+1, high);
			//�ϲ���������
			merge(list, low, mid, high);
		}
	}
	
	/**
	 * �ϲ��������������
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
		//�������±�
		int k = low;
		while(i <= mid && j <= high){
			if(tmpList.get(i).compareTo(tmpList.get(j)) < 0){
				list.set(k, tmpList.get(i));
				i++;
			}else{
				list.set(k, tmpList.get(j));
				j++;
			}
			//�±��һλ
			k++;
		}
		//��ʣ�µ�û�Ƚϵ�����ֱ�ӿ������ں���
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
