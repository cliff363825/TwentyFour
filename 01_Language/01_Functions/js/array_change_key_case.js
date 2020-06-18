function array_change_key_case(arr, caseType = 'CASE_LOWER') {
    if (Array.isArray(arr)) {
        return arr;
    }

    let res = {}
    let func = (!caseType || caseType === 'CASE_LOWER') ? 'toLowerCase' : 'toUpperCase'
    for (let k in arr) {
        res[k[func]()] = arr[k]
    }
    return res
}

let obj = {
    "FirSt": 1,
    "SecOnd": 4,
}
console.log(array_change_key_case(obj, "CASE_UPPER"))
console.log(array_change_key_case([1, 3, 5, 7, 9], "CASE_UPPER"))
