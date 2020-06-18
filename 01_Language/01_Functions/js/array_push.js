function array_push(arr) {
    if (Array.isArray(arr)) {
        let nextIdx = arr.length
        for (let i = 1; i < arguments.length; i++) {
            arr[nextIdx] = arguments[i]
            nextIdx++
        }
        return arr.length
    }

    let maxIdx = 0
    let length = 0
    for (let i in arr) {
        if (Number.isInteger(i * 1)) {
            let curIdx = i * 1
            if (curIdx > maxIdx) {
                maxIdx = curIdx
            }
        }
        length++
    }
    for (let i = 1; i < arguments.length; i++) {
        maxIdx++
        arr[maxIdx] = arguments[i]
        length++
    }
    return length
}

let stack = ["orange", "banana"]
console.log(array_push(stack, "apple", "raspberry"), stack)

let stack1 = {"k1": "orange", 2: "banana"}
console.log(array_push(stack1, "apple", "raspberry"), stack1)
