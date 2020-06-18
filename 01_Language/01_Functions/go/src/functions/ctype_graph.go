package functions

func CtypeGraph(s string) bool {
	if len(s) == 0 {
		return false
	}
	for i := 0; i < len(s); i++ {
		codePoint := int(s[i])
		if codePoint > 32 && codePoint <= 126 {
			continue
		}
		return false
	}
	return true
}
