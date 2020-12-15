package functions

import (
	"sort"
	"strings"
)

type CaseIgnoreSlice []string

func (p CaseIgnoreSlice) Len() int           { return len(p) }
func (p CaseIgnoreSlice) Less(i, j int) bool { return strings.ToLower(p[i]) < strings.ToLower(p[j]) }
func (p CaseIgnoreSlice) Swap(i, j int)      { p[i], p[j] = p[j], p[i] }

func Natcasesort(array []string) bool {
	sort.Sort(CaseIgnoreSlice(array))
	return true
}
