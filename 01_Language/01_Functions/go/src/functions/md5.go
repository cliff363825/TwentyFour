package functions

import (
	"crypto/md5"
	"encoding/hex"
)

func Md5(str string) string {
	h := md5.New()
	h.Write([]byte(str))
	bytes := h.Sum(nil)
	return hex.EncodeToString(bytes)
}
