package model

type TV struct {
	Goods
	Brand
}

type TV2 struct {
	*Goods
	*Brand
}
