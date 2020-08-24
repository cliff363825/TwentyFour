package functions

import (
	"os"
)

func Getenv(varname string) string {
	return os.Getenv(varname)
}

func GetAllEnv() []string {
	return os.Environ()
}
