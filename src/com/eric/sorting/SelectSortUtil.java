package com.eric.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ѡ������
 * @author Eric
 *
 */
public class SelectSortUtil {

	/**
	 * ��ѡ�������㷨
	 * A[0]��A[1]~A[n-1]�Ƚϣ�minA[i]��A[0]����
	 * A[1]��A[2]~A[n-1]�Ƚϣ�minA[i]��A[1]����
	 * ������
	 * A[n-2]��A[n-1]�Ƚϣ���A[n-1]С���򽻻�
	 * 
	 * ÿһ�����򣬶���һ��Ԫ��������λ����
	 */
	public static void simpleSelectSort(List<Integer> list){
		for(int i = 0; i < list.size(); i++){
			//��¼��СԪ�ص��±�
			int minIndex = i;
			for(int j=i+1; j <list.size(); j++){
				if(list.get(minIndex).compareTo(list.get(j)) > 0){
					minIndex = j;
				}
			}
			//������ȣ��򽻻�
			if(minIndex != i){
				Integer tmpElement = list.get(i);
				list.set(i, list.get(minIndex));
				list.set(minIndex, tmpElement);
			}
		}
	}
	
	/**
	 * ������
	 * 1�������ѣ�����ѣ�����С���ѣ�
	 * 2��������ڵ㣬���ѵ�Ԫ������Ѷ�
	 * 3����ʣ���n-1��Ԫ�����µ������������ɴ���ѡ��Դ�����
	 */
	public static void heapSort(List<Integer> list){
		builMaxHeap(list);
		System.out.println("heap list:" + list);
		
		//�������Ѷ�Ԫ�أ����ѵ�Ԫ�ط��ڶѶ������µ���
		for(int i=list.size(); i>1; i--){
			//���Ѷ���Ԫ�ط������
			Integer tmpElement = list.get(0);
			list.set(0, list.get(i-1));
			list.set(i-1, tmpElement);
			
			//��ǰ��1~n-1�Ķ������������µ���
			adjustDown(list, 1, i-1);
			System.out.println("heap"+ (list.size() - i + 1) +" list:" + list);
		}
	}
	
	/**
	 * ���������
	 * @param list
	 */
	private static void builMaxHeap(List<Integer> list){
		//����ײ���Ӷ�������ʼ����,���ĸ��ڵ���1��ʼ����
		for(int i=list.size()/2; i>0; i--){
			adjustDown(list, i);
		}
	}
	/**
	 * �������������γɴ����
	 * @param list
	 * @param k
	 */
	private static void adjustDown(List<Integer> list, int k){
		adjustDown(list, k, list.size());
	}
	
	/**
	 * ��kΪ���ڵ㣬���µ�����
	 * ��Ϊ�Ƕ�������k��С��1,��Ӧ�����ݴ洢��k-1λ��
	 * @param list
	 * @param k
	 */
	private static void adjustDown(List<Integer> list, int k, int size){
		//�Ƚ�k-1��2k-1��2k����������Ϊ�����
		Integer tmpElement = list.get(k-1);
		
		//�������ڵĽڵ�λ��
		int i = 2*k - 1;
		//��û������,��Ԫ������λ�ã��������������һ���ڵ��λ��
		if(i > size - 1){
			return;
		}
		
		//�ж��Һ��ӽڵ��Ƿ����;�����ڣ����Һ��ӱȽϴ��
		if(2*k+1 <= size && list.get(2*k - 1).compareTo(list.get(2*k)) < 0){
			//�Һ������ڵĽڵ�
			i = 2*k;
		}
		//�����ӽڵ�Ƚϴ�������ڵ㽻��
		if(tmpElement.compareTo(list.get(i)) < 0){
			list.set(k-1, list.get(i));
			list.set(i, tmpElement);
		}
		
		//����һ���Ӷ��������е���
		if(2*(i+1) <= size){
			adjustDown(list, i+1, size);
		}
	}
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			list.add(random.nextInt(100));
		}
		
		System.out.println("original list:" + list);
		heapSort(list);
		//simpleSelectSort(list);
		System.out.println("sorted list:" + list);

	}

}
