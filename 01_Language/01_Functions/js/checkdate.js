// https://locutus.io/php/datetime/checkdate/index.html
function checkdate(m, d, y) {
    return m > 0 && m < 13 &&
        y > 0 && y < 32768 &&
        d > 0 && d <= (new Date(y, m, 0)).getDate()
}

console.log(checkdate(12, 31, 2000))
console.log(checkdate(2, 29, 2001))
