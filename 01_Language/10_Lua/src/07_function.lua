function factorial(n)
    if n == 1 then
        return 1
    else
        return n * factorial(n-1)
    end
end

print(factorial(5))
factorial1 = factorial
print(factorial1(5))

function testFun(tab, func)
    for k, v in pairs(tab) do
        print(func(k, v))
    end
end

tab = {key1 = "val1", key2 = "val2"}
testFun(tab,
        function (k, v)
            return k .. " => " .. v
        end)