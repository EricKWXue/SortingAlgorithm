package com.eric.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ��С�������򣺲�������
 * @author Eric
 * @param <E>
 *
 */
public class InsertSortUtil {
	/**
	 * ֱ�Ӳ��������㷨
	 * A[0],...,A[i-1],A[i],A[i+1],...,A[n]
	 * 1���ҵ�A[i]��A[0]��A[i-1]�еĲ���λ��k
	 * 2����A[k]��A[i-1]������Ԫ��ȫ�������ƶ�һ��λ��
	 * 3����A[i]���Ƶ�A[k]��
	 */
	public static void directInsertSort(List<Integer> list){
		for (int i = 1; i < list.size(); i++) {
		    //ȡ��������Ԫ��
			Integer currentElement = list.get(i);
			//����ǰλ�ÿ����ǿյģ�λ��Ϊk����ǰ������бȽ�
			int k = i;
			//����ǰԪ�رȽϴ�������ѭ��
			for(int j = i - 1; j >= 0 && currentElement.compareTo(list.get(j)) < 0; j--){
				//�����Ƚϵ�Ԫ��Ԫ�ش����Ԫ����ǰ�ƶ�һλ
				list.set(j + 1, list.get(j));
				//��¼��ʱ�Ŀ���λ��Ϊk
				k = j;
			}
			list.set(k, currentElement);
		}
	}
	/**
	 * �۰���������㷨
	 * 1���ҵ�A[i]��A[0]��A[i-1]�еĲ���λ��k�������۰뷽������
	 * 2����A[k]��A[i-1]������Ԫ��ȫ�������ƶ�һ��λ��
	 * 3����A[i]���Ƶ�A[k]��
	 */
	public static void halfInsertSort(List<Integer> list){
		for (int i = 1; i < list.size(); i++) {
			Integer currentElement = list.get(i);
			//�۰����k
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
			//�ҵ�k��λ�ü�Ϊhigh+1����k��i-1��Ԫ�������ƶ�
			for(int j = i - 1; j >= high + 1; j--){
				list.set(j + 1, list.get(j));
			}
			//��kλ���в���Ŀ��Ԫ��
			list.set(high + 1, currentElement);
		}
	}
	/**
	 * ȡ����d1=n/2,���в�������
	 * d2=d1/2�����в�������
	 * d3=d2/2�����в�������
	 * ....
	 * dn=1�����в�������
	 * ϣ������
	 * ��ò���е����⣬�п��ٵ�����
	 */
	public static void shellSort(List<Integer> list){
		for(int di=list.size()/2; di>=1; di=di/2){
			//ȡ����Ϊdi��������ֱ�Ӳ�������,�ڶ�������ʼ����
			for(int i=di; i<list.size(); i=i+di){
				Integer currentElement = list.get(i);
				//�ҵ�����Ϊdi����Ĳ���λ��k
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
