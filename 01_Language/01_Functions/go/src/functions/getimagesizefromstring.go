package functions

import (
	"bytes"
	"image"
	_ "image/gif"
	"log"
)

func Getimagesizefromstring(imagedata []byte) (int, int) {
	reader := bytes.NewReader(imagedata)
	config, _, err := image.DecodeConfig(reader)
	if err != nil {
		log.Fatal(err)
	}
	return config.Width, config.Height
}
