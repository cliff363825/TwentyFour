package functions

import (
	"image"
	_ "image/gif"
	"log"
	"os"
)

func Getimagesize(filename string) (int, int) {
	file, err := os.Open(filename)
	if err != nil {
		log.Fatal(err)
	}
	defer file.Close()

	config, _, err := image.DecodeConfig(file)
	if err != nil {
		log.Fatal(err)
	}

	return config.Width, config.Height
}
