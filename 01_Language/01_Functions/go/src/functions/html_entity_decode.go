package functions

import "html"

func HtmlEntityDecode(s string) string {
	return html.UnescapeString(s)
}
