package functions

import (
	"fmt"
	"net/smtp"
	"testing"
)

func TestMail(t *testing.T) {
	err := smtp.SendMail("localhost:25", nil, "sender@example.com", []string{"caffeinated@example.com"}, []byte("Line 1\nLine 2\nLine 3"))
	fmt.Println(err)
}
