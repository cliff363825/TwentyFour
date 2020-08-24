function getdate(timestamp) {
    let d = typeof timestamp === 'undefined' ? new Date() : new Date(timestamp * 1000)

    let _w = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']
    let _m = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']

    let res = {}
    res.seconds = d.getSeconds()
    res.minutes = d.getMinutes()
    res.hours = d.getHours()
    res.mday = d.getDate()
    res.wday = d.getDay()
    res.mon = d.getMonth() + 1
    res.year = d.getFullYear()
    res.yday = Math.floor((d - (new Date(d.getFullYear(), 0, 1))) / 86400000)
    res.weekday = _w[d.getDay()]
    res.month = _m[d.getMonth()]
    res[0] = parseInt(d.getTime() / 1000)
    return res
}

console.log(getdate())
