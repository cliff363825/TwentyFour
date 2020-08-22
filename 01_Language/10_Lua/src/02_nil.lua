print(type(a))
print(type(a) == nil)
print(type(a) == "nil")

tab1 = {key1 = "val1", key2 = "val2", "val3"}
for k, v in pairs(tab1) do
    print(k .. " - " .. v)
end

tab1.key1 = nil -- unset(tab1["key1"])

for k, v in pairs(tab1) do
    print(k .. " - " .. v)
end