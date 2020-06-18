function array_rand(arr, num = 1) {
    let res = []
    let keys = Object.keys(arr)
    let endIndex = keys.length - 1
    for (let i = 0; i < num; i++) {
        let targetIndex = Math.floor(Math.random() * (endIndex + 1))
        res.push(keys[targetIndex])
        keys[targetIndex] = keys[endIndex]
        endIndex--
    }
    return num === 1 ? res[0] : res
}

let input = ["Neo", "Morpheus", "Trinity", "Cypher", "Tank"]
console.log(array_rand(input, 2));

console.log(array_rand(input));

input = {"k1": "Neo", "k2": "Morpheus", "k3": "Trinity", "k4": "Cypher", "k5": "Tank"}
console.log(array_rand(input, 2));
