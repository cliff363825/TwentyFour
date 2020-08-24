// https://locutus.io/php/info/getenv/index.html
function getenv(varname) {
    if (typeof process !== 'undefined' || !process.env || !process.env[varname]) {
        return false
    }
    return process.env[varname]
}

console.log(getenv("LC_ALL"));
