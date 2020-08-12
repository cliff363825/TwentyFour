package functions

import "os"

func GetIncludePath() string {
	return os.Getenv("GOPATH")
}
