package model

// 定义一个结构体
type student struct {
	Name  string
	score float64
}

// 因为student结构体首字母是小写，因此是只能在model包使用
// 我们通过工厂模式来解决
func NewStudent(n string, s float64) *student {
	return &student{
		Name:  n,
		score: s,
	}
}

// 如果score字段首字母小写，则，在其他包不可以直接访问，我们可以提供一个方法
func (s *student) GetScore() float64 {
	return s.score
}
