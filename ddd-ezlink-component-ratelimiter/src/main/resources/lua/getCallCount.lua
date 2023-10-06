-- 获取方法调用次数

local key = KEYS[1]
local count = tonumber(ARGV[1])
local time = tonumber(ARGV[2])
local currentCount = redis.call('INCRBY', key, 1)

-- 如果是第 1 次调用，则给当前 key 设置一个过期时间
if currentCount == 1 then
    redis.call('expire', key, time)
end

-- 返回方法的调用次数
return tonumber(currentCount)
