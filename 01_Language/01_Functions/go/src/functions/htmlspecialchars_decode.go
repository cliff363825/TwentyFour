package functions

import "html"

func HtmlspecialcharsDecode(s string) string {
	return html.UnescapeString(s)
}
