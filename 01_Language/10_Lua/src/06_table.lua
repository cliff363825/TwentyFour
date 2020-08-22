local tbl1 = {}

tbl1["key"] = 22
tbl1["key"] = tbl1["key"] + "11"
for k, v in pairs(tbl1) do
    print(k .. " => " .. v)
end
print(tbl1.key)
print(tbl1["key"])

local tbl2 = {"apple", "pear", "orange", "grape"}
for k, v in pairs(tbl2) do
   print(k .. " => " .. v)
end

local tbl3 = {}
for i = 1, 10 do
    tbl3[i] = i
end
for k, v in pairs(tbl3) do
    print(k .. " => " .. v)
end
