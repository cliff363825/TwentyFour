package functions

import (
	"fmt"
	"log"
	"os"
	"testing"
)

func TestFscanf(t *testing.T) {
	//javier  argonaut        pe
	file, err := os.Open("test.txt")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	var (
		name string
		profession string
		countrycode string
	)
	_, err = fmt.Fscanf(file, "%s\t%s\t%s\n", &name, &profession, &countrycode)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(name, profession, countrycode)
}

