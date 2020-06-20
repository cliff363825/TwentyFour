package file

import (
	"bufio"
	"fmt"
	"io"
	"io/ioutil"
	"os"
	"testing"
)

func TestFile(t *testing.T) {
	// 打开文件
	// php: fopen($path, "r")
	file, err := os.Open("/Users/songhongfa/git/my_go/file.txt")
	if err != nil {
		fmt.Println("open err=", err)
		return
	}
	fmt.Println("打开文件，资源类型使用完后需要进行关闭")

	defer func() {
		// 关闭文件
		// php: fclose($fh)
		err = file.Close()
		if err != nil {
			fmt.Println("close err=", err)
		} else {
			fmt.Println("关闭文件")
		}
	}()

	// 输出下文件，看看文件是什么
	fmt.Printf("file=(%T)%v\n", file, file)
}

func TestFile1(t *testing.T) {
	// 打开文件
	file, err := os.Open("/Users/songhongfa/git/my_go/README.md")
	if err != nil {
		fmt.Println("open err=", err)
		return
	}

	// 当函数退出时，要及时的关闭file
	// 要及时关闭file句柄，否则会有内存泄漏
	defer file.Close()

	// 创建一个 *Reader，是带缓冲的
	// java: new BufferedReader(new FileReader(file))
	//const (
	//	defaultBufSize = 4096 // 默认的缓冲区为4096
	//)

	reader := bufio.NewReader(file)
	// 循环的读取文件的内容
	for {
		s, err := reader.ReadString('\n') // 读到一个换行就结束
		if err == io.EOF { // io.EOF表示文件的末尾
			break
		}

		// 输出内容
		fmt.Print(s)
	}

	fmt.Println("文件读取结束...")
}

func TestFile2(t *testing.T) {
	// 使用 ioutil.ReadFile 一次性将文件读取到位
	// php: file_get_contents($path)
	file := "/Users/songhongfa/git/my_go/README.md"
	content, err := ioutil.ReadFile(file)
	if err != nil {
		fmt.Printf("read file err=%v\n", err)
		return
	}

	// 把读取到的内容显示到终端
	fmt.Printf("%v", content) // []byte
	// 我们没有显式的Open文件，因此也不需要显示的Close文件
	// 因为，文件的Open和Close被封装到 ReadFile 函数内部
}

func TestFile3(t *testing.T) {
	// 创建一个新文件，写入内容
	// 1. 打开文件
	filePath := "/Users/songhongfa/git/my_go/file.txt"
	file, err := os.OpenFile(filePath, os.O_WRONLY|os.O_CREATE, 0644)
	if err != nil {
		fmt.Printf("open file err=%v\n", err)
		return
	}

	// 及时关闭file句柄
	defer file.Close()

	str := "hello,world\n"
	// 写入时，使用带缓存的 *Writer
	// java: new BufferedWriter(new FileWriter(file))
	writer := bufio.NewWriter(file)
	for i := 0; i < 5; i++ {
		_, err := writer.WriteString(str)
		if err != nil {
			fmt.Println("write err=", err)
			continue
		}
	}

	// 因为writer是带缓存，因此在调用 WriteString 方法时，
	// 其实内容是先写入到缓存的，所以需要调用 Flush 方法，将缓冲的数据
	// 真正写入到文件中，否则文件中会没有数据
	writer.Flush()
}

// Exactly one of O_RDONLY, O_WRONLY, or O_RDWR must be specified.
// O_RDONLY int = syscall.O_RDONLY // open the file read-only. 只读
// O_WRONLY int = syscall.O_WRONLY // open the file write-only. 只写
// O_RDWR   int = syscall.O_RDWR   // open the file read-write. 读写
// The remaining values may be or'ed in to control behavior.
// O_APPEND int = syscall.O_APPEND // append data to the file when writing. 追加
// O_CREATE int = syscall.O_CREAT  // create a new file if none exists. 创建
// O_EXCL   int = syscall.O_EXCL   // used with O_CREATE, file must not exist. 创建（文件必须不存在，如果存在则panic）
// O_SYNC   int = syscall.O_SYNC   // open for synchronous I/O. 同步
// O_TRUNC  int = syscall.O_TRUNC  // truncate regular writable file when opened. 清空
func TestFile4(t *testing.T) {
	// 创建一个新文件，写入内容
	// 1. 打开文件
	filePath := "/Users/songhongfa/git/my_go/file.txt"
	file, err := os.OpenFile(filePath, os.O_RDWR|os.O_APPEND, 0644)
	if err != nil {
		fmt.Printf("open file err=%v\n", err)
		return
	}

	// 及时关闭file句柄
	defer file.Close()

	reader := bufio.NewReader(file)
	for {
		str, err := reader.ReadString('\n')
		if err == io.EOF { // 如果读到文件末尾
			break
		}
		// 显示到终端
		fmt.Print(str)
	}

	str := "hello,北京\n"
	// 写入时，使用带缓存的 *Writer
	writer := bufio.NewWriter(file)
	for i := 0; i < 5; i++ {
		_, err := writer.WriteString(str)
		if err != nil {
			fmt.Println("write err=", err)
			continue
		}
	}

	// 因为writer是带缓存，因此在调用 WriteString 方法时，
	// 其实内容是先写入到缓存的，所以需要调用 Flush 方法，将缓冲的数据
	// 真正写入到文件中，否则文件中会没有数据
	writer.Flush()
}

func TestFile5(t *testing.T) {
	var file1Path = "/Users/songhongfa/git/my_go/file.txt"
	var file2Path = "/Users/songhongfa/git/my_go/file1.txt"

	content, err := ioutil.ReadFile(file1Path)
	if err != nil {
		// 说明读取文件有错误
		fmt.Println("read file err=", err)
		return
	}

	err = ioutil.WriteFile(file2Path, content, 0644)
	if err != nil {
		fmt.Println("write file err=", err)
		return
	}
}

func TestFile6(t *testing.T) {
	exists, _ := Exists("/Users/songhongfa/git/my_go/file.txt")
	fmt.Printf("exists=(%T)%v\n", exists, exists)

	exists1, _ := Exists("/Users/songhongfa/git/my_go/file2.txt")
	fmt.Printf("exists1=(%T)%v\n", exists1, exists1)
}

func Exists(path string) (bool, error) {
	_, err := os.Stat(path)
	if err == nil {
		return true, nil
	}

	if os.IsNotExist(err) {
		return false, nil
	}

	return false, err
}

func TestFile7(t *testing.T) {
	_, err := CopyFile("/Users/songhongfa/git/my_go/WechatIMG615_1.jpeg", "/Users/songhongfa/git/my_go/WechatIMG615.jpeg")
	fmt.Println("err=", err)
}

func CopyFile(dst string, src string) (written int64, err error) {
	srcFile, err := os.Open(src)
	if err != nil {
		fmt.Println("open file err=", err)
		return
	}
	defer srcFile.Close()

	dstFile, err := os.OpenFile(dst, os.O_WRONLY|os.O_CREATE|os.O_TRUNC, 0644)
	if err != nil {
		fmt.Println("open file err=", err)
		return
	}
	defer dstFile.Close()

	reader := bufio.NewReader(srcFile)
	writer := bufio.NewWriter(dstFile)

	return io.Copy(writer, reader)
}
