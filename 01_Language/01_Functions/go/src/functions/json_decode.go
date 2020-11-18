package functions

import "encoding/json"

func JsonDecode(s string, v interface{}) error {
	return json.Unmarshal([]byte(s), v)
}
