package wan

import (
	"fmt"
	"testing"
)

type ValNode struct {
	Row int
	Col int
	Val int
}

func TestSparseArray(t *testing.T) {
	// 1. 创建一个原始数组
	var chessMap [11][11]int
	chessMap[1][2] = 1 // 黑子
	chessMap[2][3] = 2 // 蓝子

	// 2. 输出看看原始的数组
	for _, v := range chessMap {
		for _, v2 := range v {
			fmt.Printf("%v ", v2)
		}
		fmt.Println()
	}

	// 3. 转成稀疏数组
	// 1. 遍历 chessMap，如果我们发现又一个元素的值不为0，创建一个node结构体
	// 2. 将其放入到对应的切片即可
	var sparseArr []ValNode

	// 标准的一个稀疏数组应该还有一个记录元素的二维数组的规模（行和列，默认值）
	var valNode = ValNode{
		Row: 11,
		Col: 11,
		Val: 0,
	}
	sparseArr = append(sparseArr, valNode)

	for i, v := range chessMap {
		for j, v2 := range v {
			if v2 != 0 {
				// 创建一个ValNode 值节点
				var valNode = ValNode{
					Row: i,
					Col: j,
					Val: v2,
				}
				sparseArr = append(sparseArr, valNode)
			}
		}
	}

	// 输出稀疏数组
	fmt.Println("当前的稀疏数组")
	for i, valNode := range sparseArr {
		fmt.Printf("%d: %d %d %d\n", i, valNode.Row, valNode.Col, valNode.Val)
	}

	// 将这个稀疏数组 存盘

	// 如何恢复原始的数组

	// 1. 打开这个
	// 2. 这里使用稀疏数组恢复
	// 先创建一个原始数组
	defaultValNode := sparseArr[0]

	var chessMap2 = make([][]int, defaultValNode.Row)
	for k := range chessMap2 {
		chessMap2[k] = make([]int, defaultValNode.Col)
	}

	var n = 0
	for i, v := range chessMap2 {
		for j, _ := range v {
			var sIndex = 0
			for k, valNode := range sparseArr {
				n++
				if k == 0 {
					chessMap2[i][j] = valNode.Val
				} else if valNode.Row == i && valNode.Col == j {
					sIndex = k
					chessMap2[i][j] = valNode.Val
					break
				}
			}
			if sIndex != 0 {
				sparseArr = append(sparseArr[0:sIndex], sparseArr[sIndex+1:]...)
			}
		}
	}
	fmt.Println(n)
}
