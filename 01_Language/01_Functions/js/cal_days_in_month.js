function cal_days_in_month(month, year) {
    let d = new Date(year, month, 0)
    return d.getDate()
}

console.log(cal_days_in_month(8, 2003));
