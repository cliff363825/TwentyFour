package functions

import path2 "path"

func Pathinfo(path string) map[string]string {
	var m = map[string]string{}
	m["dirname"] = path2.Dir(path)
	m["basename"] = path2.Base(path)
	m["extension"] = path2.Ext(path)[1:]
	m["filename"] = path2.Base(path)[0:len(path2.Ext(path))]
	return m
}
