package functions

import "html"

func Htmlentities(s string) string {
	return html.EscapeString(s)
}
