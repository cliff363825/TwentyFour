package functions

import (
	"fmt"
	"github.com/h2non/filetype"
	"log"
	"os"
	"testing"
)

func TestFinfoBuffer(t *testing.T) {
	buf := make([]byte, 512)
	file, err := os.Open("test.gif")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	n, _ := file.Read(buf)
	fmt.Println("n=", n)
	fmt.Println(filetype.Match(buf[:n]))
}

func TestFinfoClose(t *testing.T) {
	buf := make([]byte, 512)
	file, err := os.Open("test.gif")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	n, _ := file.Read(buf)
	fmt.Println(filetype.Match(buf[:n]))
}

func TestFinfoFile(t *testing.T) {
	fmt.Println(filetype.MatchFile("test.gif"))
}

func TestFinfoOpen(t *testing.T) {
	buf := make([]byte, 512)
	file, err := os.Open("test.gif")
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()
	n, _ := file.Read(buf)
	fmt.Println(filetype.Match(buf[:n]))
}
