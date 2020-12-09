package functions

import (
	"fmt"
	"os"
	"testing"
)

func TestMkdir(t *testing.T) {
	fmt.Println(Mkdir("./depth1/depth2/depth3/", os.ModePerm, true))
}
