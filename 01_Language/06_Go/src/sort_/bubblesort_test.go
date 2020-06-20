package sort_

import (
	"fmt"
	"testing"
)

func TestBubbleSort(t *testing.T) {
	var arr = [5]int{24, 69, 80, 57, 13}
	BubbleSort(&arr)
}

func BubbleSort(arr *[5]int) {
	fmt.Println("排序前arr=", *arr)

	for i := 0; i < len(*arr)-1; i++ {
		for j := 0; j < len(*arr)-1-i; j++ {
			if (*arr)[j] > (*arr)[j+1] {
				var temp = (*arr)[j]
				(*arr)[j] = (*arr)[j+1]
				(*arr)[j+1] = temp
			}
		}
	}

	fmt.Println("排序后arr=", *arr)
}
