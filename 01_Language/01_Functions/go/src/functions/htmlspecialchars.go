package functions

import "html"

func Htmlspecialchars(s string) string {
	return html.EscapeString(s)
}
