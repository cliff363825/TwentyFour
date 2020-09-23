package functions

import (
	"bufio"
	"os"
)

func File(filename string) ([]string, error) {
	file, err := os.Open(filename)
	if err != nil {
		return nil, err
	}
	defer file.Close()

	var res []string
	reader := bufio.NewReader(file)
	for {
		s, err := reader.ReadString('\n')
		res = append(res, s)
		if err != nil {
			break
		}
	}
	return res, nil
}
