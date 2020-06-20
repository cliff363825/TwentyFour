package sort_

import (
	"fmt"
	"testing"
)

func TestFind(t *testing.T) {
	Find("金毛狮王")
}

func Find(heroName string) {
	var names = [...]string{"白眉鹰王", "金毛狮王", "紫衫龙王", "青翼蝠王"}
	//var heroName string
	//fmt.Println("请输入要查找的人名")
	//fmt.Scanln(&heroName)

	// 顺序查找 第一种方式
	for i := 0; i < len(names); i++ {
		if heroName == names[i] {
			fmt.Printf("names[%v]=%v\n", i, names[i])
			break
		}
		if i == (len(names) - 1) {
			fmt.Printf("没有找到%v\n", heroName)
		}
	}

	// 顺序查找 第二种方式
	var index = -1
	for i := 0; i < len(names); i++ {
		if heroName == names[i] {
			index = i
			break
		}
	}
	if index == -1 {
		fmt.Printf("没有找到%v\n", heroName)
	} else {
		fmt.Printf("names[%v]=%v\n", index, names[index])
	}
}

func TestBinaryFind(t *testing.T) {
	var arr = [6]int{1, 8, 10, 89, 1000, 1234}
	BinaryFind(&arr, 0, len(arr)-1, 1000)
}

func BinaryFind(arr *[6]int, leftIndex int, rightIndex int, findVal int) {
	// 判断 leftIndex 是否大于 rightIndex
	if leftIndex > rightIndex {
		fmt.Println("找不到")
		return
	}

	// 先找到中间的下标
	var middle = (leftIndex + rightIndex) / 2
	if (*arr)[middle] > findVal {
		// 说明我们要查找的数，应该在 leftIndex ~ middle-1
		BinaryFind(arr, leftIndex, middle-1, findVal)
	} else if (*arr)[middle] < findVal {
		// 说明我们要查找的数，应该在 middle+1 ~ rightIndex
		BinaryFind(arr, middle+1, rightIndex, findVal)
	} else {
		// 找到了
		fmt.Printf("找到了，下标为%v\n", middle)
	}
}
