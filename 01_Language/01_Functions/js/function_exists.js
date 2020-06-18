// https://locutus.io/php/funchand/function_exists/index.html
function function_exists(funcName) {
    let $global = (typeof window !== 'undefined' ? window : global)

    if (typeof funcName === 'string') {
        funcName = $global[funcName]
    }

    return typeof funcName === 'function'
}
