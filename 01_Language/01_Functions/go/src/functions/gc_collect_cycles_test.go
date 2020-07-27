package functions

import (
	"runtime"
	"testing"
)

func TestGcCollectCycles(t *testing.T) {
	runtime.GC()
}

