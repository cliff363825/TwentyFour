package wan

import (
	"errors"
	"fmt"
	"testing"
)

// 使用一个结构体管理队列
type Queue struct {
	maxSize int
	array   [5]int // 数组 => 模拟队列
	front   int    // 表示指向队列首
	rear    int    // 表示指向队列的尾部
}

func (q *Queue) AddQueue(val int) (err error) {
	// 先判断队列是否已满
	if q.rear == q.maxSize-1 { // 重要重要的提示；rear是队列尾部，（含最后元素）
		err = errors.New("queue full")
		return
	}

	q.rear++ // rear后移
	q.array[q.rear] = val
	return
}

// 从队列中取出数据
func (q *Queue) GetQueue() (val int, err error) {
	if q.front == q.rear { // 队空
		err = errors.New("queue empty")
		return
	}

	q.front++
	val = q.array[q.front]
	return
}

// 显示队列，找到队首，然后遍历到队尾
func (q *Queue) ShowQueue() {
	// q.front 不包含队首的元素
	for i := q.front + 1; i <= q.rear; i++ {
		fmt.Printf("array[%v]=%v\n", i, q.array[i])
	}
}

func TestQueue1(t *testing.T) {
	queue := Queue{
		maxSize: 5,
		front:   -1,
		rear:    -1,
	}

	queue.AddQueue(100)
	queue.ShowQueue()
}

type CircleQueue struct {
	maxSize int    // 4
	array   [4]int // 数组
	head    int    // 指向队列队首 0
	tail    int    // 指向队列队尾 0
}

func (q *CircleQueue) Push(val int) (err error) {
	if q.IsFull() {
		err = errors.New("queue full")
		return
	}

	q.array[q.tail] = val // 把值给尾部
	q.tail = (q.tail + 1) % q.maxSize
	return
}

// 从队列中取出数据
func (q *CircleQueue) Pop() (val int, err error) {
	if q.IsEmpty() {
		err = errors.New("queue empty")
		return
	}

	val = q.array[q.head]
	q.head = (q.head + 1) % q.maxSize
	return
}

func (q *CircleQueue) ListQueue() (val int, err error) {
	size := q.Size()
	if size == 0 {
		fmt.Println("队列为空")
	}

	tempHead := q.head
	for i := 0; i < size; i++ {
		fmt.Printf("arr[%d]=%d\n", tempHead, q.array[tempHead])
		tempHead = (tempHead + 1) % q.maxSize
	}

	return
}

// 判断环形队列为空
func (q *CircleQueue) IsFull() bool {
	return (q.tail+1)%q.maxSize == q.head
}

// 判断环形队列是空
func (q *CircleQueue) IsEmpty() bool {
	return q.tail == q.head
}

// 取出环形队列有多少个元素
func (q *CircleQueue) Size() int {
	// 这是一个关键的算法
	return (q.tail + q.maxSize - q.head) % q.maxSize
}

func TestQueue2(t *testing.T) {
	queue := CircleQueue{
		maxSize: 4,
		head:    0,
		tail:    0,
	}

	queue.Push(1)
	queue.Push(2)
	queue.ListQueue()

	queue.Pop()
	queue.Pop()
	queue.ListQueue()

	queue.Push(3)
	queue.Push(4)
	queue.Push(5)
	queue.Push(6)
	queue.Push(7)
	queue.Pop()
	queue.Pop()
	queue.Pop()
	queue.Pop()
	queue.Pop()
	queue.ListQueue()
}
