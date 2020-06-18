package functions

import "os/exec"

func Exec(name string, arg ...string) (string, error) {
	cmd := exec.Command(name, arg...)
	bytes, err := cmd.Output()
	if err != nil {
		return "", err
	}
	return string(bytes), nil
}
