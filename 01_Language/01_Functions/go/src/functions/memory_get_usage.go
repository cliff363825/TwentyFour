package functions

import "runtime"

func MemoryGetUsage() uint64 {
	var m runtime.MemStats
	runtime.ReadMemStats(&m)
	return m.TotalAlloc
}
