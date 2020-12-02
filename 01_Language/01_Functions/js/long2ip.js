function long2ip(properAddress) {
    return (properAddress >>> 24 & 0xFF) +
        '.' + (properAddress >>> 16 & 0xFF) +
        '.' + (properAddress >>> 8 & 0xFF) +
        '.' + (properAddress & 0xFF)
}

console.log(long2ip('3245348745'))
