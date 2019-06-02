package com.eric.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 选择排序
 * @author Eric
 *
 */
public class SelectSortUtil {

	/**
	 * 简单选择排序算法
	 * A[0]与A[1]~A[n-1]比较，minA[i]与A[0]交换
	 * A[1]与A[2]~A[n-1]比较，minA[i]与A[1]交换
	 * 。。。
	 * A[n-2]与A[n-1]比较，若A[n-1]小，则交换
	 * 
	 * 每一趟排序，都有一个元素在最终位置上
	 */
	public static void simpleSelectSort(List<Integer> list){
		for(int i = 0; i < list.size(); i++){
			//记录最小元素的下标
			int minIndex = i;
			for(int j=i+1; j <list.size(); j++){
				if(list.get(minIndex).compareTo(list.get(j)) > 0){
					minIndex = j;
				}
			}
			//若不相等，则交换
			if(minIndex != i){
				Integer tmpElement = list.get(i);
				list.set(i, list.get(minIndex));
				list.set(minIndex, tmpElement);
			}
		}
	}
	
	/**
	 * 堆排序
	 * 1、建立堆，大根堆（或者小根堆）
	 * 2、输出根节点，将堆底元素送入堆顶
	 * 3、将剩余的n-1个元素向下调整，重新生成大根堆。以此类推
	 */
	public static void heapSort(List<Integer> list){
		builMaxHeap(list);
		System.out.println("heap list:" + list);
		
		//逐个输出堆顶元素，将堆底元素放在堆顶，向下调整
		for(int i=list.size(); i>1; i--){
			//将堆顶的元素放在最后
			Integer tmpElement = list.get(0);
			list.set(0, list.get(i-1));
			list.set(i-1, tmpElement);
			
			//对前面1~n-1的二叉树重新向下调整
			adjustDown(list, 1, i-1);
			System.out.println("heap"+ (list.size() - i + 1) +" list:" + list);
		}
	}
	
	/**
	 * 建立大根堆
	 * @param list
	 */
	private static void builMaxHeap(List<Integer> list){
		//从最底层的子二叉树开始调整,树的根节点以1开始计算
		for(int i=list.size()/2; i>0; i--){
			adjustDown(list, i);
		}
	}
	/**
	 * 调整二叉树，形成大根堆
	 * @param list
	 * @param k
	 */
	private static void adjustDown(List<Integer> list, int k){
		adjustDown(list, k, list.size());
	}
	
	/**
	 * 以k为根节点，向下调整。
	 * 因为是二叉树，k最小是1,对应的数据存储在k-1位置
	 * @param list
	 * @param k
	 */
	private static void adjustDown(List<Integer> list, int k, int size){
		//比较k-1，2k-1，2k子树，调整为大根堆
		Integer tmpElement = list.get(k-1);
		
		//左孩子所在的节点位置
		int i = 2*k - 1;
		//若没有左孩子,即元素所在位置，超出了树的最后一个节点的位置
		if(i > size - 1){
			return;
		}
		
		//判断右孩子节点是否存在;若存在，且右孩子比较大的
		if(2*k+1 <= size && list.get(2*k - 1).compareTo(list.get(2*k)) < 0){
			//右孩子所在的节点
			i = 2*k;
		}
		//若孩子节点比较大，则与根节点交换
		if(tmpElement.compareTo(list.get(i)) < 0){
			list.set(k-1, list.get(i));
			list.set(i, tmpElement);
		}
		
		//对下一个子二叉树进行调整
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
