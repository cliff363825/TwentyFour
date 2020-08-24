package functions

import (
	"fmt"
	"testing"
)

func TestGetenv(t *testing.T) {
	//GetAllEnv()
	fmt.Println(Getenv("PATH"))
}
