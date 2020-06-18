function array_merge() {
    let res = []
    if (arguments.length === 0) {
        return []
    }

    for (let i in arguments) {
        if (!Array.isArray(arguments[i])) {
            res = {}
            break
        }
    }

    if (Array.isArray(res)) {
        res = res.concat(...arguments)
    } else {
        let index = 0
        for (let i in arguments) {
            for (let j in arguments[i]) {
                // typeof j === 'string'
                if (Number.isInteger(j * 1)) {
                    res[index] = arguments[i][j]
                    index++
                } else {
                    res[j] = arguments[i][j]
                }
            }
        }
    }
    return res
}

let array1 = {"color": "red", 0: 2, 1: 4}
let array2 = {0: "a", 1: "b", "color": "green", "shape": "trapezoid", 2: 4}
console.log(array_merge(array1, array2));
