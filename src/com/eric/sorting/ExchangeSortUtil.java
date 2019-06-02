package com.eric.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ��С�������򣬽�������
 * @author Eric
 *
 */
public class ExchangeSortUtil {
	
	/**
	 * ð������
	 * ��һ�ˣ��Ӻ���ǰ,�����Ƚ�����Ԫ�ص�ֵ��������A[i-1] > A[i]�����򽻻�ֱ�����бȽ��ꡣ��ʱA[0]Ԫ��������λ����
	 * �ڶ��ˣ���С��Ԫ�ز��ٱȽϣ����������м���һ��Ԫ�أ��ظ�����1����ʱA[1]Ԫ��������λ����
	 * ��n-1�ˣ������������ʱA[n-2]��A[n-1]Ԫ��������λ����
	 * @param list
	 */
	public static void bubbleSort(List<Integer> list){
		//��Ҫ����n-1��
		for(int i=0; i<list.size()-1; i++){
			//�Ƿ���������־λ
			boolean isExchange = false;
			//�Ӻ���ǰ�����Ƚϣ�����˳��
			for(int j=list.size()-1; j>i; j--){
				//A[i-1] > A[i], �򽻻�
				if(list.get(j-1).compareTo(list.get(j)) > 0){
					Integer tmpElement = list.get(j);
					list.set(j, list.get(j-1));
					list.set(j-1, tmpElement);
					isExchange = true;
				}
			}
			//�����һ��û�з�����������˵���������Ѿ�����
			if(!isExchange){
				break;
			}
		}
	}
	
	/**
	 * ��������
	 * �ڴ�������������ȡһ��Ԫ��pivot��Ϊ��׼��ͨ��һ�����򽫴��������л���Ϊ������������A[0]~A[k-1]��A[k+1]~A[n-1]��ʹ��A
	 * [k]����A[0]~A[k-1]����A[k]С��A[k+1]~A[n-1]����pivot����������λ��A[k]�ϡ�
	 * 
	 * ����ֱ�ݹ�ض������������ظ�����Ĺ��̣�ֱ��ÿ����ֻ��һ��Ԫ�ػ��߿�Ϊֹ��
	 * 
	 * @param list
	 */
	public static void quickSort(List<Integer> list){
		quickSort(list, 0, list.size() - 1);
	}
	
	/**
	 * ���ڿ�������ĵݹ��㷨
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
	 * ��������Ļ����㷨�����������е�k
	 * �����նԿ����������⣬�Լ�д�ı�����=.=��
	 * @deprecated 
	 */
	/*private static int partition(List<Integer> list, int low, int high){
		//���Ƚϵ�Ԫ��
		Integer currentElement = list.get(low);
		//��󷵻ص�λ��k
		int k = low;
		//�Ƿ�Ӻ���ǰ�ƽ��ı�־λ��Forward or Backward
		boolean isBackward = true;
		//��low+1 �� high���������м�ƽ����Ӻ���ǰ�ƽ�һ�Σ����ߴ�ǰ����ƽ�һ�Σ���Ϊһ�ˡ�
		low ++;
		while(low <= high){
			if(isBackward){
				//�Ӻ���ǰ�ƽ�
				if(list.get(high).compareTo(currentElement) < 0){
					//��ֵ����k��λ����
					list.set(k, list.get(high));
					k = high;
					isBackward = false;
				}
				high --;
			}else{
				//��ǰ����ƽ�
				if(list.get(low).compareTo(currentElement) > 0){
					//��ֵ����k��λ����
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
	 * ��������Ļ����㷨�����������е�k
	 * ���̿���ʽ�ķ�����
	 * @return
	 */
	private static int partition(List<Integer> list, int low, int high){
		//���Ƚϵ�Ԫ��
		Integer currentElement = list.get(low);
		//�Ӻ���ǰ�ƽ���Σ��ٴ�ǰ����ƽ���Σ���Ϊһ�ˡ�
		while(low < high){
			//�ȴӺ���ǰ�ƽ�
			while(low < high && list.get(high).compareTo(currentElement) >= 0){
				high --;
			}
			//�������ĳ��Ԫ��С�ڱ��Ƚϵ�Ԫ�أ���ֵ����low��λ����
			list.set(low, list.get(high));
			
			//�ٴ�ǰ����ƽ�
			while(low < high && list.get(low).compareTo(currentElement) <= 0){
				low ++;
			}
			//��ǰ���ĳ��Ԫ�ش��ڱ��Ƚϵ�Ԫ�أ���ֵ�ŵ�high��λ����
			list.set(high, list.get(low));
		}
		//���ճ�������Զ��lowλ��
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
