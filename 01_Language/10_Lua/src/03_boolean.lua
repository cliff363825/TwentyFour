print(type(true))
print(type(false))
print(type(nil))

if false then
    print("false is true")
else
    print("false is false")
end

if nil then
    print("nil is true")
else
    print("nil is false")
end

if 0 then
    print("0 is true")
else
    print("0 is false")
end

if 0 == true then
    print("0 == true is true")
else
    print("0 == true is false")
end
