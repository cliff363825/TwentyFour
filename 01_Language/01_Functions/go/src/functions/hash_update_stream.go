package functions

import (
	"hash"
	"io"
)

func HashUpdateStream(context hash.Hash, handler io.Reader) {
	b := make([]byte, 8192)
	for true {
		n, err := handler.Read(b)
		if err != nil {
			break
		}
		context.Write(b[0:n])
	}
}
